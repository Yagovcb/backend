package br.com.bcbdigital.user_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.backend.dtos.exceptions.MethodNotAllowedException;
import br.com.bcbdigital.backend.dtos.exceptions.UserNotFoundException;
import br.com.bcbdigital.user_api.mock.UserMock;
import br.com.bcbdigital.user_api.model.User;
import br.com.bcbdigital.user_api.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserService.class})
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserService - Classe de teste unitario")
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("Teste de serviço que tenta recuperar todos os usuarios")
    void testGetAll() {
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.userService.getAllUsers(null).isEmpty());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    @Order(2)
    @DisplayName("Teste de serviço que tenta recuperar um usuario pelo seu ID")
    void testFindById() {
        User user = UserMock.getUsuarioCompletoMock();

        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        UserDTO actualFindByIdResult = this.userService.findById(1L);

        assertEquals(user.getCpf(), actualFindByIdResult.getCpf());
        assertEquals(user.getTelefone(), actualFindByIdResult.getTelefone());
        assertEquals(user.getNome(), actualFindByIdResult.getNome());
        assertEquals(user.getKey(), actualFindByIdResult.getKey());
        assertEquals(user.getEndereco(), actualFindByIdResult.getEndereco());
        assertEquals(user.getEmail(), actualFindByIdResult.getEmail());
        assertEquals(user.getDataCadastro(), actualFindByIdResult.getDataCadastro());
        verify(this.userRepository).findById(any());
    }


    @Test
    @Order(3)
    @DisplayName("Teste de serviço que tenta salvar um usuario")
    void testSave() {
        User user = UserMock.getUsuarioCompletoMock();

        when(this.userRepository.save(any())).thenReturn(user);
        UserDTO actualSaveResult = this.userService.save(modelMapper.map(user, UserDTO.class));

        assertEquals(user.getCpf(), actualSaveResult.getCpf());
        assertEquals(user.getTelefone(), actualSaveResult.getTelefone());
        assertEquals(user.getNome(), actualSaveResult.getNome());
        assertEquals(user.getKey(), actualSaveResult.getKey());
        assertEquals(user.getEndereco(), actualSaveResult.getEndereco());
        assertEquals(user.getEmail(), actualSaveResult.getEmail());
        assertEquals(user.getDataCadastro(), actualSaveResult.getDataCadastro());
        verify(this.userRepository).save(any());
    }

    @Test
    @Order(4)
    @DisplayName("Teste de serviço que tenta salvar um usuario forçando o erro MethodNotAllowed")
    void testSave_MethodNotAllowedException() {
        User user = UserMock.getUsuarioCompletoMock();
        when(this.userRepository.save(any())).thenReturn(user);

        UserDTO dto = modelMapper.map(user, UserDTO.class);

        when(this.userRepository.existsUserByCpfAndNome(any(), any())).thenReturn(true);
        assertThrows(MethodNotAllowedException.class, () -> this.userService.save(dto));
        verify(this.userRepository).existsUserByCpfAndNome(any(), any());

    }

    @Test
    @Order(5)
    @DisplayName("Teste de serviço que tenta deletar um usuario")
    void testDelete() {
        User user = UserMock.getUsuarioCompletoMock();

        Optional<User> ofResult = Optional.of(user);
        doNothing().when(this.userRepository).delete(any());
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        DetalheRespostaDTO actualDeleteResult = this.userService.delete(1L);

        assertEquals(200, actualDeleteResult.getStatus());
        assertEquals("Usuario deletado com sucesso", actualDeleteResult.getMensagem());
        verify(this.userRepository).delete(any());
        verify(this.userRepository).findById(any());
    }


    @Test
    @Order(6)
    @DisplayName("Teste de serviço que tenta recuperar um usuario pelo seu CPF")
    void testFindByCpf() {
        User user = UserMock.getUsuarioCompletoMock();

        when(this.userRepository.findByCpf(any())).thenReturn(user);
        UserDTO actualFindByCpfResult = this.userService.findByCpf(user.getCpf());

        assertEquals(user.getCpf(), actualFindByCpfResult.getCpf());
        assertEquals(user.getTelefone(), actualFindByCpfResult.getTelefone());
        assertEquals(user.getNome(), actualFindByCpfResult.getNome());
        assertEquals(user.getKey(), actualFindByCpfResult.getKey());
        assertEquals(user.getEndereco(), actualFindByCpfResult.getEndereco());
        assertEquals(user.getEmail(), actualFindByCpfResult.getEmail());
        assertEquals(user.getDataCadastro(), actualFindByCpfResult.getDataCadastro());
        verify(this.userRepository).findByCpf(any());
    }

    @Test
    @Order(7)
    @DisplayName("Teste de serviço que tenta recuperar um usuario pelo seu CPF forçando erro UserNotFound")
    void testFindByCpf_UserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> {
            this.userService.findByCpf("213213215412041289");
        }, "Usuario não encontrado");
    }

    @Test
    @Order(8)
    @DisplayName("Teste de serviço que tenta recuperar um usuario pelo seu CPF e a Key")
    void testFindByCpfAndKey() {
        User user = UserMock.getUsuarioCompletoMock();

        when(this.userRepository.findByCpfAndKey(any(), any())).thenReturn(user);
        UserDTO actualFindByCpfAndKeyResult = this.userService.findByCpfAndKey(user.getCpf(), user.getKey());

        assertEquals(user.getCpf(), actualFindByCpfAndKeyResult.getCpf());
        assertEquals(user.getTelefone(), actualFindByCpfAndKeyResult.getTelefone());
        assertEquals(user.getNome(), actualFindByCpfAndKeyResult.getNome());
        assertEquals(user.getKey(), actualFindByCpfAndKeyResult.getKey());
        assertEquals(user.getEndereco(), actualFindByCpfAndKeyResult.getEndereco());
        assertEquals(user.getEmail(), actualFindByCpfAndKeyResult.getEmail());
        assertEquals(user.getDataCadastro(), actualFindByCpfAndKeyResult.getDataCadastro());
        verify(this.userRepository).findByCpfAndKey(any(), any());
    }

    @Test
    @Order(9)
    @DisplayName("Teste de serviço que tenta recuperar um usuario pelo seu CPF e a Key forçando erro UserNotFound")
    void testFindByCpfAndKey_UserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> {
            this.userService.findByCpfAndKey("213213215412041289", "123125132");
        }, "Usuario não encontrado");
    }

    @Test
    @Order(10)
    @DisplayName("Teste de serviço que tenta recuperar um usuario pelo seu nome")
    void testQueryByName() {
        User user = UserMock.getUsuarioCompletoMock();

        User user1 = new User();
        user1.setEndereco("Endereco");
        user1.setEmail("jane.doe@example.org");
        user1.setKey("Key");
        user1.setNome("Nome");
        user1.setCpf("Cpf");
        user1.setId(123L);
        user1.setDataCadastro(LocalDate.ofEpochDay(1L));
        user1.setTelefone("Telefone");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user);
        when(this.userRepository.queryByNomeLike(any())).thenReturn(userList);
        assertEquals(2, this.userService.queryByName("Name").size());
        verify(this.userRepository).queryByNomeLike(any());
    }

}


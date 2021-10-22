package br.com.bcbdigital.user_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.user_api.model.User;
import br.com.bcbdigital.user_api.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    @Test
    void testGetAll() {
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.userService.getAll(null).isEmpty());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAll2() {
        var userList = UserDTO.usuariosList.stream().map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
        PageImpl<User> pageImpl = new PageImpl<>(userList);
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, this.userService.getAll(null).size());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAll3() {
        var users = UserDTO.usuariosList.stream().map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
        PageImpl<User> pageImpl = new PageImpl<>(users);
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(2, this.userService.getAll(null).size());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAll4() {
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(this.userService.getAll(null).isEmpty());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAll5() {
        var users = UserDTO.usuariosList.stream().map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
        PageImpl<User> pageImpl = new PageImpl<>(users);
        when(this.userRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, this.userService.getAll(null).size());
        verify(this.userRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        UserDTO actualFindByIdResult = this.userService.findById(123L);
        assertEquals("Cpf", actualFindByIdResult.getCpf());
        assertTrue(actualFindByIdResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByIdResult.getTelefone());
        assertEquals("Nome", actualFindByIdResult.getNome());
        assertEquals("Key", actualFindByIdResult.getKey());
        assertEquals("Endereco", actualFindByIdResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByIdResult.getEmail());
        assertEquals("1970-01-02", actualFindByIdResult.getDataCadastro().toString());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testFindById2() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(null);
        user.setTelefone("Telefone");
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        UserDTO actualFindByIdResult = this.userService.findById(123L);
        assertEquals("Cpf", actualFindByIdResult.getCpf());
        assertTrue(actualFindByIdResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByIdResult.getTelefone());
        assertEquals("Nome", actualFindByIdResult.getNome());
        assertEquals("Key", actualFindByIdResult.getKey());
        assertEquals("Endereco", actualFindByIdResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByIdResult.getEmail());
        assertNull(actualFindByIdResult.getDataCadastro());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testFindById3() {
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());
        assertNull(this.userService.findById(123L));
        verify(this.userRepository).findById(any());
    }

    @Test
    void testSave() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        when(this.userRepository.save(any())).thenReturn(user);
        UserDTO actualSaveResult = this.userService.save(new UserDTO());
        assertEquals("Cpf", actualSaveResult.getCpf());
        assertTrue(actualSaveResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualSaveResult.getTelefone());
        assertEquals("Nome", actualSaveResult.getNome());
        assertEquals("Key", actualSaveResult.getKey());
        assertEquals("Endereco", actualSaveResult.getEndereco());
        assertEquals("jane.doe@example.org", actualSaveResult.getEmail());
        assertEquals("1970-01-02", actualSaveResult.getDataCadastro().toString());
        verify(this.userRepository).save(any());
    }

    @Test
    void testDelete() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(this.userRepository).delete(any());
        when(this.userRepository.findById(any())).thenReturn(ofResult);
        DetalheRespostaDTO actualDeleteResult = this.userService.delete(123L);
        assertEquals(200, actualDeleteResult.getStatus());
        assertEquals("Usuario deletado com sucesso", actualDeleteResult.getMensagem());
        verify(this.userRepository).delete(any());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.userRepository).delete(any());
        when(this.userRepository.findById(any())).thenReturn(Optional.empty());
        DetalheRespostaDTO actualDeleteResult = this.userService.delete(123L);
        assertEquals(200, actualDeleteResult.getStatus());
        assertEquals("Usuario deletado com sucesso", actualDeleteResult.getMensagem());
        verify(this.userRepository).findById(any());
    }

    @Test
    void testFindByCpf() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        when(this.userRepository.findByCpf(any())).thenReturn(user);
        UserDTO actualFindByCpfResult = this.userService.findByCpf("Cpf");
        assertEquals("Cpf", actualFindByCpfResult.getCpf());
        assertTrue(actualFindByCpfResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByCpfResult.getTelefone());
        assertEquals("Nome", actualFindByCpfResult.getNome());
        assertEquals("Key", actualFindByCpfResult.getKey());
        assertEquals("Endereco", actualFindByCpfResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByCpfResult.getEmail());
        assertEquals("1970-01-02", actualFindByCpfResult.getDataCadastro().toString());
        verify(this.userRepository).findByCpf(any());
    }

    @Test
    void testFindByCpf2() {
        User user = new User();
        user.setEndereco("br.com.bcbdigital.user_api.model.User");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        when(this.userRepository.findByCpf(any())).thenReturn(user);
        UserDTO actualFindByCpfResult = this.userService.findByCpf("Cpf");
        assertEquals("Cpf", actualFindByCpfResult.getCpf());
        assertTrue(actualFindByCpfResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByCpfResult.getTelefone());
        assertEquals("Nome", actualFindByCpfResult.getNome());
        assertEquals("Key", actualFindByCpfResult.getKey());
        assertEquals("br.com.bcbdigital.user_api.model.User", actualFindByCpfResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByCpfResult.getEmail());
        assertEquals("1970-01-02", actualFindByCpfResult.getDataCadastro().toString());
        verify(this.userRepository).findByCpf(any());
    }

    @Test
    void testFindByCpf3() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(null);
        user.setTelefone("Telefone");
        when(this.userRepository.findByCpf(any())).thenReturn(user);
        UserDTO actualFindByCpfResult = this.userService.findByCpf("Cpf");
        assertEquals("Cpf", actualFindByCpfResult.getCpf());
        assertTrue(actualFindByCpfResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByCpfResult.getTelefone());
        assertEquals("Nome", actualFindByCpfResult.getNome());
        assertEquals("Key", actualFindByCpfResult.getKey());
        assertEquals("Endereco", actualFindByCpfResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByCpfResult.getEmail());
        assertNull(actualFindByCpfResult.getDataCadastro());
        verify(this.userRepository).findByCpf(any());
    }

    @Test
    void testFindByCpf4() {
        User user = new User();
        user.setEndereco("br.com.bcbdigital.user_api.model.User");
        user.setEmail("br.com.bcbdigital.user_api.model.User");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        when(this.userRepository.findByCpf(any())).thenReturn(user);
        UserDTO actualFindByCpfResult = this.userService.findByCpf("Cpf");
        assertEquals("Cpf", actualFindByCpfResult.getCpf());
        assertTrue(actualFindByCpfResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByCpfResult.getTelefone());
        assertEquals("Nome", actualFindByCpfResult.getNome());
        assertEquals("Key", actualFindByCpfResult.getKey());
        assertEquals("br.com.bcbdigital.user_api.model.User", actualFindByCpfResult.getEndereco());
        assertEquals("br.com.bcbdigital.user_api.model.User", actualFindByCpfResult.getEmail());
        assertEquals("1970-01-02", actualFindByCpfResult.getDataCadastro().toString());
        verify(this.userRepository).findByCpf(any());
    }

    @Test
    void testFindByCpfAndKey() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        when(this.userRepository.findByCpfAndKey(any(), any())).thenReturn(user);
        UserDTO actualFindByCpfAndKeyResult = this.userService.findByCpfAndKey("Cpf", "Key");
        assertEquals("Cpf", actualFindByCpfAndKeyResult.getCpf());
        assertTrue(actualFindByCpfAndKeyResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByCpfAndKeyResult.getTelefone());
        assertEquals("Nome", actualFindByCpfAndKeyResult.getNome());
        assertEquals("Key", actualFindByCpfAndKeyResult.getKey());
        assertEquals("Endereco", actualFindByCpfAndKeyResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByCpfAndKeyResult.getEmail());
        assertEquals("1970-01-02", actualFindByCpfAndKeyResult.getDataCadastro().toString());
        verify(this.userRepository).findByCpfAndKey( any(), any());
    }

    @Test
    void testFindByCpfAndKey2() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(null);
        user.setTelefone("Telefone");
        when(this.userRepository.findByCpfAndKey(any(), any())).thenReturn(user);
        UserDTO actualFindByCpfAndKeyResult = this.userService.findByCpfAndKey("Cpf", "Key");
        assertEquals("Cpf", actualFindByCpfAndKeyResult.getCpf());
        assertTrue(actualFindByCpfAndKeyResult.getUsuariosList().isEmpty());
        assertEquals("Telefone", actualFindByCpfAndKeyResult.getTelefone());
        assertEquals("Nome", actualFindByCpfAndKeyResult.getNome());
        assertEquals("Key", actualFindByCpfAndKeyResult.getKey());
        assertEquals("Endereco", actualFindByCpfAndKeyResult.getEndereco());
        assertEquals("jane.doe@example.org", actualFindByCpfAndKeyResult.getEmail());
        assertNull(actualFindByCpfAndKeyResult.getDataCadastro());
        verify(this.userRepository).findByCpfAndKey(any(), any());
    }

    @Test
    void testQueryByName() {
        when(this.userRepository.queryByNomeLike(any())).thenReturn(new ArrayList<>());
        assertTrue(this.userService.queryByName("Name").isEmpty());
        verify(this.userRepository).queryByNomeLike(any());
    }

    @Test
    void testQueryByName2() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userRepository.queryByNomeLike(any())).thenReturn(userList);
        assertEquals(1, this.userService.queryByName("Name").size());
        verify(this.userRepository).queryByNomeLike(any());
    }

    @Test
    void testQueryByName3() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");

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

    @Test
    void testQueryByName4() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(null);
        user.setTelefone("Telefone");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userRepository.queryByNomeLike(any())).thenReturn(userList);
        assertEquals(1, this.userService.queryByName("Name").size());
        verify(this.userRepository).queryByNomeLike(any());
    }
}


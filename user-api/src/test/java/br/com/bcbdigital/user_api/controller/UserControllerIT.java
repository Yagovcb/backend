package br.com.bcbdigital.user_api.controller;

import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.backend.dtos.exceptions.MethodNotAllowedException;
import br.com.bcbdigital.backend.dtos.exceptions.UserNotFoundException;
import br.com.bcbdigital.user_api.UserApiApplication;
import br.com.bcbdigital.user_api.mock.UserMock;
import br.com.bcbdigital.user_api.model.User;
import br.com.bcbdigital.user_api.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = UserApiApplication.class)
@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserControllerIT - Teste de integração da API de usuario")
class UserControllerIT {

    private static final String URI_BASE = "/user/";

    @Autowired
    private MockMvc restUserMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository repository;

    @Test
    @Order(1)
    @DisplayName("Teste de API tenta persistir um usuario")
    void postUserCreateTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();

        restUserMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));

    }

    @Test
    @Order(2)
    @DisplayName("Teste de API tenta recuperar um usuario dado seu ID")
    void getFindByIdTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();
        user.setId(1L);

        restUserMockMvc.perform(get(URI_BASE + "{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    @Order(3)
    @DisplayName("Teste de API tenta recuperar uma lista de usuarios")
    void getListUsersTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();
        restUserMockMvc.perform(get(URI_BASE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].nome").value(user.getNome()))
                .andExpect(jsonPath("$.[*].cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.[*].email").value(user.getEmail()));
    }

    @Test
    @Order(4)
    @DisplayName("Teste de API tenta recuperar um usuario dado seu CPF")
    void getFindByCpfTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();

        restUserMockMvc.perform(get(URI_BASE + "cpf/{cpf}", user.getCpf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    @Order(5)
    @DisplayName("Teste de API tenta recuperar um usuario dado seu CPF e Key")
    void getFindByCpfAndKeyTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();
        User usuario = repository.findByCpf(user.getCpf());
        assertNotNull(usuario);

        restUserMockMvc.perform(get(URI_BASE + "cpfKey/{cpf}", user.getCpf()).queryParam("key", usuario.getKey()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(usuario.getNome()))
                .andExpect(jsonPath("$.cpf").value(usuario.getCpf()))
                .andExpect(jsonPath("$.email").value(usuario.getEmail()));
    }

    @Test
    @Order(6)
    @DisplayName("Teste de API tenta recuperar uma lista usuario dado um nome")
    void getUsuariosByNomeTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();

        restUserMockMvc.perform(get(URI_BASE + "search").queryParam("nome", user.getNome()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].nome").value(user.getNome()))
                .andExpect(jsonPath("$.[*].cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.[*].email").value(user.getEmail()));
    }


    @Test
    @Order(7)
    @DisplayName("Teste de API tenta deletar um usuario, dado seu ID")
    void deleteUsuarioTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();
        User usuario = repository.findByCpf(user.getCpf());
        assertNotNull(usuario);

        restUserMockMvc.perform(delete(URI_BASE + "{id}", usuario.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.mensagem").value("Usuario deletado com sucesso"));
    }

    @Test
    @Order(8)
    @DisplayName("Teste de API tenta simular cenario 'usuario nao encontrado' quando pesquisa por cpf e key. ")
    void usuarioNaoEncontradoFindCpfAndKeyTest_UserNotFoundException() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();

        restUserMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));

        user.setCpf("12345678911");
        restUserMockMvc.perform(get(URI_BASE + "cpfKey/{cpf}", user.getCpf()).queryParam("key", "sniasncasioas"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThrows(UserNotFoundException.class, () -> {throw new UserNotFoundException("Usuario não encontrado");}))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                .andExpect(result -> assertEquals("Usuario não encontrado", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Order(9)
    @ExceptionHandler(value = UserNotFoundException.class)
    @DisplayName("Teste de API tenta simular cenario 'usuario nao encontrado' quando pesquisa por cpf. ")
    void usuarioNaoEncontradoFindCpfTest_UserNotFoundException() throws Exception {
        restUserMockMvc.perform(get(URI_BASE + "cpf/{cpf}", "12345678911"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                .andExpect(result -> assertEquals("Usuario não encontrado", Objects.requireNonNull(result.getResolvedException()).getMessage()));

    }

    @Test
    @Order(10)
    @DisplayName("Teste de API tenta simular cenario 'Usuario já foi persistido na base' quando se tenta persistir")
    void createUsuarioJaPersistido_MethodNotAllowedException() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();

        restUserMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodNotAllowedException))
                .andExpect(result -> assertEquals("Usuario já foi persistido na base", Objects.requireNonNull(result.getResolvedException()).getMessage()));

    }
}

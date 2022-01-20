package br.com.bcbdigital.user_api.repository;

import br.com.bcbdigital.user_api.mock.UserMock;
import br.com.bcbdigital.user_api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Teste da classe de repository UserRepository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        userRepository.save(UserMock.getUsuarioCompletoMock());
    }

    @Test
    @DisplayName("Teste do metodo findByCpf do repository")
    void findByCpfTest() {
        var cpf = "887.177.552-04";

        User usuarioConsulta = userRepository.findByCpf(cpf);

        assertNotNull(usuarioConsulta);
        assertEquals(usuarioConsulta.getCpf(), cpf);
    }

    @Test
    @DisplayName("Teste do metodo findByNome do repository")
    void findByNomeTest() {
        var nome = "Yago";

        List<User> usuarioConsulta = userRepository.queryByNomeLike(nome);

        assertNotNull(usuarioConsulta);
        assertEquals(1, usuarioConsulta.size());
        assertEquals(usuarioConsulta.get(0).getNome(), nome);
    }

    @Test
    @DisplayName("Teste do metodo findAll do repository")
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.asc("nome"),
                Sort.Order.desc("id")
        ));

        Page<User> userPage = userRepository.findAll(pageable);
        assertEquals(1, userPage.getTotalPages());
        assertEquals(1, userPage.getNumberOfElements());
        assertNotNull(userPage);
    }

    @Test
    @DisplayName("Teste do metodo findByCpfAndKey do repository")
    void findByCpfAndKeyTest() {
        var key = "kjasbdcjsabdasjsbnd";
        var cpf = "887.177.552-04";

        User userCpfAndKey = userRepository.findByCpfAndKey(cpf, key);

        assertNotNull(userCpfAndKey);
        assertEquals(cpf, userCpfAndKey.getCpf());
        assertEquals(key, userCpfAndKey.getKey());
    }

}

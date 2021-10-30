package br.com.bcbdigital.user_api.repository;

import br.com.bcbdigital.user_api.mock.UserMock;
import br.com.bcbdigital.user_api.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Before
    public void setup(){
        userRepository.save(UserMock.getUsuarioMock());
    }


    @Test
    public void findByCpfTest() {
        var cpf = "887.177.552-04";

        User usuarioConsulta = userRepository.findByCpf(cpf);

        assertNotNull(usuarioConsulta);
        assertEquals(usuarioConsulta.getCpf(), cpf);
    }


    @Test
    public void findByNomeTest() {
        var nome = "Yago";

        List<User> usuarioConsulta = userRepository.queryByNomeLike(nome);

        assertNotNull(usuarioConsulta);
        assertEquals(1, usuarioConsulta.size());
        assertEquals(usuarioConsulta.get(0).getNome(), nome);
    }

//
//    List<User> queryByNomeLike(String name);
//
//    Page<User> findAll(Pageable pageable);
//
//    User findByCpfAndKey(String cpf, String key);
}

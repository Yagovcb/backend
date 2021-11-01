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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Test
    public void findAllTest() {
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
    public void findByCpfAndKeyTest() {
        var key = "kjasbdcjsabdasjsbnd";
        var cpf = "887.177.552-04";

        User userCpfAndKey = userRepository.findByCpfAndKey(cpf, key);

        assertNotNull(userCpfAndKey);
        assertEquals(cpf, userCpfAndKey.getCpf());
        assertEquals(key, userCpfAndKey.getKey());
    }

}

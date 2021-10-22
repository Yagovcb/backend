package br.com.bcbdigital.user_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void testCanEqual() {
        assertFalse((new User()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        User user = new User();

        User user1 = new User();
        user1.setEndereco("Endereco");
        user1.setEmail("jane.doe@example.org");
        user1.setKey("Key");
        user1.setNome("Nome");
        user1.setCpf("Cpf");
        user1.setId(123L);
        user1.setDataCadastro(LocalDate.ofEpochDay(1L));
        user1.setTelefone("Telefone");
        assertTrue(user.canEqual(user1));
    }

    @Test
    void testConstructor() {
        User actualUser = new User();
        actualUser.setCpf("Cpf");
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualUser.setDataCadastro(ofEpochDayResult);
        actualUser.setEmail("jane.doe@example.org");
        actualUser.setEndereco("Endereco");
        actualUser.setId(123L);
        actualUser.setKey("Key");
        actualUser.setNome("Nome");
        actualUser.setTelefone("Telefone");
        assertEquals("Cpf", actualUser.getCpf());
        assertSame(ofEpochDayResult, actualUser.getDataCadastro());
        assertEquals("jane.doe@example.org", actualUser.getEmail());
        assertEquals("Endereco", actualUser.getEndereco());
        assertEquals(123L, actualUser.getId());
        assertEquals("Key", actualUser.getKey());
        assertEquals("Nome", actualUser.getNome());
        assertEquals("Telefone", actualUser.getTelefone());
        assertEquals("User(id=123, nome=Nome, cpf=Cpf, endereco=Endereco, email=jane.doe@example.org, telefone=Telefone,"
                + " dataCadastro=1970-01-02, key=Key)", actualUser.toString());
    }

    @Test
    void testEquals() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        assertFalse(user.equals(null));
    }

    @Test
    void testEquals2() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        assertFalse(user.equals("Different type to User"));
    }

    @Test
    void testEquals3() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");
        assertTrue(user.equals(user));
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user.hashCode());
    }

    @Test
    void testEquals4() {
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
        assertTrue(user.equals(user1));
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    @Test
    void testEquals5() {
        User user = new User();
        user.setEndereco("Nome");
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals6() {
        User user = new User();
        user.setEndereco(null);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals7() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("Nome");
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals8() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail(null);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals9() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Nome");
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals10() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey(null);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals11() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Cpf");
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals12() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome(null);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals13() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Nome");
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals14() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf(null);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals15() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(0L);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals16() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(0L));
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals17() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(null);
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
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals18() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Nome");

        User user1 = new User();
        user1.setEndereco("Endereco");
        user1.setEmail("jane.doe@example.org");
        user1.setKey("Key");
        user1.setNome("Nome");
        user1.setCpf("Cpf");
        user1.setId(123L);
        user1.setDataCadastro(LocalDate.ofEpochDay(1L));
        user1.setTelefone("Telefone");
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals19() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone(null);

        User user1 = new User();
        user1.setEndereco("Endereco");
        user1.setEmail("jane.doe@example.org");
        user1.setKey("Key");
        user1.setNome("Nome");
        user1.setCpf("Cpf");
        user1.setId(123L);
        user1.setDataCadastro(LocalDate.ofEpochDay(1L));
        user1.setTelefone("Telefone");
        assertFalse(user.equals(user1));
    }

    @Test
    void testEquals20() {
        User user = new User();
        user.setEndereco(null);
        user.setEmail("jane.doe@example.org");
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");

        User user1 = new User();
        user1.setEndereco(null);
        user1.setEmail("jane.doe@example.org");
        user1.setKey("Key");
        user1.setNome("Nome");
        user1.setCpf("Cpf");
        user1.setId(123L);
        user1.setDataCadastro(LocalDate.ofEpochDay(1L));
        user1.setTelefone("Telefone");
        assertTrue(user.equals(user1));
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }

    @Test
    void testEquals21() {
        User user = new User();
        user.setEndereco("Endereco");
        user.setEmail(null);
        user.setKey("Key");
        user.setNome("Nome");
        user.setCpf("Cpf");
        user.setId(123L);
        user.setDataCadastro(LocalDate.ofEpochDay(1L));
        user.setTelefone("Telefone");

        User user1 = new User();
        user1.setEndereco("Endereco");
        user1.setEmail(null);
        user1.setKey("Key");
        user1.setNome("Nome");
        user1.setCpf("Cpf");
        user1.setId(123L);
        user1.setDataCadastro(LocalDate.ofEpochDay(1L));
        user1.setTelefone("Telefone");
        assertTrue(user.equals(user1));
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }
}


package br.com.bcbdigital.user_api.mock;

import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.user_api.model.User;
import lombok.Data;

import java.time.LocalDate;

public class UserMock {

    public static User getUsuarioMock() {

        User user = new User();
        user.setId(1);
        user.setNome("Yago");
        user.setEmail("yago.vcb@gmail.com");
        user.setCpf("887.177.552-04");
        user.setEndereco("Travessa 14 de abril");
        user.setTelefone("999999999999");
        user.setDataCadastro(LocalDate.now());
        user.setKey("kjasbdcjsabdasjsbnd");

        return user;
    }

    public static UserDTO getUsuarioDTOMock() {

        UserDTO user = new UserDTO();
        user.setNome("Yago");
        user.setEmail("yago.vcb@gmail.com");
        user.setCpf("887.177.552-04");
        user.setEndereco("Travessa 14 de abril");
        user.setTelefone("999999999999");
        user.setDataCadastro(LocalDate.now());
        user.setKey("kjasbdcjsabdasjsbnd");

        return user;
    }

}

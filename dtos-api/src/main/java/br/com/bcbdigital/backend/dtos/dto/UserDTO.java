package br.com.bcbdigital.backend.dtos.dto;

import lombok.Data;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


/**
 * Classe DTO para padronização das requisições REST
 * quando houvesse tivesse a necessidade de usar a entidade User
 * <p>
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Data
public class UserDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;
    private String key;


    public static List<UserDTO> usuariosList = new ArrayList<>();

    @PostConstruct
    public void initiateList() {

        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua a");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("91412-3454");
        userDTO.setDataCadastro(LocalDate.now());
        userDTO.setKey(UUID.randomUUID().toString());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua b");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("91234-3454");
        userDTO2.setDataCadastro(LocalDate.now());
        userDTO2.setKey(UUID.randomUUID().toString());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua c");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("98354-3454");
        userDTO3.setDataCadastro(LocalDate.now());
        userDTO3.setKey(UUID.randomUUID().toString());

        usuariosList.add(userDTO);
        usuariosList.add(userDTO2);
        usuariosList.add(userDTO3);
    }

    public Collection<Object> getUsuariosList() {
        return getUsuariosList();
    }
}

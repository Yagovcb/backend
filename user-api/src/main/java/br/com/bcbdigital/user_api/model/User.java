package br.com.bcbdigital.user_api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *  Classe da entidade {@link User}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDate dataCadastro;
    private String key;
}

package br.com.bcbdigital.product_api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Classe da entidade {@link Category}
 * <p>
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

}

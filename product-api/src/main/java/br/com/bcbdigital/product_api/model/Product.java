package br.com.bcbdigital.product_api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Classe da entidade {@link Product}
 * <p>
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private Float preco;
    private String descricao;
    private String productIdentifier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

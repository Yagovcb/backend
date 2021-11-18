package br.com.bcbdigital.product_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * Método construtor de apoio ao metodo do repositorio getProductByCategory
     *
     * */
    public Product(String nome, Float preco, String descricao, String productIdentifier, Category category) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.productIdentifier = productIdentifier;
        this.category = category;
    }
}

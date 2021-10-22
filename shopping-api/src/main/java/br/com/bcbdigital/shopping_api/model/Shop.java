package br.com.bcbdigital.shopping_api.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe da entidade {@link Shop}
 *
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userIdentifier;
    private float total;
    private LocalDate date;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;
}

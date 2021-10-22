package br.com.bcbdigital.shopping_api.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 * Classe da entidade {@link Item}
 *
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Data
@Embeddable
@Table(name = "item")
public class Item {
    private	String	productIdentifier;
    private	Float	price;
}

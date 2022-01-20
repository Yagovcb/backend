package br.com.bcbdigital.shopping_api.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Classe da entidade {@link ShopReport}
 *
 * Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 */
@Data
@Entity
@Table(name = "shop_report")
@RequiredArgsConstructor
public class ShopReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private	Integer	count;
    private	Double	total;
    private	Double	media;

    /**
     * Construtor padrão para uso de teste
     * */
    public ShopReport(Long count, Double total, Double media) {
        this.count = count.intValue();
        this.total = total;
        this.media = media;
    }
}

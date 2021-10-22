package br.com.bcbdigital.shopping_api.repository;

import br.com.bcbdigital.shopping_api.model.Shop;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *  Classe Repository da entidade {@link Shop}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Primary
@Repository
public interface ShopRepository extends JpaRepository<Shop,	Long>, ReportRepository {

    List<Shop> findAllByUserIdentifier(String userIdentifier);
    List<Shop> findAllByTotalGreaterThan(Float total);
    List<Shop> findAllByDateGreaterThan(LocalDate date);

}

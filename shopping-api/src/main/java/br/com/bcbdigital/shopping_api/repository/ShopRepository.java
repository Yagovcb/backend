package br.com.bcbdigital.shopping_api.repository;

import br.com.bcbdigital.shopping_api.model.Shop;
import br.com.bcbdigital.shopping_api.model.ShopReport;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
public interface ShopRepository extends JpaRepository<Shop,	Long>{

    List<Shop> findAllByUserIdentifier(String userIdentifier);
    List<Shop> findAllByTotalGreaterThan(Float total);
    List<Shop> findAllByDateGreaterThan(LocalDate date);

    @Query(value = "SELECT s FROM Shop s where (s.date between :dataInicio and :dataFim) and s.total >= :valorMinimo")
    List<Shop> getShopByFilters(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim, @Param("valorMinimo") float valorMinimo);

    @Query(value = "select new ShopReport(count(sp.id), sum(sp.total), avg(sp.total)) FROM Shop sp where (sp.date between :dataInicio and :dataFim)")
    ShopReport getReportByDate(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}

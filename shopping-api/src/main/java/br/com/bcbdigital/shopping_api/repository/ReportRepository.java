package br.com.bcbdigital.shopping_api.repository;

import br.com.bcbdigital.backend.dtos.dto.ShopReportDTO;
import br.com.bcbdigital.shopping_api.model.Shop;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 *  Classe Repository para montar dados de relatorio
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Repository
public interface ReportRepository {


    List<Shop> getShopByFilters(LocalDate dataInicio, LocalDate	dataFim, Float	valorMinimo);

    ShopReportDTO getReportByDate(LocalDate	dataInicio, LocalDate dataFim);

}

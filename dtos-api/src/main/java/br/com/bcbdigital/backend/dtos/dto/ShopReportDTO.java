package br.com.bcbdigital.backend.dtos.dto;

import lombok.Data;

/**
 *  Classe DTO para padronização das requisições REST
 *  quando houvesse tivesse a necessidade de usar a entidade ShopReport
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Data
public class ShopReportDTO {

    private	Integer	count;
    private	Double	total;
    private	Double	mean;

}

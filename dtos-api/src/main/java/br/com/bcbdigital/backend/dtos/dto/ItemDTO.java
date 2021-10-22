package br.com.bcbdigital.backend.dtos.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  Classe DTO para padronização das requisições REST
 *  quando houvesse tivesse a necessidade de usar a entidade Item
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Data
public class ItemDTO {

    @NotBlank
    private	String	productIdentifier;
    @NotNull
    private	Float	price;

}

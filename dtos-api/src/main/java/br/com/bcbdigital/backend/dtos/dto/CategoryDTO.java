package br.com.bcbdigital.backend.dtos.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *  Classe DTO para padronização das requisições REST
 *  quando houvesse tivesse a necessidade de usar a entidade Category
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Data
public class CategoryDTO {

    @NotNull
    private	Long	id;
    private	String	nome;

}

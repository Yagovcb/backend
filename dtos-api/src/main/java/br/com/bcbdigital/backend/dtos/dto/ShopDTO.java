package br.com.bcbdigital.backend.dtos.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 *  Classe DTO para padronização das requisições REST
 *  quando houvesse tivesse a necessidade de usar a entidade Shop
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Data
public class ShopDTO {

    @NotBlank
    private	String	userIdentifier;

    @NotNull
    private	Float	total;

    @NotNull
    private LocalDate date;

    @NotNull
    private List<ItemDTO> items;

}

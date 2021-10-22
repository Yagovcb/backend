package br.com.bcbdigital.backend.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 *  Classe DTO para padronização das Respostas das requisições REST quando houvesse {@link Exception}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalheRespostaDTO {

    private String mensagem;
    private int status;
    private LocalDate data;
}
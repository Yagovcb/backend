package br.com.bcbdigital.backend.dtos.exceptions;

/**
 *  Classe que faz o apontamento para a {@link Exception} personalizada {@link MethodNotAllowedException}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
public class MethodNotAllowedException extends RuntimeException{

    public MethodNotAllowedException(String message) {
        super(message);
    }
}

package br.com.bcbdigital.backend.dtos.exceptions;

/**
 *  Classe que faz o apontamento para a {@link Exception} personalizada {@link UserNotFoundException}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
public class UserNotFoundException extends RuntimeException{
    /**
     * Método que chama o construtor padrão de RuntimeException
     * */
    public UserNotFoundException(String message) {
        super(message);
    }
}

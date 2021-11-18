package br.com.bcbdigital.backend.dtos.exceptions;

/**
 *  Classe que faz o apontamento para a {@link Exception} personalizada {@link CategoryNotFoundException}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
public class CategoryNotFoundException extends RuntimeException {
    /**
     * Método que chama o construtor padrão de RuntimeException
     * */
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

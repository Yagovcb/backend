package br.com.bcbdigital.backend.dtos.exceptions;

/**
 *  Classe que faz o apontamento para a {@link Exception} personalizada {@link ProductNotFoundException}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
public class ProductNotFoundException extends RuntimeException {

    /**
     * Método que chama o construtor padrão de RuntimeException
     * */
    public ProductNotFoundException() {
        super();
    }
}

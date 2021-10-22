package br.com.bcbdigital.shopping_api.service;

import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *  {@link Service} controller para gerenciar a requisição para o microsserviço de {@link br.com.bcbdigital.product_api}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Service
public class ProductService {

    /**
     * Método responsavel fazer a consulta para o microsserviço de {@link br.com.bcbdigital.product_api},
     * e retorna o {@link ProductDTO} pesquisado
     *
     * @param productIdentifier do {@link UserDTO}
     *
     * @throws ProductNotFoundException se o {@link ProductDTO} não for encontrado
     *
     * @return um {@link ProductDTO} que fora pesquisado no microsserviço de {@link br.com.bcbdigital.product_api}
     * */
    public ProductDTO getProductByIdentifier(String productIdentifier) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8081/product/" + productIdentifier;

            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

            return response.getBody();
        } catch (HttpClientErrorException.NotFound	e) {
            throw new ProductNotFoundException();
        }
    }
}

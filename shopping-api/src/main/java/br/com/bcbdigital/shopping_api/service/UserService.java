package br.com.bcbdigital.shopping_api.service;

import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.backend.dtos.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *  {@link Service} controller para gerenciar a requisição para o microsserviço de {@link br.com.bcbdigital.user_api}
 *
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Service
public class UserService {

    @Autowired
    private RestTemplate client;

    /**
     * Método responsavel fazer a consulta para o microsserviço de {@link br.com.bcbdigital.user_api},
     * e retorna o {@link UserDTO} pesquisado
     *
     * @param key do {@link UserDTO}
     * @param cpf do {@link UserDTO}
     *
     * @throws UserNotFoundException se o {@link UserDTO} não for encontrado
     *
     * @return um {@link UserDTO} que fora pesquisado no microsserviço de {@link br.com.bcbdigital.user_api}
     * */
    public UserDTO getUserByCpf(String cpf, String key) {
        try {

            String url = "http://localhost:8080/user/cpf/" + cpf;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            builder.queryParam("key", key);

            ResponseEntity<UserDTO> response = client.exchange(builder.toUriString(), HttpMethod.GET, null, UserDTO.class);

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException("Usuario não encontrado");
        }
    }
}

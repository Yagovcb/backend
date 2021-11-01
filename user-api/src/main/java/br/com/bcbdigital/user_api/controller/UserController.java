package br.com.bcbdigital.user_api.controller;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.user_api.model.User;
import br.com.bcbdigital.user_api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *  REST controller para gerenciar {@link User}.
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * {@code GET /products} : Rest Endpoint de busca de uma {@link List<UserDTO>} já criada
     * @param pageable paginação informada
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)}} e a entidade {@link List<UserDTO>} criada
     * */
    @ApiOperation(value = "Endpoint de busca de todos os Users cadastrados")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers(@ApiParam Pageable pageable) {
        log.info("UserController: Retornando todos os Usuarios Cadastrados");
        return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
    }

    /**
     * {@code GET /user/{id}} : Rest Endpoint de busca de um {@link UserDTO} pelo id passado
     * @param id passado no URL da requisição
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link UserDTO} criada
     * */
    @ApiOperation(value = "Endpoint de busca de um usuario especifico, dado seu ID")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        log.info("UserController: Buscando usuario do ID - " + id + "...");
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    /**
     * {@code POST /products} : Rest Endpoint de Criação do {@link UserDTO}
     * @param userDTO passado no corpo da requisição
     * @return the {@link ResponseEntity} com o status {@code 201 (CREATED)} e a entidade {@link UserDTO} criada
     * */
    @ApiOperation(value = "Endpoint de criação de um novo usuario")
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        log.info( "UserController: Criando novo produto" + userDTO );
        return new ResponseEntity<>(userService.save(userDTO), HttpStatus.CREATED);
    }

    /**
     * {@code GET /user/cpf/{cpf}} : Rest Endpoint de busca de um {@link UserDTO} pelo id passado
     * @param cpf passado no URL da requisição
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link UserDTO} criada
     * */
    @ApiOperation(value = "Endpoint de busca de um usuario especifico, dado seu CPF")
    @GetMapping(path = "/cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByCpf(@PathVariable String cpf) {
        log.info("UserController: Buscando usuario do CPF - " + cpf + "...");
        return new ResponseEntity<>(userService.findByCpf(cpf), HttpStatus.OK);
    }

    /**
     * {@code GET /user/cpfKey/{cpf}} : Rest Endpoint de busca de um {@link UserDTO} pelo id passado
     * @param cpf passado no URL da requisição
     * @param key passado no URL da requisição
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link UserDTO} criada
     * */
    @ApiOperation(value = "Endpoint de busca de um usuario especifico, dado seu CPF e a chave principal do usuario")
    @GetMapping(path = "/cpfKey/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByCpfAndKey(@RequestParam(name="key") String	key, @PathVariable String cpf) {
        log.info("UserController: Buscando usuario do CPF - " + cpf + " e a chave " + key + "...");
        return new ResponseEntity<>(userService.findByCpfAndKey(cpf, key), HttpStatus.OK);
    }

    /**
     * {@code DELETE /user/{id}} : Rest Endpoint de deleção de um {@link User} dado seu ID
     * @param id passado no URL da requisição
     * @return the {@link ResponseEntity} com o status {@code 201 (OK)} e a entidade {@link DetalheRespostaDTO} criada
     * */
    @ApiOperation(value = "Endpoint de deleção de usuario, dado seu ID")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetalheRespostaDTO> delete(@PathVariable Long id) {
        log.info("UserController: Deletando usuario pelo ID - " + id + "...");
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK);
    }

    /**
     * {@code GET /user/search} : Rest Endpoint de busca personalizada de um {@link UserDTO}
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)}} e a entidade {@link List<UserDTO>} criada
     * */
    @ApiOperation(value = "Endpoint de busca de todos os usuarios cadastrados, dado um nome.")
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsuariosByNome(@RequestParam(name = "nome") String nome) {
        log.info("ProdutosController: Buscando produts conforme parametros...");
        return new ResponseEntity<>(userService.queryByName(nome), HttpStatus.OK);
    }
}

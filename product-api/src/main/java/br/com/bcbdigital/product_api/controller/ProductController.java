package br.com.bcbdigital.product_api.controller;

import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopDTO;
import br.com.bcbdigital.product_api.model.Category;
import br.com.bcbdigital.product_api.model.Product;
import br.com.bcbdigital.product_api.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  REST controller para gerenciar {@link Product}.
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * {@code GET /product} : Rest Endpoint de busca de uma {@link List<ProductDTO>} já criada
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)}} e a entidade {@link List<ProductDTO>} criada
     * */
    @ApiOperation(value = "Endpoint de busca de todos os produtos cadastrados")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        log.info("ProductController: Retornando todas os produtos cadastrados");
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    /**
     * {@code DELETE /product/{id}} : Rest Endpoint de deleção de um {@link ProductDTO} dado seu ID
     * @param id passado no URL da requisição
     * @return the {@link ResponseEntity} com o status {@code 201 (OK)} e a entidade {@link DetalheRespostaDTO} criada
     * */
    @ApiOperation(value = "Endpoint de deleção de produtos, dado seu ID")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetalheRespostaDTO> delete(@PathVariable Long id) {
        log.info("ProductController: Deletando produto pelo ID - " + id + "...");
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

    /**
     * {@code GET /product/{userIdentifier}} : Rest Endpoint de busca de uma {@link ProductDTO}
     * pelo id do {@link Product} passado
     *
     * @param productIdentifier passado no URL da requisição
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link ShopDTO} criada
     * */
    @ApiOperation(value = "Endpoint de busca um produto especifico dado seu ID")
    @GetMapping(path ="/{productIdentifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> findById(@PathVariable String	productIdentifier) {
        log.info("ProductController: Retornando todas os produtos cadastrados pelo ID - " + productIdentifier + "...");
        return new ResponseEntity<>(productService.findByProductIdentifier(productIdentifier), HttpStatus.OK);
    }

    /**
     * {@code GET /product/category/{categoryId}} : Rest Endpoint de busca de uma {@link List<ProductDTO>}
     * pelo id da {@link Category} passado
     *
     * @param categoryId passado no URL da requisição
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link ProductDTO} criada
     * */
    @ApiOperation(value = "Endpoint de busca uma lista de produtos dada sua categoria")
    @GetMapping(path ="/category/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public	ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable	Long categoryId) {
        log.info("ProductController: Retornando todas os produtos pela Categoria de ID - " + categoryId + "...");
        return new ResponseEntity<>(productService.getProductByCategoryId(categoryId), HttpStatus.OK);
    }

    /**
     * {@code POST /products} : Rest Endpoint de Criação do {@link ProductDTO}
     *
     * @param productDTO passado no corpo da requisição
     *
     * @return o {@link ResponseEntity} com o status {@code 201 (CREATED)} e a entidade {@link ShopDTO} criada
     * */
    @ApiOperation(value = "Endpoint de criação de um novo produto")
    @PostMapping(path ="", produces = MediaType.APPLICATION_JSON_VALUE)
    public	ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info( "ProductController: Criando um novo produto" + productDTO );
        return	new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }
}

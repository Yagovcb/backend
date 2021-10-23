package br.com.bcbdigital.shopping_api.controller;

import br.com.bcbdigital.backend.dtos.dto.ShopDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopReportDTO;
import br.com.bcbdigital.shopping_api.model.Shop;
import br.com.bcbdigital.shopping_api.service.ShopService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

/**
 *  REST controller para gerenciar {@link Shop}.
 *  Criado por Yago Castelo Branco
 *
 * @since 21/10/2021
 * */
@Slf4j
@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    /**
     * {@code GET /shopping} : Rest Endpoint de busca de uma {@link List<ShopDTO>} já criada
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)}} e a entidade {@link List<ShopDTO>} criada
     * */
    @Cacheable(value = "listaTodasCompras")
    @ApiOperation(value = "Endpoint de busca de todos as compras cadastrados")
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShopDTO>> getAllShops() {
        log.info("ShopController: Retornando todas as compras cadastrados");
        return new ResponseEntity<>(shopService.getAll(), HttpStatus.OK);
    }

    /**
     * {@code GET /shopping/shopByUser/{userIdentifier}} : Rest Endpoint de busca de uma {@link List<ShopDTO>}
     * pelo id do usuario passado
     *
     * @param userIdentifier passado no URL da requisição
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link ShopDTO} criada
     * */
    @Cacheable(value = "listaTodasComprasPorUsuario")
    @ApiOperation(value = "Endpoint de busca de todas as compras, dado seu ID de usuario")
    @GetMapping(path = "/shopByUser/{userIdentifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShopDTO>> getShopsByUser(@PathVariable String userIdentifier) {
        log.info("ShopController: Retornando todas as compras cadastrados pelo ID do Usuario associado");
        return new ResponseEntity<>(shopService.getByUser(userIdentifier), HttpStatus.OK);
    }

    /**
     * {@code GET /shopping/shopByDate } : Rest Endpoint de busca de uma {@link List<ShopDTO>} ordenada pela data
     *
     * @param shopDTO passado no URL da requisição
     *
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link ShopDTO} criada
     * */
    @Cacheable(value = "listaTodasComprasOrdenandoData")
    @ApiOperation(value = "Endpoint de busca de todas as compras cadastrados ordenada pelas datas das compras")
    @GetMapping(path = "/shopByDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShopDTO>> getShopsByDate(@RequestBody ShopDTO shopDTO) {
        log.info("ShopController: Retornando todas as compras cadastrados ordenada pelas datas das compras");
        return new ResponseEntity<>(shopService.getByDate(shopDTO), HttpStatus.OK);
    }

    /**
     * {@code GET /shopping/{id}} : Rest Endpoint de busca de um {@link ShopDTO} pelo id passado
     * @param id passado no URL da requisição
     * @return the {@link ResponseEntity} com o status {@code 200 (OK)} e a entidade {@link ShopDTO} criada
     * */
    @ApiOperation(value = "Endpoint de busca de um compra especifica, dado seu ID")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopDTO> findById(@PathVariable Long id) {
        log.info("ShopController: Buscando compra do ID - " + id + "...");
        return new ResponseEntity<>(shopService.findById(id), HttpStatus.OK);
    }

    /**
     * {@code POST /shopping} : Rest Endpoint de Criação do {@link ShopDTO}
     *
     * @param shopDTO passado no corpo da requisição
     *
     * @return o {@link ResponseEntity} com o status {@code 201 (CREATED)} e a entidade {@link ShopDTO} criada
     * */
    @CacheEvict(value = {"listaTodasComprasOrdenandoData", "listaTodasComprasPorUsuario", "listaTodasCompras", "listaTodasComprasPorFiltro"},allEntries = true)
    @ApiOperation(value = "Endpoint de criação de um novo usuario")
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopDTO> createShop(@RequestHeader(name="key") String	key,
                                              @Valid @RequestBody ShopDTO shopDTO) {
        log.info( "ShopController: Criando nova compra" + shopDTO );
        return new ResponseEntity<>(shopService.save(shopDTO, key), HttpStatus.CREATED);
    }

    /**
     * {@code GET /shopping/search } : Rest Endpoint de busca para retornar uma {@link List<ShopDTO>} com base no
     * parametros que serão utilizados no filtro
     *
     * @param dataInicio  representa o atributo dataInicio da entidade {@link ShopDTO}, podendo ser null
     * @param dataFim representa o maior valor do atributo dataFim da entidade {@link ShopDTO}, podendo ser null
     * @param valorMinimo  representa o valor do atributo valorMinimo da entidade {@link ShopDTO}, podendo ser null
     *
     * @return uma {@link List<ShopDTO>} todos os produtos que se encaixem no filtro e
     * um {@link ResponseEntity} com o status {@code 200 (OK)}
     * */
    @Cacheable(value = "listaTodasComprasPorFiltro")
    @ApiOperation(value = "Endpoint de busca de todos as compras cadastrados. Dado um filtro passado via QueryParam")
    @GetMapping(path ="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShopDTO>> getShopsByFilter(
            @RequestParam(name = "dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
            @RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
            @RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {
        log.info("ShopController: Buscando compras conforme parametros...");
        return new ResponseEntity<>(shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo), HttpStatus.OK);
    }

    /**
     * {@code GET /shopping/report } : Rest Endpoint de geração de relatorio para retornar uma {@link List<ShopDTO>}
     * com base no parametros que serão utilizados no filtro
     *
     * @param dataInicio  representa o atributo dataInicio da entidade {@link ShopDTO}, podendo ser null
     * @param dataFim representa o maior valor do atributo dataFim da entidade {@link ShopDTO}, podendo ser null
     *
     * @return uma {@link List<ShopDTO>} todos os produtos que se encaixem no filtro e
     * um {@link ResponseEntity} com o status {@code 200 (OK)}
     * */
    @ApiOperation(value = "Endpoint de geração de relatorio. Dado um filtro passado via QueryParam")
    @GetMapping(path ="/report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShopReportDTO> getReportByDate(
            @RequestParam(name = "dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
            @RequestParam(name = "dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {
        log.info("ShopController: Gerando relatorios conforme parametros...");
        return new ResponseEntity<>(shopService.getReportByDate(dataInicio, dataFim), HttpStatus.OK);
    }

}

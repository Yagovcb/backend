package br.com.bcbdigital.shopping_api.controller;

import br.com.bcbdigital.backend.dtos.dto.ShopDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopReportDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.shopping_api.ShoppingApiApplication;
import br.com.bcbdigital.shopping_api.mock.ShopMock;
import br.com.bcbdigital.shopping_api.model.Shop;
import br.com.bcbdigital.shopping_api.repository.ShopRepository;
import br.com.bcbdigital.shopping_api.service.ProductService;
import br.com.bcbdigital.shopping_api.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.engine.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = ShoppingApiApplication.class)
@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("ShopControllerIT - Teste de integração da API de compras")
class ShopControllerIT {

    private static final String URI_BASE = "/shopping/";

    @Autowired
    private MockMvc restShopMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShopRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("Teste de API tenta persistir uma compra")
    void postCreateShopTest() throws Exception {
        ShopDTO dto = ShopMock.getShopDTOMock();

        restShopMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("key", "1")
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.userIdentifier").value(dto.getUserIdentifier()))
                .andExpect(jsonPath("$.date").value(dto.getDate()))
                .andExpect(jsonPath("$.total").value(dto.getTotal()));
    }

    @Test
    @Order(2)
    @DisplayName("Teste de API tenta recuperar todas as compras")
    void getAllShopsTest() throws Exception {
        ShopDTO dto = ShopMock.getShopDTOMock();
        restShopMockMvc.perform(get(URI_BASE)).andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].date").value(dto.getDate()))
                .andExpect(jsonPath("$.[*].total").value(dto.getTotal()));
    }

    @Test
    @Order(3)
    @DisplayName("Teste de API tenta recuperar todas as compras por usuario")
    void getShopsByUserTest() throws Exception {
        ShopDTO dto = ShopMock.getShopDTOMock();
        restShopMockMvc.perform(get(URI_BASE + "shopByUser/{userIdentifier}", dto.getUserIdentifier()))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("Teste de API tenta recuperar todas as compras por data")
    void getShopsByDateTest() throws Exception {
        ShopDTO dto = ShopMock.getShopDTOMock();
        restShopMockMvc.perform(get(URI_BASE + "shopByDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(5)
    @DisplayName("Teste de API tenta recuperar uma compra por dado o Id dela")
    void getFindByIdTest() throws Exception {
        ShopDTO dto = ShopMock.getShopDTOMock();

        restShopMockMvc.perform(get(URI_BASE + "{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.date").value(dto.getDate()))
                .andExpect(jsonPath("$.total").value(dto.getTotal()));
    }


    @Test
    @Order(6)
    @DisplayName("Teste de API tenta recuperar todas as compras dado alguns valores de filtro")
    void getShopsByFilterTest() throws Exception {
        LocalDate dataInicio = LocalDate.of(2021,11,10);
        LocalDate dataFim = LocalDate.of(2021,11,20);
        Float valorMinimo = 10F;
        ShopDTO dto = ShopMock.getShopDTOMock();
        restShopMockMvc.perform(get(URI_BASE + "search")
                        .queryParam("dataInicio", String.valueOf(dataInicio))
                        .queryParam("dataFim", String.valueOf(dataFim))
                        .queryParam("valorMinimo", String.valueOf(valorMinimo))
                )
                .andExpect(status().isOk());
    }

    @Test
    @Order(7)
    @DisplayName("Teste de API tenta recuperar uma entidade de relatorio")
    void getReportByDateTest() throws Exception {
        LocalDate dataInicio = LocalDate.of(2021,11,10);
        LocalDate dataFim = LocalDate.of(2021,11,20);

        ShopReportDTO dto = ShopMock.getShopReportDTOMock();
        restShopMockMvc.perform(get(URI_BASE + "report")
                        .queryParam("dataInicio", String.valueOf(dataInicio))
                        .queryParam("dataFim", String.valueOf(dataFim))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(dto.getCount()))
                .andExpect(jsonPath("$.total").value(dto.getTotal()))
                .andExpect(jsonPath("$.media").value(dto.getMedia()));
    }
}

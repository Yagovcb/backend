package br.com.bcbdigital.product_api.controller;

import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.exceptions.CategoryNotFoundException;
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import br.com.bcbdigital.product_api.ProductApiApplication;
import br.com.bcbdigital.product_api.mock.ProductMock;
import br.com.bcbdigital.product_api.model.Product;
import br.com.bcbdigital.product_api.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest(classes = ProductApiApplication.class)
@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("ProductControllerIT - Teste de integração da API de produtos")
class ProductControllerIT {

    private static final String URI_BASE = "/product/";

    @Autowired
    private MockMvc restProductMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    private void setup() {
        productRepository.deleteAll();
    }

    @Test
    @Order(1)
    @DisplayName("Teste de API tenta persistir um produto")
    void postProductCreateTest() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();

        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.productIdentifier").value(dto.getProductIdentifier()))
                .andExpect(jsonPath("$.preco").value(dto.getPreco()));

    }

    @Test
    @Order(2)
    @DisplayName("Teste de API tenta deletar um produto, dado seu ID")
    void deleteUsuarioTest() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();
        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());


        List<Product> product = productRepository.findAll();
        assertNotNull(product.get(0));

        restProductMockMvc.perform(delete(URI_BASE + "{id}", product.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.mensagem").value("Produto deletado com sucesso"));
    }

    @Test
    @Order(3)
    @DisplayName("Teste de API tenta recuperar um produto dado seu ID")
    void getFindByIdTest() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();
        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        restProductMockMvc.perform(get(URI_BASE + "{productIdentifier}", dto.getProductIdentifier()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.productIdentifier").value(dto.getProductIdentifier()))
                .andExpect(jsonPath("$.preco").value(dto.getPreco()));
    }

    @Test
    @Order(4)
    @DisplayName("Teste de API tenta recuperar um produto dado seu ID forçando erro ProductNotFoundException")
    void getFindByIdTest_erroProductNotFoundException() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();

        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.productIdentifier").value(dto.getProductIdentifier()))
                .andExpect(jsonPath("$.preco").value(dto.getPreco()));

        dto.setProductIdentifier("Teste 123");

        restProductMockMvc.perform(get(URI_BASE + "{productIdentifier}", dto.getProductIdentifier()))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThrows(ProductNotFoundException.class, () -> {
                                    throw new ProductNotFoundException("Produto não encontrado");
                                }
                        )
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("Produto não encontrado", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Order(5)
    @DisplayName("Teste de API tenta obter todos os produtos")
    void getAllProductsTest() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();
        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        restProductMockMvc.perform(get(URI_BASE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].nome").value(dto.getNome()))
                .andExpect(jsonPath("$.[*].productIdentifier").value(dto.getProductIdentifier()));
    }

    @Test
    @Order(6)
    @DisplayName("Teste de API tenta obter todos os produtos pela categoria informada")
    void getProductByCategoryTest() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();
        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        restProductMockMvc.perform(get(URI_BASE + "category/{categoryId}", dto.getCategory().getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].nome").value(dto.getNome()));
    }

    @Test
    @Order(7)
    @DisplayName("Teste de API tenta persistir um produto forçando o erro CategoryNotFoundException")
    void postCreateProductTest_errorCategoryNotFoundException() throws Exception {
        ProductDTO dto = ProductMock.getProductDTOMock();
        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        dto.getCategory().setId(15L);
        restProductMockMvc.perform(post(URI_BASE).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThrows(CategoryNotFoundException.class, () -> {
                                    throw new CategoryNotFoundException("Categoria não encontrada");
                                }
                        )
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CategoryNotFoundException))
                .andExpect(result -> assertEquals("Categoria não encontrada", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    @Order(8)
    @DisplayName("Teste de API tenta deletar um produto, dado seu ID forçando o erro ProductNotFoundException")
    void deleteUsuarioTest_erorProductNotFoundException() throws Exception {

        restProductMockMvc.perform(delete(URI_BASE + "{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertThrows(ProductNotFoundException.class, () -> {
                                    throw new ProductNotFoundException("Produto não encontrado");
                                }
                        )
                )
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductNotFoundException))
                .andExpect(result -> assertEquals("Produto não encontrado", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }
}

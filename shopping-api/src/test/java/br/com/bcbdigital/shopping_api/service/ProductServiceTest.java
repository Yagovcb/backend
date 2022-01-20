package br.com.bcbdigital.shopping_api.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import br.com.bcbdigital.backend.dtos.dto.CategoryDTO;
import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {ProductService.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("ProductService - Classe de teste unitario")
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetProductByIdentifier() throws RestClientException {
        when(this.restTemplate.exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.productService.getProductByIdentifier("42"));
        verify(this.restTemplate).exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testGetProductByIdentifier2() throws RestClientException {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        productDTO.setDescricao("Descricao");
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(productDTO, HttpStatus.CONTINUE);

        when(this.restTemplate.exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(responseEntity);
        assertSame(productDTO, this.productService.getProductByIdentifier("42"));
        verify(this.restTemplate).exchange( any(), any(), any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testGetProductByIdentifier3() throws RestClientException {
        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenThrow(new ProductNotFoundException("Produto não encontrado"));
        when(this.restTemplate.exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(responseEntity);
        assertThrows(ProductNotFoundException.class, () -> this.productService.getProductByIdentifier("42"));
        verify(this.restTemplate).exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any());
        verify(responseEntity).getBody();
    }
}


package br.com.bcbdigital.product_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.bcbdigital.backend.dtos.dto.CategoryDTO;
import br.com.bcbdigital.backend.dtos.dto.DetalheRespostaDTO;
import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.exceptions.CategoryNotFoundException;
import br.com.bcbdigital.backend.dtos.exceptions.MethodNotAllowedException;
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import br.com.bcbdigital.product_api.mock.CategoryMock;
import br.com.bcbdigital.product_api.mock.ProductMock;
import br.com.bcbdigital.product_api.model.Category;
import br.com.bcbdigital.product_api.model.Product;
import br.com.bcbdigital.product_api.repository.CategoryRepository;
import br.com.bcbdigital.product_api.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductService.class})
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("ProductService - Classe de teste unitario")
class ProductServiceTest {

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    @Order(1)
    @DisplayName("Teste de serviço que tenta recuperar todos os produtos")
    void testGetAll() {
        when(this.productRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.productService.getAll().isEmpty());
        verify(this.productRepository).findAll();
    }

    @Test
    @Order(2)
    @DisplayName("Teste de serviço que tenta recuperar uma lista de produtos dada o ID da categoria")
    void testGetProductByCategoryId() {
        Product product = ProductMock.getProductMock();

        when(this.productRepository.getProductByCategory(anyLong())).thenReturn(new ArrayList<>());
        List<ProductDTO> actualProductByCategoryId = this.productService.getProductByCategoryId(product.getCategory().getId());
        assertTrue(actualProductByCategoryId.isEmpty());
        verify(this.productRepository).getProductByCategory(anyLong());
        assertEquals(actualProductByCategoryId, this.productService.getAll());
    }

    @Test
    @Order(3)
    @DisplayName("Teste de serviço que tenta salvar um Produto")
    void testSave() {
        Product product = ProductMock.getProductMock();

        when(this.productRepository.save(any())).thenReturn(product);
        when(this.categoryRepository.existsById(any())).thenReturn(true);

        CategoryDTO categoryDTO = CategoryMock.getCategoryDTOMock();
        ProductDTO productDTO = ProductMock.getProductDTOMock();

        ProductDTO actualSaveResult = this.productService.save(productDTO);
        CategoryDTO ctualSaveCategory = actualSaveResult.getCategory();

        assertEquals(productDTO.getNome(), actualSaveResult.getNome());
        assertEquals(productDTO.getProductIdentifier(), actualSaveResult.getProductIdentifier());
        assertEquals(productDTO.getPreco(), actualSaveResult.getPreco().floatValue());
        assertEquals(categoryDTO.getNome(), ctualSaveCategory.getNome());

        verify(this.productRepository).save(any());
        verify(this.categoryRepository).existsById(any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    @Order(4)
    @DisplayName("Teste de serviço que tenta salvar um Produto forçando o erro CategoryNotFoundException")
    void testSave_CategoryNotFoundException() {
        Product product = ProductMock.getProductMock();
        when(this.productRepository.save(any())).thenReturn(product);
        ProductDTO dto = modelMapper.map(product, ProductDTO.class);
        assertThrows(CategoryNotFoundException.class, () -> this.productService.save(dto));
    }

    @Test
    @Order(5)
    @DisplayName("Teste de serviço que tenta buscar um produto dado seu identificador")
    void testFindByProductIdentifier() {
        Category category = CategoryMock.getCategoryMock();
        Product product = ProductMock.getProductMock();

        when(this.productRepository.findByProductIdentifier(any())).thenReturn(product);
        ProductDTO actualFindByProductIdentifierResult = this.productService.findByProductIdentifier(product.getProductIdentifier());

        assertEquals(product.getNome(), actualFindByProductIdentifierResult.getNome());
        assertEquals(product.getProductIdentifier(), actualFindByProductIdentifierResult.getProductIdentifier());
        assertEquals(product.getPreco(), actualFindByProductIdentifierResult.getPreco().floatValue());

        CategoryDTO category1 = actualFindByProductIdentifierResult.getCategory();
        assertEquals(category.getId(), category1.getId().longValue());
        assertEquals(category.getNome(), category1.getNome());

        verify(this.productRepository).findByProductIdentifier(any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    @Order(6)
    @DisplayName("Teste de serviço que tenta buscar um produto dado seu identificador forçando o erro ProductNotFoundException")
    void testFindByProductIdentifier_ProductNotFoundException() {
        Product product = ProductMock.getProductMock();
        when(this.productRepository.save(any())).thenReturn(product);
        when(this.productRepository.findByProductIdentifier(any())).thenReturn(null);
        assertThrows(ProductNotFoundException.class, () ->
                this.productService.findByProductIdentifier(product.getProductIdentifier())
        );
        verify(this.productRepository).findByProductIdentifier(any());
    }

    @Test
    @Order(7)
    @DisplayName("Teste de serviço que tenta deletar um Produto")
    void testDelete() {
        Product product = ProductMock.getProductMock();

        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(this.productRepository).delete(any());
        when(this.productRepository.findById(any())).thenReturn(ofResult);
        DetalheRespostaDTO actualDeleteResult = this.productService.delete(product.getId());

        assertEquals(200, actualDeleteResult.getStatus());
        assertEquals("Usuario deletado com sucesso", actualDeleteResult.getMensagem());

        verify(this.productRepository).delete(any());
        verify(this.productRepository).findById(any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    @Order(8)
    @DisplayName("Teste de serviço que tenta deletar um Produto forçando erro ProductNotFoundException")
    void testDelete_ProductNotFoundException() {
        Product product = ProductMock.getProductMock();
        when(this.productRepository.save(any())).thenReturn(product);

        when(this.productRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> this.productService.delete(0L));
   }

}


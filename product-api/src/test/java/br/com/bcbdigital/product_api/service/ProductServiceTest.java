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
import br.com.bcbdigital.backend.dtos.exceptions.ProductNotFoundException;
import br.com.bcbdigital.product_api.model.Category;
import br.com.bcbdigital.product_api.model.Product;
import br.com.bcbdigital.product_api.repository.CategoryRepository;
import br.com.bcbdigital.product_api.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void testGetAll() {
        when(this.productRepository.findAll()).thenReturn(new ArrayList<Product>());
        assertTrue(this.productService.getAll().isEmpty());
        verify(this.productRepository).findAll();
    }

    @Test
    void testGetAll2() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(product);
        when(this.productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> actualAll = this.productService.getAll();
        assertEquals(1, actualAll.size());
        ProductDTO getResult = actualAll.get(0);
        assertEquals("ProductDTO(productIdentifier=42, nome=Nome, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                getResult.toString());
        assertEquals("Nome", getResult.getNome());
        assertEquals("42", getResult.getProductIdentifier());
        assertEquals(10.0f, getResult.getPreco().floatValue());
        CategoryDTO category1 = getResult.getCategory();
        assertEquals(123L, category1.getId().longValue());
        assertEquals("Nome", category1.getNome());
        verify(this.productRepository).findAll();
    }

    @Test
    void testGetAll3() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);

        Product product1 = new Product();
        product1.setNome("Nome");
        product1.setPreco(10.0f);
        product1.setCategory(category1);
        product1.setId(123L);
        product1.setProductIdentifier("42");
        product1.setDescricao("Descricao");

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(product1);
        productList.add(product);
        when(this.productRepository.findAll()).thenReturn(productList);
        List<ProductDTO> actualAll = this.productService.getAll();
        assertEquals(2, actualAll.size());
        ProductDTO getResult = actualAll.get(1);
        assertEquals("ProductDTO(productIdentifier=42, nome=Nome, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                getResult.toString());
        assertEquals("42", getResult.getProductIdentifier());
        assertEquals(10.0f, getResult.getPreco().floatValue());
        assertEquals("Nome", getResult.getNome());
        ProductDTO getResult1 = actualAll.get(0);
        assertEquals("42", getResult1.getProductIdentifier());
        CategoryDTO category2 = getResult.getCategory();
        CategoryDTO category3 = getResult1.getCategory();
        assertEquals(category2, category3);
        assertEquals(10.0f, getResult1.getPreco().floatValue());
        assertEquals("Nome", getResult1.getNome());
        assertEquals("Nome", category3.getNome());
        assertEquals(123L, category3.getId().longValue());
        assertEquals(123L, category2.getId().longValue());
        assertEquals("Nome", category2.getNome());
        verify(this.productRepository).findAll();
    }

    @Test
    void testGetProductByCategoryId() {
        when(this.productRepository.getProductByCategory(anyLong())).thenReturn(new ArrayList<Product>());
        List<ProductDTO> actualProductByCategoryId = this.productService.getProductByCategoryId(123L);
        assertTrue(actualProductByCategoryId.isEmpty());
        verify(this.productRepository).getProductByCategory(anyLong());
        assertEquals(actualProductByCategoryId, this.productService.getAll());
    }

    @Test
    void testGetProductByCategoryId2() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(product);
        when(this.productRepository.getProductByCategory(anyLong())).thenReturn(productList);
        List<ProductDTO> actualProductByCategoryId = this.productService.getProductByCategoryId(123L);
        assertEquals(1, actualProductByCategoryId.size());
        ProductDTO getResult = actualProductByCategoryId.get(0);
        assertEquals("ProductDTO(productIdentifier=42, nome=Nome, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                getResult.toString());
        assertEquals("Nome", getResult.getNome());
        assertEquals("42", getResult.getProductIdentifier());
        assertEquals(10.0f, getResult.getPreco().floatValue());
        CategoryDTO category1 = getResult.getCategory();
        assertEquals(123L, category1.getId().longValue());
        assertEquals("Nome", category1.getNome());
        verify(this.productRepository).getProductByCategory(anyLong());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testGetProductByCategoryId3() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);

        Product product1 = new Product();
        product1.setNome("Nome");
        product1.setPreco(10.0f);
        product1.setCategory(category1);
        product1.setId(123L);
        product1.setProductIdentifier("42");
        product1.setDescricao("Descricao");

        ArrayList<Product> productList = new ArrayList<Product>();
        productList.add(product1);
        productList.add(product);
        when(this.productRepository.getProductByCategory(anyLong())).thenReturn(productList);
        List<ProductDTO> actualProductByCategoryId = this.productService.getProductByCategoryId(123L);
        assertEquals(2, actualProductByCategoryId.size());
        ProductDTO getResult = actualProductByCategoryId.get(1);
        assertEquals("ProductDTO(productIdentifier=42, nome=Nome, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                getResult.toString());
        assertEquals("42", getResult.getProductIdentifier());
        assertEquals(10.0f, getResult.getPreco().floatValue());
        assertEquals("Nome", getResult.getNome());
        ProductDTO getResult1 = actualProductByCategoryId.get(0);
        assertEquals("42", getResult1.getProductIdentifier());
        CategoryDTO category2 = getResult.getCategory();
        CategoryDTO category3 = getResult1.getCategory();
        assertEquals(category2, category3);
        assertEquals(10.0f, getResult1.getPreco().floatValue());
        assertEquals("Nome", getResult1.getNome());
        assertEquals("Nome", category3.getNome());
        assertEquals(123L, category3.getId().longValue());
        assertEquals(123L, category2.getId().longValue());
        assertEquals("Nome", category2.getNome());
        verify(this.productRepository).getProductByCategory(anyLong());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testFindByProductIdentifier() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");
        when(this.productRepository.findByProductIdentifier((String) any())).thenReturn(product);
        ProductDTO actualFindByProductIdentifierResult = this.productService.findByProductIdentifier("42");
        assertEquals("ProductDTO(productIdentifier=42, nome=Nome, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                actualFindByProductIdentifierResult.toString());
        assertEquals("Nome", actualFindByProductIdentifierResult.getNome());
        assertEquals("42", actualFindByProductIdentifierResult.getProductIdentifier());
        assertEquals(10.0f, actualFindByProductIdentifierResult.getPreco().floatValue());
        CategoryDTO category1 = actualFindByProductIdentifierResult.getCategory();
        assertEquals(123L, category1.getId().longValue());
        assertEquals("Nome", category1.getNome());
        verify(this.productRepository).findByProductIdentifier((String) any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testFindByProductIdentifier2() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("br.com.bcbdigital.product_api.model.Product");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");
        when(this.productRepository.findByProductIdentifier((String) any())).thenReturn(product);
        ProductDTO actualFindByProductIdentifierResult = this.productService.findByProductIdentifier("42");
        assertEquals("ProductDTO(productIdentifier=42, nome=br.com.bcbdigital.product_api.model.Product, preco=10.0,"
                + " category=CategoryDTO(id=123, nome=Nome))", actualFindByProductIdentifierResult.toString());
        assertEquals("br.com.bcbdigital.product_api.model.Product", actualFindByProductIdentifierResult.getNome());
        assertEquals("42", actualFindByProductIdentifierResult.getProductIdentifier());
        assertEquals(10.0f, actualFindByProductIdentifierResult.getPreco().floatValue());
        CategoryDTO category1 = actualFindByProductIdentifierResult.getCategory();
        assertEquals(123L, category1.getId().longValue());
        assertEquals("Nome", category1.getNome());
        verify(this.productRepository).findByProductIdentifier((String) any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testFindByProductIdentifier3() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("br.com.bcbdigital.product_api.model.Product");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("br.com.bcbdigital.product_api.model.Product");
        product.setDescricao("Descricao");
        when(this.productRepository.findByProductIdentifier((String) any())).thenReturn(product);
        ProductDTO actualFindByProductIdentifierResult = this.productService.findByProductIdentifier("42");
        assertEquals(
                "ProductDTO(productIdentifier=br.com.bcbdigital.product_api.model.Product, nome=br.com.bcbdigital.product"
                        + "_api.model.Product, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                actualFindByProductIdentifierResult.toString());
        assertEquals("br.com.bcbdigital.product_api.model.Product", actualFindByProductIdentifierResult.getNome());
        assertEquals("br.com.bcbdigital.product_api.model.Product",
                actualFindByProductIdentifierResult.getProductIdentifier());
        assertEquals(10.0f, actualFindByProductIdentifierResult.getPreco().floatValue());
        CategoryDTO category1 = actualFindByProductIdentifierResult.getCategory();
        assertEquals(123L, category1.getId().longValue());
        assertEquals("Nome", category1.getNome());
        verify(this.productRepository).findByProductIdentifier((String) any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testSave() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.categoryRepository.existsById((Long) any())).thenReturn(true);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        ProductDTO actualSaveResult = this.productService.save(productDTO);
        CategoryDTO category1 = actualSaveResult.getCategory();
        assertEquals(categoryDTO, category1);
        assertEquals("Nome", actualSaveResult.getNome());
        assertEquals("42", actualSaveResult.getProductIdentifier());
        assertEquals(10.0f, actualSaveResult.getPreco().floatValue());
        assertEquals(123L, category1.getId().longValue());
        assertEquals("Nome", category1.getNome());
        verify(this.productRepository).save((Product) any());
        verify(this.categoryRepository).existsById((Long) any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testSave2() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.categoryRepository.existsById((Long) any())).thenReturn(false);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        assertThrows(CategoryNotFoundException.class, () -> this.productService.save(productDTO));
        verify(this.categoryRepository).existsById((Long) any());
    }

    @Test
    void testDelete() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(10.0f);
        product.setCategory(category);
        product.setId(123L);
        product.setProductIdentifier("42");
        product.setDescricao("Descricao");
        Optional<Product> ofResult = Optional.<Product>of(product);
        doNothing().when(this.productRepository).delete((Product) any());
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        DetalheRespostaDTO actualDeleteResult = this.productService.delete(123L);
        assertEquals(200, actualDeleteResult.getStatus());
        assertEquals("Usuario deletado com sucesso", actualDeleteResult.getMensagem());
        verify(this.productRepository).delete((Product) any());
        verify(this.productRepository).findById((Long) any());
        assertTrue(this.productService.getAll().isEmpty());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.productRepository).delete((Product) any());
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.<Product>empty());
        assertThrows(ProductNotFoundException.class, () -> this.productService.delete(123L));
        verify(this.productRepository).findById((Long) any());
    }
}


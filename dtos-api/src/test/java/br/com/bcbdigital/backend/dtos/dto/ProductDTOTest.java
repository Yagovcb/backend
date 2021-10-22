package br.com.bcbdigital.backend.dtos.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {
    @Test
    void testCanEqual() {
        assertFalse((new ProductDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ProductDTO productDTO = new ProductDTO();

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setCategory(categoryDTO);
        productDTO1.setNome("Nome");
        productDTO1.setPreco(10.0f);
        productDTO1.setProductIdentifier("42");
        assertTrue(productDTO.canEqual(productDTO1));
    }

    @Test
    void testConstructor() {
        ProductDTO actualProductDTO = new ProductDTO();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);
        actualProductDTO.setCategory(categoryDTO);
        actualProductDTO.setNome("Nome");
        actualProductDTO.setPreco(10.0f);
        actualProductDTO.setProductIdentifier("42");
        assertSame(categoryDTO, actualProductDTO.getCategory());
        assertEquals("Nome", actualProductDTO.getNome());
        assertEquals(10.0f, actualProductDTO.getPreco().floatValue());
        assertEquals("42", actualProductDTO.getProductIdentifier());
        assertEquals("ProductDTO(productIdentifier=42, nome=Nome, preco=10.0, category=CategoryDTO(id=123, nome=Nome))",
                actualProductDTO.toString());
    }

    @Test
    void testEquals() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        assertNotEquals(null, productDTO);
    }

    @Test
    void testEquals2() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        assertNotEquals("Different type to ProductDTO", productDTO);
    }

    @Test
    void testEquals3() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        assertEquals(productDTO, productDTO);
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO.hashCode());
    }

    @Test
    void testEquals4() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setCategory(categoryDTO1);
        productDTO1.setNome("Nome");
        productDTO1.setPreco(10.0f);
        productDTO1.setProductIdentifier("42");
        assertEquals(productDTO, productDTO1);
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO1.hashCode());
    }
}


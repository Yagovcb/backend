package br.com.bcbdigital.product_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void testCanEqual() {
        assertFalse((new Product()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        Product product = new Product();

        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product1 = new Product();
        product1.setNome("Nome");
        product1.setPreco(10.0f);
        product1.setCategory(category);
        product1.setId(123L);
        product1.setProductIdentifier("42");
        product1.setDescricao("Descricao");
        assertTrue(product.canEqual(product1));
    }

    @Test
    void testConstructor() {
        Product actualProduct = new Product();
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);
        actualProduct.setCategory(category);
        actualProduct.setDescricao("Descricao");
        actualProduct.setId(123L);
        actualProduct.setNome("Nome");
        actualProduct.setPreco(10.0f);
        actualProduct.setProductIdentifier("42");
        assertSame(category, actualProduct.getCategory());
        assertEquals("Descricao", actualProduct.getDescricao());
        assertEquals(123L, actualProduct.getId());
        assertEquals("Nome", actualProduct.getNome());
        assertEquals(10.0f, actualProduct.getPreco().floatValue());
        assertEquals("42", actualProduct.getProductIdentifier());
        assertEquals(
                "Product(id=123, nome=Nome, preco=10.0, descricao=Descricao, productIdentifier=42, category=Category(id=123,"
                        + " nome=Nome))",
                actualProduct.toString());
    }

    @Test
    void testEquals() {
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
        assertFalse(product.equals(null));
    }

    @Test
    void testEquals2() {
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
        assertFalse(product.equals("Different type to Product"));
    }

    @Test
    void testEquals3() {
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
        assertTrue(product.equals(product));
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product.hashCode());
    }

    @Test
    void testEquals4() {
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
        assertTrue(product.equals(product1));
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product1.hashCode());
    }

    @Test
    void testEquals5() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Descricao");
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
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals6() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome(null);
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
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals7() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(null);
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
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals8() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Product product = new Product();
        product.setNome("Nome");
        product.setPreco(0.5f);
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
        assertFalse(product.equals(product1));
    }
}


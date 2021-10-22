package br.com.bcbdigital.product_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CategoryTest {
    @Test
    void testCanEqual() {
        assertFalse((new Category()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        Category category = new Category();

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);
        assertTrue(category.canEqual(category1));
    }

    @Test
    void testConstructor() {
        Category actualCategory = new Category();
        actualCategory.setId(123L);
        actualCategory.setNome("Nome");
        assertEquals(123L, actualCategory.getId());
        assertEquals("Nome", actualCategory.getNome());
        assertEquals("Category(id=123, nome=Nome)", actualCategory.toString());
    }

    @Test
    void testEquals() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);
        assertFalse(category.equals(null));
    }

    @Test
    void testEquals2() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);
        assertFalse(category.equals("Different type to Category"));
    }

    @Test
    void testEquals3() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);
        assertTrue(category.equals(category));
        int expectedHashCodeResult = category.hashCode();
        assertEquals(expectedHashCodeResult, category.hashCode());
    }

    @Test
    void testEquals4() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(123L);

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);
        assertTrue(category.equals(category1));
        int expectedHashCodeResult = category.hashCode();
        assertEquals(expectedHashCodeResult, category1.hashCode());
    }

    @Test
    void testEquals5() {
        Category category = new Category();
        category.setNome(null);
        category.setId(123L);

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);
        assertFalse(category.equals(category1));
    }

    @Test
    void testEquals6() {
        Category category = new Category();
        category.setNome("br.com.bcbdigital.product_api.model.Category");
        category.setId(123L);

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);
        assertFalse(category.equals(category1));
    }

    @Test
    void testEquals7() {
        Category category = new Category();
        category.setNome("Nome");
        category.setId(0L);

        Category category1 = new Category();
        category1.setNome("Nome");
        category1.setId(123L);
        assertFalse(category.equals(category1));
    }

    @Test
    void testEquals8() {
        Category category = new Category();
        category.setNome(null);
        category.setId(123L);

        Category category1 = new Category();
        category1.setNome(null);
        category1.setId(123L);
        assertTrue(category.equals(category1));
        int expectedHashCodeResult = category.hashCode();
        assertEquals(expectedHashCodeResult, category1.hashCode());
    }
}


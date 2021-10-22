package br.com.bcbdigital.backend.dtos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CategoryDTOTest {
    @Test
    void testCanEqual() {
        assertFalse((new CategoryDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        CategoryDTO categoryDTO = new CategoryDTO();

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);
        assertTrue(categoryDTO.canEqual(categoryDTO1));
    }

    @Test
    void testConstructor() {
        CategoryDTO actualCategoryDTO = new CategoryDTO();
        actualCategoryDTO.setId(123L);
        actualCategoryDTO.setNome("Nome");
        assertEquals(123L, actualCategoryDTO.getId().longValue());
        assertEquals("Nome", actualCategoryDTO.getNome());
        assertEquals("CategoryDTO(id=123, nome=Nome)", actualCategoryDTO.toString());
    }

    @Test
    void testEquals() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);
        assertFalse(categoryDTO.equals(null));
    }

    @Test
    void testEquals2() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);
        assertFalse(categoryDTO.equals("Different type to CategoryDTO"));
    }

    @Test
    void testEquals3() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);
        assertTrue(categoryDTO.equals(categoryDTO));
        int expectedHashCodeResult = categoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, categoryDTO.hashCode());
    }

    @Test
    void testEquals4() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);
        assertTrue(categoryDTO.equals(categoryDTO1));
        int expectedHashCodeResult = categoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, categoryDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome(null);
        categoryDTO.setId(123L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);
        assertFalse(categoryDTO.equals(categoryDTO1));
    }

    @Test
    void testEquals6() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("br.com.bcbdigital.backend.dtos.dto.CategoryDTO");
        categoryDTO.setId(123L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);
        assertFalse(categoryDTO.equals(categoryDTO1));
    }

    @Test
    void testEquals7() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(0L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);
        assertFalse(categoryDTO.equals(categoryDTO1));
    }

    @Test
    void testEquals8() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(null);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(123L);
        assertFalse(categoryDTO.equals(categoryDTO1));
    }

    @Test
    void testEquals9() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome(null);
        categoryDTO.setId(123L);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome(null);
        categoryDTO1.setId(123L);
        assertTrue(categoryDTO.equals(categoryDTO1));
        int expectedHashCodeResult = categoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, categoryDTO1.hashCode());
    }

    @Test
    void testEquals10() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(null);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setNome("Nome");
        categoryDTO1.setId(null);
        assertTrue(categoryDTO.equals(categoryDTO1));
        int expectedHashCodeResult = categoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, categoryDTO1.hashCode());
    }
}


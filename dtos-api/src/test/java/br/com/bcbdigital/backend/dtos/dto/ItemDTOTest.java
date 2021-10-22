package br.com.bcbdigital.backend.dtos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ItemDTOTest {
    @Test
    void testCanEqual() {
        assertFalse((new ItemDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ItemDTO itemDTO = new ItemDTO();

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier("42");
        assertTrue(itemDTO.canEqual(itemDTO1));
    }

    @Test
    void testConstructor() {
        ItemDTO actualItemDTO = new ItemDTO();
        actualItemDTO.setPrice(10.0f);
        actualItemDTO.setProductIdentifier("42");
        assertEquals(10.0f, actualItemDTO.getPrice().floatValue());
        assertEquals("42", actualItemDTO.getProductIdentifier());
        assertEquals("ItemDTO(productIdentifier=42, price=10.0)", actualItemDTO.toString());
    }

    @Test
    void testEquals() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier("42");
        assertFalse(itemDTO.equals(null));
    }

    @Test
    void testEquals2() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier("42");
        assertFalse(itemDTO.equals("Different type to ItemDTO"));
    }

    @Test
    void testEquals3() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier("42");
        assertTrue(itemDTO.equals(itemDTO));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO.hashCode());
    }

    @Test
    void testEquals4() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier("42");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier("42");
        assertTrue(itemDTO.equals(itemDTO1));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(null);
        itemDTO.setProductIdentifier("42");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier("42");
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals6() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(0.5f);
        itemDTO.setProductIdentifier("42");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier("42");
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals7() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier(null);

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier("42");
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals8() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier("Product Identifier");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier("42");
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals9() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(null);
        itemDTO.setProductIdentifier("42");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(null);
        itemDTO1.setProductIdentifier("42");
        assertTrue(itemDTO.equals(itemDTO1));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }

    @Test
    void testEquals10() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier(null);

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setPrice(10.0f);
        itemDTO1.setProductIdentifier(null);
        assertTrue(itemDTO.equals(itemDTO1));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }
}


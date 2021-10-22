package br.com.bcbdigital.shopping_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ItemTest {
    @Test
    void testCanEqual() {
        assertFalse((new Item()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        Item item = new Item();

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier("42");
        assertTrue(item.canEqual(item1));
    }

    @Test
    void testConstructor() {
        Item actualItem = new Item();
        actualItem.setPrice(10.0f);
        actualItem.setProductIdentifier("42");
        assertEquals(10.0f, actualItem.getPrice().floatValue());
        assertEquals("42", actualItem.getProductIdentifier());
        assertEquals("Item(productIdentifier=42, price=10.0)", actualItem.toString());
    }

    @Test
    void testEquals() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier("42");
        assertFalse(item.equals(null));
    }

    @Test
    void testEquals2() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier("42");
        assertFalse(item.equals("Different type to Item"));
    }

    @Test
    void testEquals3() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier("42");
        assertTrue(item.equals(item));
        int expectedHashCodeResult = item.hashCode();
        assertEquals(expectedHashCodeResult, item.hashCode());
    }

    @Test
    void testEquals4() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier("42");

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier("42");
        assertTrue(item.equals(item1));
        int expectedHashCodeResult = item.hashCode();
        assertEquals(expectedHashCodeResult, item1.hashCode());
    }

    @Test
    void testEquals5() {
        Item item = new Item();
        item.setPrice(null);
        item.setProductIdentifier("42");

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier("42");
        assertFalse(item.equals(item1));
    }

    @Test
    void testEquals6() {
        Item item = new Item();
        item.setPrice(0.5f);
        item.setProductIdentifier("42");

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier("42");
        assertFalse(item.equals(item1));
    }

    @Test
    void testEquals7() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier(null);

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier("42");
        assertFalse(item.equals(item1));
    }

    @Test
    void testEquals8() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier("Product Identifier");

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier("42");
        assertFalse(item.equals(item1));
    }

    @Test
    void testEquals9() {
        Item item = new Item();
        item.setPrice(null);
        item.setProductIdentifier("42");

        Item item1 = new Item();
        item1.setPrice(null);
        item1.setProductIdentifier("42");
        assertTrue(item.equals(item1));
        int expectedHashCodeResult = item.hashCode();
        assertEquals(expectedHashCodeResult, item1.hashCode());
    }

    @Test
    void testEquals10() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier(null);

        Item item1 = new Item();
        item1.setPrice(10.0f);
        item1.setProductIdentifier(null);
        assertTrue(item.equals(item1));
        int expectedHashCodeResult = item.hashCode();
        assertEquals(expectedHashCodeResult, item1.hashCode());
    }
}


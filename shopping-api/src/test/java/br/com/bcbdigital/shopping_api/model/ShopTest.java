package br.com.bcbdigital.shopping_api.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ShopTest {
    @Test
    void testCanEqual() {
        assertFalse((new Shop()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        Shop shop = new Shop();

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertTrue(shop.canEqual(shop1));
    }

    @Test
    void testConstructor() {
        Shop actualShop = new Shop();
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualShop.setDate(ofEpochDayResult);
        actualShop.setId(123L);
        ArrayList<Item> itemList = new ArrayList<Item>();
        actualShop.setItems(itemList);
        actualShop.setTotal(10.0f);
        actualShop.setUserIdentifier("42");
        assertSame(ofEpochDayResult, actualShop.getDate());
        assertEquals(123L, actualShop.getId());
        assertSame(itemList, actualShop.getItems());
        assertEquals(10.0f, actualShop.getTotal());
        assertEquals("42", actualShop.getUserIdentifier());
        assertEquals("Shop(id=123, userIdentifier=42, total=10.0, date=1970-01-02, items=[])", actualShop.toString());
    }

    @Test
    void testEquals() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(null));
    }

    @Test
    void testEquals2() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());
        assertFalse(shop.equals("Different type to Shop"));
    }

    @Test
    void testEquals3() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());
        assertTrue(shop.equals(shop));
        int expectedHashCodeResult = shop.hashCode();
        assertEquals(expectedHashCodeResult, shop.hashCode());
    }

    @Test
    void testEquals4() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertTrue(shop.equals(shop1));
        int expectedHashCodeResult = shop.hashCode();
        assertEquals(expectedHashCodeResult, shop1.hashCode());
    }

    @Test
    void testEquals5() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(0L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals6() {
        Shop shop = new Shop();
        shop.setDate(null);
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals7() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier(null);
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals8() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("User Identifier");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals9() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(0L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals10() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(0.5f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals11() {
        Item item = new Item();
        item.setPrice(10.0f);
        item.setProductIdentifier("42");

        ArrayList<Item> itemList = new ArrayList<Item>();
        itemList.add(item);

        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(itemList);

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertFalse(shop.equals(shop1));
    }

    @Test
    void testEquals12() {
        Shop shop = new Shop();
        shop.setDate(null);
        shop.setUserIdentifier("42");
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(null);
        shop1.setUserIdentifier("42");
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertTrue(shop.equals(shop1));
        int expectedHashCodeResult = shop.hashCode();
        assertEquals(expectedHashCodeResult, shop1.hashCode());
    }

    @Test
    void testEquals13() {
        Shop shop = new Shop();
        shop.setDate(LocalDate.ofEpochDay(1L));
        shop.setUserIdentifier(null);
        shop.setId(123L);
        shop.setTotal(10.0f);
        shop.setItems(new ArrayList<Item>());

        Shop shop1 = new Shop();
        shop1.setDate(LocalDate.ofEpochDay(1L));
        shop1.setUserIdentifier(null);
        shop1.setId(123L);
        shop1.setTotal(10.0f);
        shop1.setItems(new ArrayList<Item>());
        assertTrue(shop.equals(shop1));
        int expectedHashCodeResult = shop.hashCode();
        assertEquals(expectedHashCodeResult, shop1.hashCode());
    }
}


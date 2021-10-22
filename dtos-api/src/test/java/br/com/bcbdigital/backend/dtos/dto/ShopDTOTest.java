package br.com.bcbdigital.backend.dtos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ShopDTOTest {
    @Test
    void testCanEqual() {
        assertFalse((new ShopDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ShopDTO shopDTO = new ShopDTO();

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertTrue(shopDTO.canEqual(shopDTO1));
    }

    @Test
    void testConstructor() {
        ShopDTO actualShopDTO = new ShopDTO();
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualShopDTO.setDate(ofEpochDayResult);
        ArrayList<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        actualShopDTO.setItems(itemDTOList);
        actualShopDTO.setTotal(10.0f);
        actualShopDTO.setUserIdentifier("42");
        assertSame(ofEpochDayResult, actualShopDTO.getDate());
        assertSame(itemDTOList, actualShopDTO.getItems());
        assertEquals(10.0f, actualShopDTO.getTotal().floatValue());
        assertEquals("42", actualShopDTO.getUserIdentifier());
        assertEquals("ShopDTO(userIdentifier=42, total=10.0, date=1970-01-02, items=[])", actualShopDTO.toString());
    }

    @Test
    void testEquals() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(null));
    }

    @Test
    void testEquals2() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals("Different type to ShopDTO"));
    }

    @Test
    void testEquals3() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());
        assertTrue(shopDTO.equals(shopDTO));
        int expectedHashCodeResult = shopDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopDTO.hashCode());
    }

    @Test
    void testEquals4() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertTrue(shopDTO.equals(shopDTO1));
        int expectedHashCodeResult = shopDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(0L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals6() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(null);
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals7() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier(null);
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals8() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("User Identifier");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals9() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(null);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals10() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(0.5f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals11() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(10.0f);
        itemDTO.setProductIdentifier("42");

        ArrayList<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        itemDTOList.add(itemDTO);

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(itemDTOList);

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertFalse(shopDTO.equals(shopDTO1));
    }

    @Test
    void testEquals12() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(null);
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(null);
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertTrue(shopDTO.equals(shopDTO1));
        int expectedHashCodeResult = shopDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopDTO1.hashCode());
    }

    @Test
    void testEquals13() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier(null);
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier(null);
        shopDTO1.setTotal(10.0f);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertTrue(shopDTO.equals(shopDTO1));
        int expectedHashCodeResult = shopDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopDTO1.hashCode());
    }

    @Test
    void testEquals14() {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(LocalDate.ofEpochDay(1L));
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(null);
        shopDTO.setItems(new ArrayList<ItemDTO>());

        ShopDTO shopDTO1 = new ShopDTO();
        shopDTO1.setDate(LocalDate.ofEpochDay(1L));
        shopDTO1.setUserIdentifier("42");
        shopDTO1.setTotal(null);
        shopDTO1.setItems(new ArrayList<ItemDTO>());
        assertTrue(shopDTO.equals(shopDTO1));
        int expectedHashCodeResult = shopDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopDTO1.hashCode());
    }
}


package br.com.bcbdigital.backend.dtos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShopReportDTOTest {
    @Test
    void testCanEqual() {
        assertFalse((new ShopReportDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertTrue(shopReportDTO.canEqual(shopReportDTO1));
    }

    @Test
    void testConstructor() {
        ShopReportDTO actualShopReportDTO = new ShopReportDTO();
        actualShopReportDTO.setCount(3);
        actualShopReportDTO.setMedia(10.0);
        actualShopReportDTO.setTotal(10.0);
        assertEquals(3, actualShopReportDTO.getCount().intValue());
        assertEquals(10.0, actualShopReportDTO.getMedia().doubleValue());
        assertEquals(10.0, actualShopReportDTO.getTotal().doubleValue());
        assertEquals("ShopReportDTO(count=3, total=10.0, mean=10.0)", actualShopReportDTO.toString());
    }

    @Test
    void testEquals() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);
        assertFalse(shopReportDTO.equals(null));
    }

    @Test
    void testEquals2() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);
        assertFalse(shopReportDTO.equals("Different type to ShopReportDTO"));
    }

    @Test
    void testEquals3() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);
        assertTrue(shopReportDTO.equals(shopReportDTO));
        int expectedHashCodeResult = shopReportDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopReportDTO.hashCode());
    }

    @Test
    void testEquals4() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertTrue(shopReportDTO.equals(shopReportDTO1));
        int expectedHashCodeResult = shopReportDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopReportDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(null);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertFalse(shopReportDTO.equals(shopReportDTO1));
    }

    @Test
    void testEquals6() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(0.5);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertFalse(shopReportDTO.equals(shopReportDTO1));
    }

    @Test
    void testEquals7() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(null);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertFalse(shopReportDTO.equals(shopReportDTO1));
    }

    @Test
    void testEquals8() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(0.5);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertFalse(shopReportDTO.equals(shopReportDTO1));
    }

    @Test
    void testEquals9() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(0);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertFalse(shopReportDTO.equals(shopReportDTO1));
    }

    @Test
    void testEquals10() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(null);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertFalse(shopReportDTO.equals(shopReportDTO1));
    }

    @Test
    void testEquals11() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(null);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(null);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(3);
        assertTrue(shopReportDTO.equals(shopReportDTO1));
        int expectedHashCodeResult = shopReportDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopReportDTO1.hashCode());
    }

    @Test
    void testEquals12() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(null);
        shopReportDTO.setCount(3);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(null);
        shopReportDTO1.setCount(3);
        assertTrue(shopReportDTO.equals(shopReportDTO1));
        int expectedHashCodeResult = shopReportDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopReportDTO1.hashCode());
    }

    @Test
    void testEquals13() {
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setTotal(10.0);
        shopReportDTO.setMedia(10.0);
        shopReportDTO.setCount(null);

        ShopReportDTO shopReportDTO1 = new ShopReportDTO();
        shopReportDTO1.setTotal(10.0);
        shopReportDTO1.setMedia(10.0);
        shopReportDTO1.setCount(null);
        assertTrue(shopReportDTO.equals(shopReportDTO1));
        int expectedHashCodeResult = shopReportDTO.hashCode();
        assertEquals(expectedHashCodeResult, shopReportDTO1.hashCode());
    }
}


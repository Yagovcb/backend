package br.com.bcbdigital.backend.dtos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DetalheRespostaDTOTest {
    @Test
    void testCanEqual() {
        assertFalse((new DetalheRespostaDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO();
        assertTrue(detalheRespostaDTO.canEqual(new DetalheRespostaDTO()));
    }

    @Test
    void testConstructor() {
        DetalheRespostaDTO actualDetalheRespostaDTO = new DetalheRespostaDTO();
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualDetalheRespostaDTO.setData(ofEpochDayResult);
        actualDetalheRespostaDTO.setMensagem("Mensagem");
        actualDetalheRespostaDTO.setStatus(1);
        assertSame(ofEpochDayResult, actualDetalheRespostaDTO.getData());
        assertEquals("Mensagem", actualDetalheRespostaDTO.getMensagem());
        assertEquals(1, actualDetalheRespostaDTO.getStatus());
        assertEquals("DetalheRespostaDTO(mensagem=Mensagem, status=1, data=1970-01-02)",
                actualDetalheRespostaDTO.toString());
    }

    @Test
    void testConstructor2() {
        DetalheRespostaDTO actualDetalheRespostaDTO = new DetalheRespostaDTO("Mensagem", 1, LocalDate.ofEpochDay(1L));
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualDetalheRespostaDTO.setData(ofEpochDayResult);
        actualDetalheRespostaDTO.setMensagem("Mensagem");
        actualDetalheRespostaDTO.setStatus(1);
        assertSame(ofEpochDayResult, actualDetalheRespostaDTO.getData());
        assertEquals("Mensagem", actualDetalheRespostaDTO.getMensagem());
        assertEquals(1, actualDetalheRespostaDTO.getStatus());
        assertEquals("DetalheRespostaDTO(mensagem=Mensagem, status=1, data=1970-01-02)",
                actualDetalheRespostaDTO.toString());
    }

    @Test
    void testEquals() {
        assertFalse((new DetalheRespostaDTO()).equals(null));
        assertFalse((new DetalheRespostaDTO()).equals("Different type to DetalheRespostaDTO"));
    }

    @Test
    void testEquals2() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO();
        assertTrue(detalheRespostaDTO.equals(detalheRespostaDTO));
        int expectedHashCodeResult = detalheRespostaDTO.hashCode();
        assertEquals(expectedHashCodeResult, detalheRespostaDTO.hashCode());
    }

    @Test
    void testEquals3() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO();
        DetalheRespostaDTO detalheRespostaDTO1 = new DetalheRespostaDTO();
        assertTrue(detalheRespostaDTO.equals(detalheRespostaDTO1));
        int expectedHashCodeResult = detalheRespostaDTO.hashCode();
        assertEquals(expectedHashCodeResult, detalheRespostaDTO1.hashCode());
    }

    @Test
    void testEquals4() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO("Mensagem", 1, LocalDate.ofEpochDay(1L));
        assertFalse(detalheRespostaDTO.equals(new DetalheRespostaDTO()));
    }

    @Test
    void testEquals5() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO("Mensagem", 0, LocalDate.ofEpochDay(1L));
        assertFalse(detalheRespostaDTO.equals(new DetalheRespostaDTO()));
    }

    @Test
    void testEquals6() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO("Mensagem", 1, LocalDate.ofEpochDay(1L));
        DetalheRespostaDTO detalheRespostaDTO1 = new DetalheRespostaDTO("Mensagem", 1, LocalDate.ofEpochDay(1L));

        assertTrue(detalheRespostaDTO.equals(detalheRespostaDTO1));
        int expectedHashCodeResult = detalheRespostaDTO.hashCode();
        assertEquals(expectedHashCodeResult, detalheRespostaDTO1.hashCode());
    }

    @Test
    void testEquals7() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO(null, 0, LocalDate.ofEpochDay(1L));
        assertFalse(detalheRespostaDTO.equals(new DetalheRespostaDTO()));
    }

    @Test
    void testEquals8() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO(null, 1, LocalDate.ofEpochDay(1L));
        assertFalse(detalheRespostaDTO.equals(new DetalheRespostaDTO("Mensagem", 1, LocalDate.ofEpochDay(1L))));
    }

    @Test
    void testEquals9() {
        DetalheRespostaDTO detalheRespostaDTO = new DetalheRespostaDTO("Mensagem", 1, null);
        assertFalse(detalheRespostaDTO.equals(new DetalheRespostaDTO("Mensagem", 1, LocalDate.ofEpochDay(1L))));
    }
}


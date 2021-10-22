package br.com.bcbdigital.backend.dtos.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserDTOTest {
    @Test
    void testInitiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.initiateList();
        assertEquals(3, userDTO.getUsuariosList().size());
    }
}


package br.com.bcbdigital.shopping_api.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.backend.dtos.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {UserService.class, RestTemplate.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserService - Classe de teste unitario")
class UserServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test
    void testGetUserByCpf() throws RestClientException {
        when(this.restTemplate.exchange( any(), any(), any(), (Class<Object>) any(), (Object[]) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        assertNull(this.userService.getUserByCpf("Cpf", "Key"));
        verify(this.restTemplate).exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testGetUserByCpf2() throws RestClientException {
        UserDTO userDTO = new UserDTO();
        when(this.restTemplate.exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(userDTO, HttpStatus.CONTINUE));
        assertSame(userDTO, this.userService.getUserByCpf("Cpf", "Key"));
        verify(this.restTemplate).exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any());
    }

    @Test
    void testGetUserByCpf3() throws RestClientException {
        ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenThrow(new UserNotFoundException("Usuario não encontrado"));
        when(this.restTemplate.exchange(any(), any(), any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(responseEntity);
        assertThrows(UserNotFoundException.class, () -> this.userService.getUserByCpf("Cpf", "Key"));
        verify(this.restTemplate).exchange(any(),any(), any(), (Class<Object>) any(), (Object[]) any());
        verify(responseEntity).getBody();
    }
}


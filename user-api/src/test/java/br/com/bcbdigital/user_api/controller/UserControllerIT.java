package br.com.bcbdigital.user_api.controller;

import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.user_api.UserApiApplication;
import br.com.bcbdigital.user_api.mock.UserMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = UserApiApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
public class UserControllerIT {

    @Autowired
    private MockMvc restUserMockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void postUserCreateTest() throws Exception {
        UserDTO user = UserMock.getUsuarioDTOMock();
        restUserMockMvc.perform(post("/user/").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.nome").value(user.getNome()))
                .andExpect(jsonPath("$.cpf").value(user.getCpf()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));

    }

}

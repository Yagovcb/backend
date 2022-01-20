package br.com.bcbdigital.shopping_api.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import br.com.bcbdigital.backend.dtos.dto.ItemDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopDTO;
import br.com.bcbdigital.shopping_api.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ShopController.class})
@ExtendWith(SpringExtension.class)
class ShopControllerTest {
    @Autowired
    private ShopController shopController;

    @MockBean
    private ShopService shopService;

    @Test
    void testCreateShop() throws Exception {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(null);
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());
        String content = (new ObjectMapper()).writeValueAsString(shopDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/shopping")
                .header("key", "Key")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testFindById() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopping/*");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetAllShops() throws Exception {
        when(this.shopService.getAll()).thenReturn(new ArrayList<ShopDTO>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopping");
        MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllShops2() throws Exception {
        when(this.shopService.getAll()).thenReturn(new ArrayList<ShopDTO>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/shopping");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetReportByDate() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/shopping/report");
        MockHttpServletRequestBuilder paramResult = getResult.param("dataFim", String.valueOf(LocalDate.ofEpochDay(1L)));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("dataInicio",
                String.valueOf(LocalDate.ofEpochDay(1L)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetShopsByDate() throws Exception {
        when(this.shopService.getByDate((ShopDTO) any())).thenReturn(new ArrayList<ShopDTO>());

        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDate(null);
        shopDTO.setUserIdentifier("42");
        shopDTO.setTotal(10.0f);
        shopDTO.setItems(new ArrayList<ItemDTO>());
        String content = (new ObjectMapper()).writeValueAsString(shopDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopping/shopByDate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetShopsByFilter() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/shopping/search");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("dataInicio",
                String.valueOf(LocalDate.ofEpochDay(1L)));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testGetShopsByUser() throws Exception {
        when(this.shopService.getByUser((String) any())).thenReturn(new ArrayList<ShopDTO>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/shopping/shopByUser/*");
        MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetShopsByUser2() throws Exception {
        when(this.shopService.getByUser((String) any())).thenReturn(new ArrayList<ShopDTO>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/shopping/shopByUser/*");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.shopController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}


package br.com.bcbdigital.shopping_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import br.com.bcbdigital.backend.dtos.dto.CategoryDTO;
import br.com.bcbdigital.backend.dtos.dto.ProductDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopDTO;
import br.com.bcbdigital.backend.dtos.dto.ShopReportDTO;
import br.com.bcbdigital.backend.dtos.dto.UserDTO;
import br.com.bcbdigital.shopping_api.mock.ShopMock;
import br.com.bcbdigital.shopping_api.model.Shop;
import br.com.bcbdigital.shopping_api.model.ShopReport;
import br.com.bcbdigital.shopping_api.repository.ShopRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ShopService.class, ProductService.class, UserService.class})
@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("ShopService - Classe de teste unitario")
class ShopServiceTest {

    @Autowired
    private ShopService shopService;

    @MockBean
    private ProductService productService;

    @MockBean
    private ShopRepository shopRepository;

    @MockBean
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("Teste de serviço que tenta recuperar todos as compras")
    void testGetAll() {
        Shop shop = ShopMock.getShopMock();

        ArrayList<Shop> shopList = new ArrayList<>();
        shopList.add(shop);
        when(this.shopRepository.findAll()).thenReturn(shopList);
        List<ShopDTO> actualAll = this.shopService.getAll();
        assertEquals(1, actualAll.size());

        ShopDTO getResult = actualAll.get(0);

        assertEquals(shop.getDate(), getResult.getDate());
        assertEquals(shop.getUserIdentifier(), getResult.getUserIdentifier());
        assertEquals(shop.getTotal(), getResult.getTotal().floatValue());
        verify(this.shopRepository).findAll();
    }

    @Test
    @Order(2)
    @DisplayName("Teste de serviço que tenta recuperar todos as compras por usuario")
    void testGetByUser() {
        Shop shop = ShopMock.getShopMock();

        ArrayList<Shop> shopList = new ArrayList<>();
        shopList.add(shop);
        when(this.shopRepository.findAllByUserIdentifier(any())).thenReturn(shopList);
        List<ShopDTO> actualByUser = this.shopService.getByUser(shop.getUserIdentifier());
        assertEquals(1, actualByUser.size());

        ShopDTO getResult = actualByUser.get(0);
        assertEquals(shop.getDate(), getResult.getDate());
        assertEquals(shop.getUserIdentifier(), getResult.getUserIdentifier());
        assertEquals(shop.getTotal(), getResult.getTotal().floatValue());

        verify(this.shopRepository).findAllByUserIdentifier(any());
    }

    @Test
    @Order(3)
    @DisplayName("Teste de serviço que tenta recuperar todos as compras pela data")
    void testGetByDate() {
        when(this.shopRepository.findAllByDateGreaterThan(any())).thenReturn(new ArrayList<>());

        ShopDTO shopDTO = ShopMock.getShopDTOMock();
        List<ShopDTO> actualByDate = this.shopService.getByDate(shopDTO);

        assertTrue(actualByDate.isEmpty());
        verify(this.shopRepository).findAllByDateGreaterThan(any());
        assertEquals(actualByDate, this.shopService.getAll());
    }

    @Test
    @Order(4)
    @DisplayName("Teste de serviço que tenta recuperar uma compra dado seu ID")
    void testFindById() {
        Shop shop = ShopMock.getShopMock();
        Optional<Shop> ofResult = Optional.of(shop);
        when(this.shopRepository.findById(any())).thenReturn(ofResult);
        ShopDTO actualFindByIdResult = this.shopService.findById(shop.getId());
        assertEquals(shop.getDate(), actualFindByIdResult.getDate());
        assertEquals(shop.getUserIdentifier(), actualFindByIdResult.getUserIdentifier());
        assertEquals(shop.getTotal(), actualFindByIdResult.getTotal());

        verify(this.shopRepository).findById(any());
    }

    @Test
    @Order(5)
    @DisplayName("Teste de serviço que tenta salvar uma compra")
    void testSave() {
        when(this.userService.getUserByCpf(any(), any())).thenReturn(new UserDTO());

        Shop shop = ShopMock.getShopMock();
        when(this.shopRepository.save(any())).thenReturn(shop);

        ShopDTO shopDTO = ShopMock.getShopDTOMock();
        ShopDTO actualSaveResult = this.shopService.createShop(shopDTO, "Key");
        assertEquals(shop.getDate(), actualSaveResult.getDate());
        assertEquals(shop.getUserIdentifier(), actualSaveResult.getUserIdentifier());
        assertEquals(shop.getTotal(), actualSaveResult.getTotal());

        verify(this.userService).getUserByCpf(any(), any());
        verify(this.shopRepository).save(any());
        assertEquals(shopDTO.getTotal(), shopDTO.getTotal());
    }

    @Test
    @Order(6)
    @DisplayName("Teste de serviço que tenta salvar uma compra com melhoria de cobertura 1")
    void testSave2() {
        when(this.userService.getUserByCpf(any(), any())).thenReturn(null);

        Shop shop = ShopMock.getShopMock();
        when(this.shopRepository.save(any())).thenReturn(shop);

        ShopDTO shopDTO = ShopMock.getShopDTOMock();
        assertNull(this.shopService.createShop(shopDTO, "Key"));
        verify(this.userService).getUserByCpf(any(), any());
    }

    @Test
    @Order(7)
    @DisplayName("Teste de serviço que tenta salvar uma compra com melhoria de cobertura 2")
    void testSave3() {
        when(this.userService.getUserByCpf(any(), any())).thenReturn(new UserDTO());

        Shop shop = ShopMock.getShopMock();
        when(this.shopRepository.save(any())).thenReturn(shop);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNome("Nome");
        categoryDTO.setId(123L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        productDTO.setNome("Nome");
        productDTO.setPreco(10.0f);
        productDTO.setProductIdentifier("42");
        productDTO.setDescricao("Descricao");
        when(this.productService.getProductByIdentifier(any())).thenReturn(productDTO);

        ShopDTO shopDTO = ShopMock.getShopDTOMock();

        ShopDTO actualSaveResult = this.shopService.createShop(shopDTO, "Key");

        assertEquals(shopDTO.getDate(), actualSaveResult.getDate());
        assertEquals(shop.getUserIdentifier(), actualSaveResult.getUserIdentifier());
        assertEquals(shop.getTotal(), actualSaveResult.getTotal());

        verify(this.userService).getUserByCpf(any(), any());
        verify(this.shopRepository).save(any());
        verify(this.productService).getProductByIdentifier(any());
        assertEquals(shopDTO.getTotal(), shopDTO.getTotal());
    }


    @Test
    @Order(8)
    @DisplayName("Teste de serviço que tenta recuperar uma lista de compras por um filtro")
    void testGetShopsByFilter() {
        when(this.shopRepository.getShopByFilters(any(), any(), anyFloat())).thenReturn(new ArrayList<>());
        List<ShopDTO> actualShopsByFilter = this.shopService.getShopsByFilter(LocalDate.ofEpochDay(1L),
                LocalDate.ofEpochDay(1L), 10.0f);
        assertTrue(actualShopsByFilter.isEmpty());
        verify(this.shopRepository).getShopByFilters(any(), any(), anyFloat());
        assertEquals(actualShopsByFilter, this.shopService.getAll());
    }


    @Test
    @Order(9)
    @DisplayName("Teste de serviço que tenta recuperar um relatorio de compras")
    void testGetReportByDate() {
        ShopReport shopReport = new ShopReport();
        shopReport.setTotal(10.0);
        shopReport.setId(123L);
        shopReport.setMedia(10.0);
        shopReport.setCount(3);
        when(this.shopRepository.getReportByDate(any(), any())).thenReturn(shopReport);

        ShopReportDTO actualReportByDate = this.shopService.getReportByDate(LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L));
        assertEquals(shopReport.getCount(), actualReportByDate.getCount());
        assertEquals(shopReport.getTotal(), actualReportByDate.getTotal());
        assertEquals(shopReport.getMedia(), actualReportByDate.getMedia());
        verify(this.shopRepository).getReportByDate(any(), any());
        assertTrue(this.shopService.getAll().isEmpty());
    }

}

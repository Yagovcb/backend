package br.com.bcbdigital.shopping_api.repository;

import br.com.bcbdigital.shopping_api.mock.ShopMock;
import br.com.bcbdigital.shopping_api.model.Shop;
import br.com.bcbdigital.shopping_api.model.ShopReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Teste da classe de repository ReportRepositoryImpl")
class ReportRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @BeforeEach
    void setup(){
        shopRepository.save(ShopMock.getShopMock());

        Shop shop2 = ShopMock.getShopMock();
        shop2.setId(2);
        shop2.setDate(LocalDate.of(2021,11,17));
        shopRepository.save(shop2);
    }

    @Test
    @DisplayName("Teste do metodo getShopByFilters do repository")
    void getShopByFiltersTest(){
        var dataInicio = LocalDate.of(2021,11,16);
        var datafim = LocalDate.now();
        var valor = 80F;

        List<Shop> shopByFilters = shopRepository.getShopByFilters(dataInicio, datafim, valor);
        assertNotNull(shopByFilters);
        assertEquals(2, shopByFilters.size());
        assertEquals(ShopMock.getShopMock().getTotal(), shopByFilters.get(0).getTotal());
        assertEquals(ShopMock.getShopMock().getUserIdentifier(), shopByFilters.get(0).getUserIdentifier());
    }

    @Test
    @DisplayName("Teste do metodo getReportByDate do repository")
    void getReportByDateTest(){
        var dataInicio = LocalDate.of(2021,11,16);
        var datafim = LocalDate.now();

        ShopReport reportByDate = shopRepository.getReportByDate(dataInicio, datafim);
        assertNotNull(reportByDate);
        assertEquals(2, reportByDate.getCount());
    }
}

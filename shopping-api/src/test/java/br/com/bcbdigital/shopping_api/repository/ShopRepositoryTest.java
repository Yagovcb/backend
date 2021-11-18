package br.com.bcbdigital.shopping_api.repository;

import br.com.bcbdigital.shopping_api.mock.ShopMock;
import br.com.bcbdigital.shopping_api.model.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Teste da classe de repository ShopRepository")
class ShopRepositoryTest {

    @Autowired
    private ShopRepository repository;

    @BeforeEach
    void setup(){
       repository.save(ShopMock.getShopMock());
    }

    @Test
    @DisplayName("Teste do metodo findAllByUserIdentifier do repository")
    void findAllByUserIdentifierTest(){
        var userIdentifier = ShopMock.getShopDTOMock().getUserIdentifier();

        List<Shop> allByUserIdentifier = repository.findAllByUserIdentifier(userIdentifier);
        assertNotNull(allByUserIdentifier);
        assertEquals(1, allByUserIdentifier.size());
        assertEquals(allByUserIdentifier.get(0).getUserIdentifier(), userIdentifier);
    }

    @Test
    @DisplayName("Teste do metodo findAllByTotalGreaterThan do repository")
    void findAllByTotalGreaterThanTest(){
        var total = 100F;

        List<Shop> allByTotalGreaterThan = repository.findAllByTotalGreaterThan(total);
        assertNotNull(allByTotalGreaterThan);
        assertEquals(1, allByTotalGreaterThan.size());
        assertTrue(allByTotalGreaterThan.get(0).getTotal() >  total);
    }

    @Test
    @DisplayName("Teste do metodo findAllByDateGreaterThan do repository")
    void findAllByDateGreaterThanTest(){
        var oldDate = LocalDate.of(2021,11,17);


        List<Shop> allByDateGreaterThan = repository.findAllByDateGreaterThan(oldDate);
        assertNotNull(allByDateGreaterThan);
        assertEquals(1, allByDateGreaterThan.size());
        assertTrue(allByDateGreaterThan.get(0).getDate().isAfter(oldDate));
    }
}

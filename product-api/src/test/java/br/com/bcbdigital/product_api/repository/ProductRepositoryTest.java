package br.com.bcbdigital.product_api.repository;

import br.com.bcbdigital.product_api.mock.CategoryMock;
import br.com.bcbdigital.product_api.mock.ProductMock;
import br.com.bcbdigital.product_api.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Teste da classe de repository ProductRepository")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setup(){
        productRepository.save(ProductMock.getProductMock());
    }

    @Test
    @DisplayName("Teste do metodo getProductByCategory do repository")
    void getProductByCategoryTest(){
        var categoriaId = CategoryMock.getCategoryMock().getId();

        List<Product> productByCategory = productRepository.getProductByCategory(categoriaId);
        assertNotNull(productByCategory);
        assertEquals(1, productByCategory.size());
        assertEquals(productByCategory.get(0).getCategory().getId(), categoriaId);
    }

    @Test
    @DisplayName("Teste do metodo findByProductIdentifier do repository")
    void findByProductIdentifierTest(){
        var productIdentifier = ProductMock.getProductMock().getProductIdentifier();

        Product identifier = productRepository.findByProductIdentifier(productIdentifier);
        assertNotNull(identifier);
        assertEquals(identifier.getProductIdentifier(), productIdentifier);
    }
}

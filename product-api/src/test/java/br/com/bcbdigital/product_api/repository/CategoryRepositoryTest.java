package br.com.bcbdigital.product_api.repository;

import br.com.bcbdigital.product_api.mock.CategoryMock;
import br.com.bcbdigital.product_api.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(value = "test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setup(){
        categoryRepository.save(CategoryMock.getCategoryMock());
    }

    @Test
    void findByNomeTest() {
        long id = 1;

        Optional<Category> usuarioConsulta = categoryRepository.findById(id);
        assertTrue(usuarioConsulta.isPresent());
        assertEquals(CategoryMock.getCategoryMock(), usuarioConsulta.get());
    }
}

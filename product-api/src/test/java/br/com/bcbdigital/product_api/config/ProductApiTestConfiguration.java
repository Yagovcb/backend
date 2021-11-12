package br.com.bcbdigital.product_api.config;

import br.com.bcbdigital.product_api.repository.CategoryRepository;
import br.com.bcbdigital.product_api.repository.ProductRepository;
import br.com.bcbdigital.product_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductApiTestConfiguration {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Bean
    public ProductService productService() {
        return new ProductService();
    }
}

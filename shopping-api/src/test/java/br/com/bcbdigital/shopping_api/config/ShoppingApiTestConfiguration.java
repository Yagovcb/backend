package br.com.bcbdigital.shopping_api.config;

import br.com.bcbdigital.shopping_api.repository.ShopRepository;
import br.com.bcbdigital.shopping_api.service.ProductService;
import br.com.bcbdigital.shopping_api.service.ShopService;
import br.com.bcbdigital.shopping_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ShoppingApiTestConfiguration {

    @Autowired
    private ShopRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Bean
    public ShopService shopService() {
        return new ShopService(repository, productService, userService);
    }
}

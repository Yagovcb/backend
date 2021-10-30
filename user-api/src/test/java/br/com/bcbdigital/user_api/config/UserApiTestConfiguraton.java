package br.com.bcbdigital.user_api.config;

import br.com.bcbdigital.user_api.repository.UserRepository;
import br.com.bcbdigital.user_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserApiTestConfiguraton {

    @Autowired
    private UserRepository repository;

    @Bean
    public UserService userService(){ return new UserService(repository);}
}

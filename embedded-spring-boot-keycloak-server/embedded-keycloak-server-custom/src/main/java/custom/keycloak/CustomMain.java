package custom.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@EntityScan(basePackages = "custom.keycloak.model")
public class CustomMain {

    public static void main(String[] args) {
        SpringApplication.run(CustomMain.class, args);
    }
}

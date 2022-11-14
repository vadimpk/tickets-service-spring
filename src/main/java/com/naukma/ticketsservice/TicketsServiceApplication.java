package com.naukma.ticketsservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TicketsServiceApplication {

    static final Logger log =
            LoggerFactory.getLogger(TicketsServiceApplication.class);

    public static void main(String[] args){
        SpringApplication.run(TicketsServiceApplication.class, args);
        log.debug("Starting TicketsServiceApplication application in debug with {} args", args.length);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Ticket Service").version("1.4")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}

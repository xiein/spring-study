package com.faith;

import com.faith.entity.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringConsumingRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringConsumingRestApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Quote quote = restTemplate.getForObject(
                    "http://localhost:8080/random", Quote.class);
            log.info(quote.toString());
        };
    }

}

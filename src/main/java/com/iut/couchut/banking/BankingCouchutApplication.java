package com.iut.couchut.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.iut.couchut.banking.web",
        "com.iut.couchut.banking.config",
        "com.iut.couchut.banking.repository"
})
@EnableAutoConfiguration
public class BankingCouchutApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingCouchutApplication.class, args);
    }
}

package com.example.insecure.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User john = new User("John", "test@test.fr");
            User jane = new User("Jane", "test2@test.fr");
            userRepository.saveAll(List.of(john, jane));
        };
    }
}
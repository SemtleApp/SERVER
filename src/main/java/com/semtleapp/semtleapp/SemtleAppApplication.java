package com.semtleapp.semtleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SemtleAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemtleAppApplication.class, args);
    }

}

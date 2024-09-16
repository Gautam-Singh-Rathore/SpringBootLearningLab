package com.gautam.JPA;

import com.gautam.JPA.models.Author;
import com.gautam.JPA.repositories.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpaApplication.class, args);

	}


    @Bean
    public CommandLineRunner commandLineRunner(
            AuthorRepository authorRepository
    ){
        return args -> {



        };
    }

}

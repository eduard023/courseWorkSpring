package com.example.courseworkspring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class CourseWorkSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseWorkSpringApplication.class, args);
    }

}

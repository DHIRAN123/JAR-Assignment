package com.example.jar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.jar")
public class JarApplication {

    public static void main(String[] args) {
        SpringApplication.run(JarApplication.class, args);
    }
}

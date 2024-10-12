package com.example.task18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BookManagerApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BookManagerApp.class, args); // запуск веб приложения
    }
}

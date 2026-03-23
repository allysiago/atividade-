package com.kanux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Primeiro endpoint da aula - Hello World
 * Acessível em: GET http://localhost:8080/api/hello
 */
@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}

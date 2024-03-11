package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fake-api")
public class FakeAPIController {
    @GetMapping("/example")
    public String getExample() {
        return "{\"message\": \"Witaj w moim symulowanym API!\", \"next\": \"http://localhost:8080/fake-api/next\"}";
    }

    @GetMapping("/next")
    public String getNext() {
        return "{\"message\": \"To jest kolejny zasób, do którego dotarłeś!\", \"next\": \"http://localhost:8080/fake-api/another\"}";
    }

    @GetMapping("/another")
    public String getAnother() {
        return "{\"message\": \"Dotarłeś do ostatniego zasobu!\"}";
    }
}

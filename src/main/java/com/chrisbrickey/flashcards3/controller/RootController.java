package com.chrisbrickey.flashcards3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @RequestMapping("/")
    public String getIntroduction() {
        return "Flashcards";
    }
}
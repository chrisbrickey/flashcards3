package com.chrisbrickey.flashcards3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    // TODO: serve basic html structure that uses CardController and DeckController to serve up content
    @RequestMapping("/")
    public String getRoot() {
        return "Flashcards interim page";
    }
}
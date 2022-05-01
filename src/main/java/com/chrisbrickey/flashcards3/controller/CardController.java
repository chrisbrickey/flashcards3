package com.chrisbrickey.flashcards3.controller;

import com.chrisbrickey.flashcards3.response.CardResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// TODO: extract logic to a CardService
// TODO: nest json controllers under api/ directory (e.g. api/v1/card)
@RestController
public class CardController {

    @RequestMapping(value="/v1/card", method= RequestMethod.GET)
//    public CardResponse getCard(@RequestParam(category = "category", defaultValue = "java")) {
    public CardResponse getCard() {
        // Spring Boot framework transforms the object/attributes into json when returning via HTTP
        CardResponse card = new CardResponse(1L, "some question", "some answer");
        return card;
    }
}

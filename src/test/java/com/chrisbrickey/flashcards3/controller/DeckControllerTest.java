package com.chrisbrickey.flashcards3.controller;


import com.chrisbrickey.flashcards3.response.CardResponse;
import com.chrisbrickey.flashcards3.response.DeckResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(DeckController.class)
public class DeckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    // unit test
    public void returnDeckResponseAttributes() throws IOException {
        DeckController controller = new DeckController();
        DeckResponse response = controller.getDeck("static/csv/sample.csv");
        List<CardResponse> cards = response.getCards();

        assertEquals(3, cards.size());

        var firstCard = cards.get(0);
        assertEquals(1L, firstCard.getId());
        assertEquals("cat", firstCard.getQuestion());
        assertEquals("el gato", firstCard.getAnswer());
        assertEquals("spanish", firstCard.getCategory());

        var secondCard = cards.get(1);
        assertEquals(2L, secondCard.getId());
        assertEquals("cat", secondCard.getQuestion());
        assertEquals("le chat", secondCard.getAnswer());
        assertEquals("french", secondCard.getCategory());

        var thirdCard = cards.get(2);
        assertEquals(3L, thirdCard.getId());
        assertEquals("Index into a String.", thirdCard.getQuestion());
        assertEquals("someString.substring(0,1)", thirdCard.getAnswer());
        assertEquals("java", thirdCard.getCategory());
    }

    @Test
    // integration test
    public void returnCardResponse() throws Exception {

        // construct expected response
        List<List<String>> deckOfCards = new ArrayList<>();
        deckOfCards.add(Arrays.asList("cat", "el gato", "spanish"));
        deckOfCards.add(Arrays.asList("cat", "le chat", "french"));
        deckOfCards.add(Arrays.asList("Index into a String.", "someString.substring(0,1)", "java"));
        DeckResponse expectedResponseObject = new DeckResponse(deckOfCards);
        ObjectMapper mapper = new ObjectMapper();
        String expectedResponseJson = mapper.writeValueAsString(expectedResponseObject);

        // assert properties and content
        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/deck")
                .param("filepath", "static/csv/sample.csv")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponseJson));
    }
}
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

import java.io.FileNotFoundException;
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
    public void returnDeckResponseAttributes() throws FileNotFoundException {
        DeckController controller = new DeckController();
        DeckResponse response = controller.getDeck("/static/sample.csv");
        List<CardResponse> cards = response.getCards();

        assertEquals(cards.size(), 2);

        var firstCard = cards.get(0);
        assertEquals(firstCard.getId(), 1L);
        assertEquals(firstCard.getQuestion(), "cat");
        assertEquals(firstCard.getAnswer(), "el gato");
        assertEquals(firstCard.getCategory(), "spanish");

        var secondCard = cards.get(1);
        assertEquals(secondCard.getId(), 2L);
        assertEquals(secondCard.getQuestion(), "cat");
        assertEquals(secondCard.getAnswer(), "le chat");
        assertEquals(secondCard.getCategory(), "french");
    }

    @Test
    // integration test
    public void returnCardResponse() throws Exception {
        List<List<String>> deckOfCards = new ArrayList<>();
        deckOfCards.add(Arrays.asList("cat", "el gato", "spanish"));
        deckOfCards.add(Arrays.asList("cat", "le chat", "french"));
        DeckResponse expectedResponseObject = new DeckResponse(deckOfCards);

        ObjectMapper mapper = new ObjectMapper();
        String expectedResponseJson = mapper.writeValueAsString(expectedResponseObject);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/deck")
                .param("filepath", "/static/sample.csv")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponseJson));
    }
}
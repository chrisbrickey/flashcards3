package com.chrisbrickey.flashcards3.controller;


import com.chrisbrickey.flashcards3.response.CardResponse;
import com.chrisbrickey.flashcards3.response.DeckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(DeckController.class)
public class DeckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void returnDeckResponseAttributes() throws IOException {
        DeckController controller = new DeckController();
        DeckResponse response = controller.getDeck("static/csv/sample.csv");
        List<CardResponse> cardResponses = response.getCards();
        DeckResponse.sortCards(cardResponses);

        assertEquals(4, cardResponses.size());

        var firstCard = cardResponses.get(0);
        assertEquals(1L, firstCard.getId());
        assertEquals("a cat", firstCard.getQuestion());
        assertEquals("un gato", firstCard.getAnswer());
        assertEquals("spanish", firstCard.getCategory());

        var secondCard = cardResponses.get(1);
        assertEquals(2L, secondCard.getId());
        assertEquals("a cat", secondCard.getQuestion());
        assertEquals("un chat", secondCard.getAnswer());
        assertEquals("french", secondCard.getCategory());

        var thirdCard = cardResponses.get(2);
        assertEquals(3L, thirdCard.getId());
        assertEquals("Index into a String.", thirdCard.getQuestion());
        assertEquals("someString.substring(0,1)", thirdCard.getAnswer());
        assertEquals("java", thirdCard.getCategory());
    }

    @Test
    public void shuffleCardsAfterIdsAssigned() throws IOException {
        DeckController controller = new DeckController();
        DeckResponse response = controller.getDeck("static/csv/sample.csv");
        List<CardResponse> result = response.getCards();
        Long start = 0L;
        boolean ordered = true;

        for (CardResponse cardResponse : result) {
            Long resultID = cardResponse.getId();
            if ((start + 1L) == resultID) {
                start ++;
            } else {
                ordered = false;
                break;
            }
        }

        assertFalse(ordered);
    }

    @Test
    // integration test
    public void returnCardResponse() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/deck")
                .param("filepath", "static/csv/sample.csv")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
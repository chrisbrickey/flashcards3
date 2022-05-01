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
        // TODO: feed test file and test against that when filepath is a parameter to the controller action
        // ...so that I'm not testing against production content and can move my java csv file into the app
        DeckController controller = new DeckController();
        DeckResponse response = controller.getDeck();
        List<CardResponse> cards = response.getCards();

        var firstCard = cards.get(0);
        var secondCard = cards.get(1);
        var thirdCard = cards.get(2);

        assertEquals(firstCard.getId(), 0L);
        assertEquals(firstCard.getQuestion(), "question");
        assertEquals(firstCard.getAnswer(), "answer");

        assertEquals(secondCard.getId(), 1L);
        assertEquals(secondCard.getQuestion(), "cat");
        assertEquals(secondCard.getAnswer(), "el gato");

        assertEquals(thirdCard.getId(), 2L);
        assertEquals(thirdCard.getQuestion(), "cat");
        assertEquals(thirdCard.getAnswer(), "le chat");

//        System.out.println("From test: ");
//        System.out.println(first.getId());
//        System.out.println(first.getQuestion());
//        System.out.println(first.getAnswer());
//
//        System.out.println(second.getId());
//        System.out.println(second.getQuestion());
//        System.out.println(second.getAnswer());
//
//        System.out.println(third.getId());
//        System.out.println(third.getQuestion());
//        System.out.println(third.getAnswer());
    }

    @Test
    // integration test
    public void returnCardResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CardResponse expectedResponseObject = new CardResponse(1, "some question", "some answer");
        String expectedResponseJson = mapper.writeValueAsString(expectedResponseObject);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/card")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponseJson));
    }
}
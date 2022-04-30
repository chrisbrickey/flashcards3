package com.chrisbrickey.flashcards3.controller;

import com.chrisbrickey.flashcards3.response.CardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    // unit test
    public void returnCardResponseAttributes() {
        CardController controller = new CardController();
        CardResponse response = controller.getCard();
        assertEquals(response.getId(), 1L);
        assertEquals(response.getQuestion(), "some question");
        assertEquals(response.getAnswer(), "some answer");
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

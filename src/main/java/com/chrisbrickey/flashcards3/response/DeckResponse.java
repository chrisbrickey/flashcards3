package com.chrisbrickey.flashcards3.response;

import java.util.ArrayList;
import java.util.List;

public class DeckResponse {
    private List<CardResponse> cards;

    public DeckResponse(List<List<String>> cardData) {
        setCards(cardData);
    }

    public List<CardResponse> getCards() { return cards; }

    public void setCards(List<List<String>> cardData) {
        List<CardResponse> deck = new ArrayList<>();
        for(int i=0; i< cardData.size(); i++) {
            List<String> cardLine = cardData.get(i);

            // below assumes the structure of the CSV file; using maps instead of arrays might loosen this up a bit
            String question = cardLine.get(0);
            String answer = cardLine.get(1);

            // TODO: add validation on content here or in a Card file
            int cardId = i + 1;
            CardResponse card = new CardResponse(cardId, question, answer);
            deck.add(card);
        }

        this.cards = deck;
    }
}
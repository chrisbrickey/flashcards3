package com.chrisbrickey.flashcards3.response;

import java.util.ArrayList;
import java.util.List;

public class DeckResponse {
    private List<CardResponse> cards;
//    private String[] filterCriteria;
//
    public DeckResponse(List<List<String>> cardData) {
        System.out.println("The cardData being passed into the DeckResponse constructor: ");
        System.out.println(cardData);
        setCards(cardData);
//        setFilterCriteria(category, filename);
    }
//
    public List<CardResponse> getCards() { return cards; }

    public void setCards(List<List<String>> cardData) {
//        for(List<String> cardLine: cardData) {
        List<CardResponse> deck = new ArrayList<>();
        for(int i=0; i< cardData.size(); i++) {
            List<String> cardLine = cardData.get(i);

            // below assumes the structure of the CSV file; using maps instead of arrays could loosen this up a bit
            String question = cardLine.get(0);
            String answer = cardLine.get(1);
            String category = cardLine.get(2);

            int cardId = i + 1;
            CardResponse card = new CardResponse(cardId, question, answer, category);
            deck.add(card);
        }

        this.cards = deck;
    }
}

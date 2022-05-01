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
            String question = cardLine.get(0); // assumes the structure of the CSV file
            String answer = cardLine.get(1);
            //TODO: add category to cards

            CardResponse card = new CardResponse(i, question, answer);
            deck.add(card);
        }

        this.cards = deck;
    }
}

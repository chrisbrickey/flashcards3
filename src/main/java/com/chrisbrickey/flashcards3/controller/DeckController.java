package com.chrisbrickey.flashcards3.controller;

import com.chrisbrickey.flashcards3.response.DeckResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: extract logic to a DeckService
@RestController
public class DeckController {

    // TODO: add a POST method instead of passing data packet to GET
    // TODO: pass in category as parameter - to filter deck
    @RequestMapping(value="/v1/deck", method= RequestMethod.GET)
    public DeckResponse getDeck(@RequestParam String filepath)
            throws FileNotFoundException {

        // TODO: extract reading of csv file to DeckService
        // TODO: consider transforming each CSV line to a map instead of an array
        List<String[]> content = new ArrayList<>();

        File file = new File(getClass().getResource(filepath).getFile());
        Scanner scanner = new Scanner(file);

        // skip header row of csv file
        scanner.nextLine();

        // parse file content into 2D array of formatted strings; TODO: extract to method
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            String[] cardStrings = nextLine.split(",");
            formatCardStrings(cardStrings); // mutates
            content.add(cardStrings);
        }

        // construct response object
        DeckResponse deck = new DeckResponse(content);
        return deck;
    }

    /**
     * Mutates the collection of strings - transforming special characters
     * For example, commas are used as delimeters so if we need to show a comma within the content of a card,
     * then we use different character to represent it in the source file (e.g. `) and transform the backtick to a comma here.
     * @param unformatted: array of strings from some source file
     */
    private void formatCardStrings(String[] unformatted) {
        for (int i=0; i<unformatted.length; i++) {
            String cardString = unformatted[i];
            if (cardString.contains("`")) {
                unformatted[i] = cardString.replace('`', ',');
            }
        }
    }
}
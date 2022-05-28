package com.chrisbrickey.flashcards3.controller;

import com.chrisbrickey.flashcards3.response.DeckResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
public class DeckController {

    // TODO: add a POST method instead of passing data packet to GET
    // TODO: pass in category as parameter - to filter deck
    @RequestMapping(value="/v1/deck", method= RequestMethod.GET)
    public DeckResponse getDeck(@RequestParam String filepath)
            throws FileNotFoundException {

        // TODO: extract reading of csv file to a DeckService
        // TODO: consider transforming each CSV line to a map instead of an array
        List<List<String>> content = new ArrayList<>();

        File file = new File(getClass().getResource(filepath).getFile());
        Scanner scanner = new Scanner(file);

        // skip header row of csv file
        scanner.nextLine();

        // TODO: extract to method
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();

            // Parse file content into 2D array of formatted strings.
            List<String> cardStrings = Arrays.asList(nextLine.split(","));

            // Mutation!
            formatCardStrings(cardStrings);

            content.add(cardStrings);
        }

        // construct response object
        DeckResponse deck = new DeckResponse(content);
        return deck;
    }

    /**
     * Mutates content to accommodate special characters in a CSV source. See README.
     * @param unformatted: List of Strings
     */
    private void formatCardStrings(List<String> unformatted) {
        for (int i = 0; i < unformatted.size(); i++) {
            String cardString = unformatted.get(i);
            if (cardString.contains("`")) {
                String formattedString = cardString.replace('`', ',');
                unformatted.set(i, formattedString);
            }
        }
    }
}
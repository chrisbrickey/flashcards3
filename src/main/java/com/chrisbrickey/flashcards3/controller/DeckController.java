package com.chrisbrickey.flashcards3.controller;

import com.chrisbrickey.flashcards3.response.DeckResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: extract logic to a DeckService
@RestController
public class DeckController {

    @RequestMapping(value="/v1/deck", method= RequestMethod.GET)
    public DeckResponse getDeck(
//        TODO: pass in category as parameter - to filter deck
//        TODO: pass in filepath as parameter - instead of hard-coding; add validation on file before injesting
//        @RequestParam("category") String category,
//        @RequestParam("filepath") String filepath
    ) throws FileNotFoundException {

        // TODO: consider transforming each CSV line to a map instead of an array
        List<List<String>> records = new ArrayList<>();

        File file = new File(getClass().getResource("/static/sample.csv").getFile());
        Scanner scanner = new Scanner(file);

        // skip header row of csv file
        scanner.nextLine();

        while (scanner.hasNextLine()) { records.add(getRecordFromLine(scanner.nextLine())); }

        DeckResponse deck = new DeckResponse(records);
        return deck;
    }

    // TODO: Refactor. Try splitting the string into array using comma delimeter.
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
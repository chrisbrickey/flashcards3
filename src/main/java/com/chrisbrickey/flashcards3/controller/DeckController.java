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

        // TODO: extract reading of csv file into memory to a DeckService
        // TODO: consider transforming each CSV line to a map instead of an array
        List<List<String>> records = new ArrayList<>();

        File file = new File(getClass().getResource(filepath).getFile());
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
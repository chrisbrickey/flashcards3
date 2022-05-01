package com.chrisbrickey.flashcards3.controller;

import com.chrisbrickey.flashcards3.response.CardResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// TODO: extract logic to a DeckService
@RestController
public class DeckController {

    @RequestMapping(value="/v1/deck", method= RequestMethod.GET)
    public String getDeck(
//        @RequestParam("category") String category,
//        @RequestParam("filepath") String filepath
    ) throws FileNotFoundException {
        // Spring Boot framework transforms the object/attributes into json when returning via HTTP
//        CardResponse card = new CardResponse(1L, "some question", "some answer");
//        return card;

        List<List<String>> records = new ArrayList<>();

        File file = new File(getClass().getResource("/static/sample.csv").getFile());
        System.out.println(file);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }

        System.out.println(records);

        return "hello there again";
        // TODO: package return value into DeckResponse object
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
    }
        return values;
    }
}


//        List<List<String>> records = new ArrayList<>();
//        try (Scanner scanner = new Scanner(new File("/../../resources/static/sample.csv"));) {
//            while (scanner.hasNextLine()) {
//                records.add(getRecordFromLine(scanner.nextLine()));
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(records);


// ALternative using Scanner
//List<List<String>> records = new ArrayList<>();
//try (Scanner scanner = new Scanner(new File("book.csv"));) {
//    while (scanner.hasNextLine()) {
//        records.add(getRecordFromLine(scanner.nextLine()));
//    }
//}
//private List<String> getRecordFromLine(String line) {
//    List<String> values = new ArrayList<String>();
//    try (Scanner rowScanner = new Scanner(line)) {
//        rowScanner.useDelimiter(COMMA_DELIMITER);
//        while (rowScanner.hasNext()) {
//            values.add(rowScanner.next());
//        }
//    }
//    return values;
//}


// USING 3rd party CSVReader...
//List<List<String>> records = new ArrayList<List<String>>();
//try (CSVReader csvReader = new CSVReader(new FileReader("book.csv"));) {
//    String[] values = null;
//    while ((values = csvReader.readNext()) != null) {
//        records.add(Arrays.asList(values));
//    }
//}

// gives 500 error not sure why - maybe can't find file
//
//        List<List<String>> records = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader("sample.csv"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//                records.add(Arrays.asList(values));
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
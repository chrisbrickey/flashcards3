package com.chrisbrickey.flashcards3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.chrisbrickey.flashcards3.controller.DeckController;
import com.chrisbrickey.flashcards3.response.DeckResponse;
import org.junit.jupiter.api.Test;

public class CsvParsingTest {

    @Test
    public void ensureAllCsvFilesCanBeParsed() {
        String[] filepaths = new String[] {
                "/static/css/sample.csv",
                "/static/css/content1.csv"
        };

        for (String filepath : filepaths) {
            assertEquals(canParseCsv(filepath), true);
        }
    }

//    TODO: extract reading of csv file into memory to a DeckService and test using that service
    private boolean canParseCsv(String filepath) {
        try {
            DeckController controller = new DeckController();
            DeckResponse response = controller.getDeck(filepath);
            response.getCards();
        } catch (Exception e){
          return false;
        }

        return true;
    }
}
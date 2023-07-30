package com.chrisbrickey.flashcards3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.chrisbrickey.flashcards3.controller.DeckController;
import com.chrisbrickey.flashcards3.response.DeckResponse;
import org.junit.jupiter.api.Test;

public class CsvParsingTest {

    @Test
    public void ensureAllCsvFilesCanBeParsed() {
        // TODO: change this to detect and loop over all files in the csv directory
        String[] filepaths = new String[] {
                "static/csv/sample.csv",
                "static/csv/english_to_french.csv",
        };

        for (String filepath : filepaths) {
            assertEquals(true, canParseCsv(filepath));
        }
    }

//    TODO: extract reading of csv file into memory to a DeckService and test using that service
    private boolean canParseCsv(String filepath) {
        try {
            DeckController controller = new DeckController();
            DeckResponse response = controller.getDeck(filepath);
            response.getCards();
        } catch (Exception e){
          // TODO: surface the line number where parsing error was encountered (from DeckResponse.setCards)
          return false;
        }

        return true;
    }
}

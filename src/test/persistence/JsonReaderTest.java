package persistence;

import model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account a = reader.read();
            fail();
        } catch (IOException e) {
            // all good here
        }
    }

    @Test
    void testReaderEmptyCart() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account a = reader.read();
            fail();
        } catch (IOException e) {
            // all good here
        }
    }


}

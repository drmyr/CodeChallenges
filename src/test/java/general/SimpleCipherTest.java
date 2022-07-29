package general;

import org.junit.jupiter.api.Test;

import static general.SimpleCipher.decrypt;
import static org.junit.jupiter.api.Assertions.*;

class SimpleCipherTest {

    @Test
    void decryptTest() {
        assertEquals("TRYME", decrypt(2, "VTAOG"));
        assertEquals("TURTLE", decrypt(10, "DEBDVO"));
    }
}
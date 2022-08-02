package general;

import org.junit.jupiter.api.Test;

import static general.DecodeString.decodeString;
import static org.junit.jupiter.api.Assertions.*;

class DecodeStringTest {

    @Test
    void decodeStringTest() {
        assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
    }
}
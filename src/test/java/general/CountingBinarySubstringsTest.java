package general;

import org.junit.jupiter.api.Test;

import static general.CountingBinarySubstrings.countBinarySubstrings;
import static org.junit.jupiter.api.Assertions.*;

class CountingBinarySubstringsTest {

    @Test
    void countBinarySubstringsTest() {
        final int count1 = countBinarySubstrings("001101");
        assertEquals(4, count1);

        final int count2 = countBinarySubstrings("00011100");
        assertEquals(5, count2);

        final int count3 = countBinarySubstrings("00011100111");
        assertEquals(7, count3);
    }
}
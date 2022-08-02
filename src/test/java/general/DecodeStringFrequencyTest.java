package general;

import org.junit.jupiter.api.Test;

import static general.DecodeStringFrequency.stringFrequencyTwo;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DecodeStringFrequencyTest {

    @Test
    void stringFrequencyTest() {
        assertArrayEquals(new int[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, stringFrequencyTwo("1226#24#"));
        assertArrayEquals(new int[]{2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, stringFrequencyTwo("1(2)23(3)"));
        assertArrayEquals(new int[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, stringFrequencyTwo("2110#(2)"));
        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 1, 1, 1}, stringFrequencyTwo("23#(2)24#25#26#23#(3)"));
    }
}
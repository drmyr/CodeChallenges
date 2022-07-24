package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static general.SlowestKey.slowestKey;
import static org.junit.jupiter.api.Assertions.*;

class SlowestKeyTest {

    @Test
    void slowestKeyTest() {
        record Test(char answer, int[] releaseTimes, String keysPressed) {}
        final List<Test> tests = List.of(
            new Test('c', new int[] {9,29,49,50}, "cbcd"),
            new Test('a', new int[] {12,23,36,46,62}, "spuda")
        );

        for(final Test test : tests) {
            assertEquals(test.answer(), slowestKey(test.releaseTimes(), test.keysPressed()));
        }
    }
}
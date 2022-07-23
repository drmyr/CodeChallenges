package dp;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static dp.DeleteAndEarn.deleteAndEarn;
import static org.junit.jupiter.api.Assertions.*;

class DeleteAndEarnTest {

    @Test
    void deleteAndEarnTest() {
        final Map<Integer, int[]> answerAndQuestion = Map.of(
            9, new int[] {2,2,3,3,3,4},
            6, new int[] {3,4,2},
            18, new int[] {1,1,1,2,4,5,5,5,6}
        );

        for(final var entry : answerAndQuestion.entrySet()) {
            assertEquals(entry.getKey(), deleteAndEarn(entry.getValue()));
        }
    }
}
package dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static dp.FootballScoreCombos.enumerateSequencesToFinalScore;
import static org.junit.jupiter.api.Assertions.*;

class FootballScoreCombosTest {

    @Test
    void enumerateSequencesToFinalScoreTest() {
        enumerateSequencesToFinalScore(9).forEach(l -> System.out.println(Arrays.toString(l.toArray())));
    }
}
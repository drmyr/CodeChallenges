package backtracking;

import org.junit.jupiter.api.Test;

import static backtracking.AllPermutationsOfSet.allPermutations;
import static org.junit.jupiter.api.Assertions.*;

class AllPermutationsOfSetTest {

    @Test
    void allPermutationsTest() {
        final String testOne = "abc";
        assertEquals(6, allPermutations(testOne).size());

        final String testTwo = "halo";
        assertEquals(24, allPermutations(testTwo).size());
    }
}
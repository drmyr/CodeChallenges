package backtracking;

import org.junit.jupiter.api.Test;

import static backtracking.AllPermutationsOfSet.allPermutationsSampleOne;
import static backtracking.AllPermutationsOfSet.allPermutationsSampleTwo;
import static org.junit.jupiter.api.Assertions.*;

class AllPermutationsOfSetTest {

    @Test
    void allPermutationsTest() {
        final String testOne = "1234";
        assertEquals(6, allPermutationsSampleOne(testOne).size());

//        final String testTwo = "halo";
//        assertEquals(24, allPermutations(testTwo).size());
    }

    @Test
    void allPermutationsSampleTwoTest() {
        allPermutationsSampleTwo("abc").forEach(System.out::println);
    }
}
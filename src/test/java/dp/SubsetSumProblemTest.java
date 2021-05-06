package dp;

import org.junit.jupiter.api.Test;

import static dp.SubsetSumProblem.canFormSumFromSubset;
import static org.junit.jupiter.api.Assertions.*;

class SubsetSumProblemTest {

    @Test
    void canFormSumFromSubsetTest() {
        assertFalse(canFormSumFromSubset(new int[] {2,3,7,8,10}, 11));
        assertTrue(canFormSumFromSubset(new int[] {2,3,7,8,10}, 12));
    }
}
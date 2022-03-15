package graphs;

import org.junit.jupiter.api.Test;

import static graphs.KahnsAlgorithm.canFinish;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KhansAlgorithmTest {

    @Test
    void canFinishTest() {
        assertTrue(canFinish(2, new int[][] {{1,0}}));
        assertTrue(canFinish(5, new int[][] {{1,4},{2,4},{3,1},{3,2}}));
        assertFalse(canFinish(2, new int[][] {{1,0},{0,1}}));
    }
}
package trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static trees.ValidTree.isValidTree;

class ValidTreeTest {

    @Test
    void isValidTreeTest() {
        assertTrue(isValidTree(5, new int[][] {{0,1},{0,2},{0,3},{1,4}}));
        assertFalse(isValidTree(5, new int[][] {{0,1},{1,2},{2,3},{1,3},{1,4}}));
    }
}
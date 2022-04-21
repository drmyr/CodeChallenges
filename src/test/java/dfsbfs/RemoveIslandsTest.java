package dfsbfs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static dfsbfs.RemoveIslands.removeIslands;
import static org.junit.jupiter.api.Assertions.*;

class RemoveIslandsTest {

    @Test
    void removeIslandsTest() {
        int[][] map = {
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1},
            {0, 0, 1, 0, 1, 0},
            {1, 1, 0, 0, 1, 0},
            {1, 0, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 1},
        };
        int[][] res = removeIslands(map);
        for(int[] line : res) {
            System.out.println(Arrays.toString(line));
        }
    }
}
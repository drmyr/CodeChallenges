package dfsbfs;

import org.junit.jupiter.api.Test;

import static dfsbfs.RobotInPyramid.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RobotInPyramidTest {

    @Test
    void numberOfWaysToEndTest() {
        assertThat(2, is(equalTo(numberOfWaysToEnd(new int[30][3]))));
        assertThat(4, is(equalTo(numberOfWaysToEnd(new int[40][4]))));
        assertThat(9, is(equalTo(numberOfWaysToEnd(new int[90][5]))));
    }

    @Test
    void canEnterTest() {
        final int[][] oddColumnMatrix = new int[3][5];
        for(int r = 0; r < oddColumnMatrix.length; r++) {
            for(int c = 0; c < oddColumnMatrix[r].length; c++) {
                if(r == 0 && (c == 0 || c == 1 || c == 3 || c == 4)) {
                    assertFalse(canEnterTrimmedMatrix(oddColumnMatrix, r, c));
                } else if(r == 1 && (c == 0 || c == 4)) {
                    assertFalse(canEnterTrimmedMatrix(oddColumnMatrix, r, c));
                } else {
                    assertTrue(canEnterTrimmedMatrix(oddColumnMatrix, r, c));
                }
            }
        }

        final int[][] evenColumnMatrix = new int[2][4];
        for(int r = 0; r < evenColumnMatrix.length; r++) {
            for(int c = 0; c < evenColumnMatrix[r].length; c++) {
                if(r == 0 && (c == 0 || c == 3)) {
                    assertFalse(canEnterTrimmedMatrix(evenColumnMatrix, r, c));
                } else {
                    assertTrue(canEnterTrimmedMatrix(evenColumnMatrix, r, c));
                }
            }
        }

    }

    @Test
    void trimToHeightOfPyramidTest() {
        final int[][] longAndSkinnyEven = new int[100][4];
        longAndSkinnyEven[99] = new int[] {1,2,3,4};
        final int[][] trimmed = trimMatrixToHeightOfPyramid(longAndSkinnyEven);
        assertEquals(trimmed.length, (int)Math.ceil(trimmed[0].length / 2d));
        assertArrayEquals(trimmed[1], new int[] {1,2,3,4});

        final int[][] longAndSkinnyOdd = new int[100][5];
        longAndSkinnyOdd[99] = new int[] {1,2,3,4,5};
        final int[][] trimmedOdd = trimMatrixToHeightOfPyramid(longAndSkinnyOdd);
        assertEquals(trimmedOdd.length, (int)Math.ceil(trimmedOdd[0].length / 2d));
        assertArrayEquals(trimmedOdd[2], new int[] {1,2,3,4,5});
    }
}
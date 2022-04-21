package dfsbfs;

import org.junit.jupiter.api.Test;

import static dfsbfs.RiverSizes.riverSizes;
import static org.junit.jupiter.api.Assertions.*;

class RiverSizesTest {

    @Test
    void riverSizesTest() {
        int[][] matrix = {
          {1, 1, 0},
          {1, 0, 1},
          {1, 1, 1},
          {1, 1, 0},
          {1, 0, 1},
          {0, 1, 0},
          {1, 0, 0},
          {1, 0, 0},
          {0, 0, 0},
          {1, 0, 0},
          {1, 0, 1},
          {1, 1, 1}
        };

        riverSizes(matrix);
    }
}
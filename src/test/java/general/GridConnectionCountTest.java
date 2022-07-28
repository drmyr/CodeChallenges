package general;

import org.junit.jupiter.api.Test;

import static general.GridConnectionCount.countGridConnections;
import static org.junit.jupiter.api.Assertions.*;

class GridConnectionCountTest {

    @Test
    void countGridConnectionsTest() {
        final int count = countGridConnections(new int[][] {{1,1,1},{0,1,0},{0,0,0},{1,1,0}});
        assertEquals(5, count);
    }
}
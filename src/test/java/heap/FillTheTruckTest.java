package heap;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static heap.FillTheTruck.fillTheTruck;
import static org.junit.jupiter.api.Assertions.*;

class FillTheTruckTest {

    @Test
    void fillTheTruckTest() {
        record Test(int answer, int[][] boxCountsAndCapacities, int truckCapacity) {}
        final List<Test> tests = List.of(
            new Test(8, new int[][] {{1,3}, {2,2}, {3,1}}, 4),
            new Test(91, new int[][] {{5,10}, {2,5}, {4,7}, {3,9}}, 10)
        );

        for(final Test test : tests) {
            assertEquals(test.answer(), fillTheTruck(test.boxCountsAndCapacities(), test.truckCapacity()));
        }
    }
}
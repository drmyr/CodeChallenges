package general;

import org.junit.jupiter.api.Test;

import static general.GasStation.canCompleteCircuit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GasStationTest {

    @Test
    void canCompleteCircuitTest() {
        assertThat(3, is(equalTo(canCompleteCircuit(new int[] {1,2,3,4,5}, new int[] {3,4,5,1,2}))));
        assertThat(-1, is(equalTo(canCompleteCircuit(new int[] {2,3,4}, new int[] {3,4,3}))));
    }
}
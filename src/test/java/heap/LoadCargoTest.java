package heap;

import org.junit.jupiter.api.Test;

import static heap.LoadCargo.maxCargoLoad;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LoadCargoTest {

    @Test
    void maxCargoLoadTest() {
        assertThat(3, is(equalTo(maxCargoLoad(new int[] {3,4,6,5}, new int[] {3,3,4,4}, 2))));
        assertThat(2, is(equalTo(maxCargoLoad(new int[] {90,20,15}, new int[] {20,10,10}, 15))));
    }
}
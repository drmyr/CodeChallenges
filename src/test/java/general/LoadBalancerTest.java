package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.LoadBalancer.balanceLoad;
import static org.hamcrest.MatcherAssert.assertThat;

class LoadBalancerTest {

    @Test
    void balanceLoadTest() {
        assertThat(balanceLoad(new Integer[] {2, 4, 5, 3, 3, 9, 2, 2, 2}), Matchers.arrayContaining(5,9));
        assertThat(balanceLoad(new Integer[] {1, 1, 1, 1}), Matchers.emptyArray());
    }
}
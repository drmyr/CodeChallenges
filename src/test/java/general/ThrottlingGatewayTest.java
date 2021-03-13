package general;

import org.junit.jupiter.api.Test;

import static general.ThrottlingGateway.cableCarThrottling;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ThrottlingGatewayTest {

    @Test
    void cableCatThrottlingTest() {
        assertThat(7, is(equalTo(cableCarThrottling(27, new int[] {1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7,7,7,7, 11, 11, 11, 11}))));
    }
}
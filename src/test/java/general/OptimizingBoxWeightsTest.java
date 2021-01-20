package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static general.OptimizingBoxWeights.optimizeBoxWeights;

class OptimizingBoxWeightsTest {

    @Test
    void optimizeBoxWeightsTest() {
        final Integer[][] a = {{4,5}, {5,3,2,4,1,2}};
        for(final Integer[][] curr : List.<Integer[][]>of(a)) {
            assertThat(curr[0], is(equalTo(optimizeBoxWeights(curr[1]))));
        }
    }
}
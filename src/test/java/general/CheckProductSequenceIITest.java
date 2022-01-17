package general;

import org.junit.jupiter.api.Test;

import static general.CheckProductSequenceII.shortestSubsequence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CheckProductSequenceIITest {

    @Test
    void shortestSubsequenceTest() {
        final String[] products = {"bio", "nulo", "amaz", "nulo", "bioto", "candi"};
        final String[] sequence = {"nulo", "bioto", "candi"};
        assertThat(3, is(equalTo(shortestSubsequence(products, sequence))));
    }
}
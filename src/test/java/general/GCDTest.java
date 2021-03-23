package general;

import org.junit.jupiter.api.Test;

import static general.GCD.gcdByEuclidsAlgorithm;
import static general.GCD.gcdByEuclidsAlgorithmRecursive;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GCDTest {

    @Test
    void gcdByEuclidsAlgorithmTest() {
        assertThat(5, is(equalTo(gcdByEuclidsAlgorithm(5, 25))));
        assertThat(5, is(equalTo(gcdByEuclidsAlgorithm(25, 5))));
        assertThat(5, is(equalTo(gcdByEuclidsAlgorithmRecursive(25, 5))));
        assertThat(5, is(equalTo(gcdByEuclidsAlgorithmRecursive(5, 25))));
    }
}
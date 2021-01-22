package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static general.PairsOfSongs.numPairsDivisibleBy60;

class PairsOfSongsTest {

    @Test
    void numPairsDivisibleBy60Test() {
        final int[][] a = {{3}, {30,20,150,100,40}},
                      b = {{3}, {60,60,60}},
                      c = {{6}, {60,60,60,60}};
        for(final int[][] test : List.of(a, b, c)) {
            assertThat(test[0][0], is(equalTo(numPairsDivisibleBy60(test[1]))));
        }
    }
}
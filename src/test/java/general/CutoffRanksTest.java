package general;

import org.junit.jupiter.api.Test;

import static general.CutoffRanks.rankCutoff;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class CutoffRanksTest {

    @Test
    void rankCutoffTest() {
        assertThat(3, is(equalTo(rankCutoff(3, 4, new int[] {100,50,50,25}))));
        assertThat(5, is(equalTo(rankCutoff(4, 5, new int[] {2,2,3,4,5}))));
    }
}
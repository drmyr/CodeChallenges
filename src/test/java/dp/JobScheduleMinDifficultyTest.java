package dp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static dp.JobScheduleMinDifficulty.*;

class JobScheduleMinDifficultyTest {

    private final int[][]
        a = {{6,5,4,3,2,1}, {2}, {7}},
        b = {{9,9,9}, {4}, {-1}},
        c = {{1,1,1}, {3}, {3}},
        d = {{6,5,4,3,2,1}, {4}, {12}},
        e = {{11,111,22,222,33,333,44,444}, {6}, {843}},
        f = {{143,446,351,170,117,963,785,76,139,772,452,743,23,767,564,872,922,532,957,945,203,615,843,909,458,320,290,235,174,814,414,669,422,769,781,721,523,94,100,464,484,562,941}, {5}, {1839}},
        g = {{380,302,102,681,863,676,243,671,651,612,162,561,394,856,601,30,6,257,921,405,716,126,158,476,889,699,668,930,139,164,641,801,480,756,797,915,275,709,161,358,461,938,914,557,121,964,315}, {10}, {3807}};

    @Test
    void minDifficultyTest() {
        for(final int[][] curr : List.of(a,b,c,d,e,f,g)) {
            assertThat(curr[2][0], is(equalTo(minDifficultyTooSlow(curr[0], curr[1][0]))));
        }
    }
}
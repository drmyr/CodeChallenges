package general;

import org.junit.jupiter.api.Test;

import static general.LargestRectangleOfHistogram.largestRectangleOfHistogram;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MostCommonWordTest {

    @Test
    void mostCommonWordTest() {
        assertThat("ball", is(equalTo(MostCommonWord.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}))));
    }
}
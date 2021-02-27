package general;

import org.junit.jupiter.api.Test;

import static general.ChooseFlask.flaskFinder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ChooseFlaskTest {

    @Test
    void flaskFinderTest() {
        final int[][] markings = new int[][] {{0,3},{0,5},{0,7},{1,6},{1,8},{1,9},{2,3},{2,5},{2,6}};
        assertThat(0, is(equalTo(flaskFinder(4, new int[] {4,6,6,7}, 3, 9, markings))));
    }
}
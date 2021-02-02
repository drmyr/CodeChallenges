package heap;

import org.junit.jupiter.api.Test;

import static heap.FiveStarSellers.fiveStartReviews;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class FiveStarSellersTest {

    @Test
    void fiveStartReviewsTest() {
        assertThat(3, is(equalTo(fiveStartReviews(new Integer[][] {{4,4}, {1,2}, {3, 6}}, 77d))));
    }
}
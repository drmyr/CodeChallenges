package general;

import org.junit.jupiter.api.Test;

import static general.RoseGarden.daysToBouquet;
import static general.RoseGarden.daysToBouquetBinarySearch;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoseGardenTest {

    @Test
    void daysToBouquetTest() {
        assertThat(4, is(equalTo(daysToBouquet(new int[] {1, 2, 4, 9, 3, 4, 1}, 2, 2))));
        assertThat(-1, is(equalTo(daysToBouquet(new int[] {1,10,3,10,2}, 3, 2))));
        assertThat(12, is(equalTo(daysToBouquet(new int[] {7,7,7,7,12,7,7}, 2, 3))));
    }

    @Test
    void daysToBouquetBinarySearchTest() {
        assertThat(4, is(equalTo(daysToBouquetBinarySearch(new int[] {1, 2, 4, 9, 3, 4, 1}, 2, 2))));
        assertThat(12, is(equalTo(daysToBouquetBinarySearch(new int[] {7,7,7,7,12,7,7}, 2, 3))));
    }
}
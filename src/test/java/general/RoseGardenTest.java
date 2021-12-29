package general;

import org.junit.jupiter.api.Test;

import static general.RoseGarden.daysToBouquet;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RoseGardenTest {

    @Test
    void daysToBouquetTest() {
        assertThat(4, is(equalTo(daysToBouquet(new int[] {1, 2, 4, 9, 3, 4, 1}, 2, 2))));
    }
}
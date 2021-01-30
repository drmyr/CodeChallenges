package general;

import org.junit.jupiter.api.Test;

import static general.ScheduleTasks.minTimeToFinish;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ScheduleTasksTest {

    @Test
    void minTimeToFinishTest() {
        assertThat(4, is(equalTo(minTimeToFinish(5, new int[] {4,2,8,3,5}, 19))));
    }
}
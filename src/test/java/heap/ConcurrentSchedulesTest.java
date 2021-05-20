package heap;

import org.junit.jupiter.api.Test;

import static heap.ConcurrentSchedules.mostConcurrentSchedules;
import static heap.MeetingRoomsTwo.minMeetingRooms;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrentSchedulesTest {

    @Test
    void mostConcurrentSchedulesTest() {
        final int[][] meetings = {{100, 300}, {145, 215}, {200, 230}, {215, 300}, {215, 400}, {500, 600}, {600, 700}, {205, 395}};
        assertThat(6, is(equalTo(mostConcurrentSchedules(meetings))));
        assertThat(2, is(equalTo(mostConcurrentSchedules(new int[][] {{0,30},{5,10},{15,20}}))));
        assertThat(1, is(equalTo(mostConcurrentSchedules(new int[][] {{7,10},{2,4}}))));
    }
}
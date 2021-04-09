package heap;

import org.junit.jupiter.api.Test;

import static heap.MeetingRoomsTwo.minMeetingRooms;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MeetingRoomsTwoTest {

    @Test
    void minMeetingRoomsTest() {
        assertThat(2, is(equalTo(minMeetingRooms(new int[][] {{0,30},{5,10},{15,20}}))));

        assertThat(1, is(equalTo(minMeetingRooms(new int[][] {{7,10},{2,4}}))));
    }
}
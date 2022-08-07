package mst;

import org.junit.jupiter.api.Test;

import static mst.TheEarliestMomentWhenEveryoneBecomeFriends.earliestFriends;
import static org.junit.jupiter.api.Assertions.*;

class TheEarliestMomentWhenEveryoneBecomeFriendsTest {

    @Test
    void earliestFriendsTest() {
        final int res = earliestFriends(new int[][] {{20190101,0,1}, {20190104,3,4}, {20190107,2,3}, {20190211,1,5}, {20190224,2,4}, {20190301,0,3}, {20190312,1,2}, {20190322,4,5}}, 6);

        assertEquals(20190301, res);
    }
}
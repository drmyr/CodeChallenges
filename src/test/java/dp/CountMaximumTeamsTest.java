package dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static dp.CountMaximumTeams.*;
import static org.junit.jupiter.api.Assertions.*;

class CountMaximumTeamsTest {

    @Test
    void countMaxTeamsTest() {
        assertEquals(2, countMaxTeams(Arrays.asList(3, 4, 3, 1, 6, 5), 3, 2));
        assertEquals(2, countMaxTeams(Arrays.asList(5, 1, 2, 1, 4, 5), 3, 2));
        assertEquals(3, countMaxTeams(Arrays.asList(1, 7, 18, 32, 65, 72, 90, 98, 100, 113, 146), 3, 25));
        assertEquals(0, countMaxTeams(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 5));
        //assertEquals(0, countMaxTeams(Arrays.asList(9, 3, 5, 7, 1), 2, 1));

    }

    @Test
    void countMaxTeamsTwoTest() {
        assertEquals(2, countMaxTeamsTwo(Arrays.asList(3, 4, 3, 1, 6, 5), 3, 2));
        assertEquals(2, countMaxTeamsTwo(Arrays.asList(5, 1, 2, 1, 4, 5), 3, 2));
        assertEquals(3, countMaxTeamsTwo(Arrays.asList(1, 7, 18, 32, 65, 72, 90, 98, 100, 113, 146), 3, 25));
        assertEquals(0, countMaxTeamsTwo(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 5));
        assertEquals(0, countMaxTeamsTwo(Arrays.asList(9, 3, 5, 7, 1), 2, 1));
        assertEquals(2, countMaxTeamsTwo(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 40));
        assertEquals(6, countMaxTeamsTwo(Arrays.asList(34, 5, 72, 48, 15, 2), 1, 1));
        assertEquals(1, countMaxTeamsTwo(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 20));
        assertEquals(0, countMaxTeamsTwo(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 5));
    }

    @Test
    void countMaxTeamsThreeTest() {
        assertEquals(0, countMaxTeamsThree(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 5));
        assertEquals(0, countMaxTeamsThree(Arrays.asList(9, 3, 5, 7, 1), 2, 1));
        assertEquals(1, countMaxTeamsThree(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 20));
        assertEquals(2, countMaxTeamsThree(Arrays.asList(3, 4, 3, 1, 6, 5), 3, 2));
        assertEquals(2, countMaxTeamsThree(Arrays.asList(34, 5, 72, 48, 15, 2), 3, 40));
        assertEquals(2, countMaxTeamsThree(Arrays.asList(5, 1, 2, 1, 4, 5), 3, 2));
        assertEquals(3, countMaxTeamsThree(Arrays.asList(1, 7, 18, 32, 65, 72, 90, 98, 100, 113, 146), 3, 25));
        assertEquals(6, countMaxTeamsThree(Arrays.asList(34, 5, 72, 48, 15, 2), 1, 1));
    }
}
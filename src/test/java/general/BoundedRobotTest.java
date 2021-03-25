package general;

import org.junit.jupiter.api.Test;

import static general.BoundedRobot.isRobotBounded;
import static org.junit.jupiter.api.Assertions.*;

class BoundedRobotTest {

    @Test
    void isRobotBoundedTest() {
        assertTrue(isRobotBounded("GGLLGG"));
        assertFalse(isRobotBounded("GG"));
        assertTrue(isRobotBounded("GL"));
    }
}
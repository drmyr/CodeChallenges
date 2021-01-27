package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static models.TwoDimensionalDirection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static general.RoverControl.moveRover;

class RoverControlTest {

    @Test
    void moveRoverTest() {
        assertThat(12, is(equalTo(moveRover(4, List.of(RIGHT, UP, DOWN, LEFT, DOWN, DOWN)))));
        assertThat(8, is(equalTo(moveRover(4, List.of(RIGHT, DOWN, LEFT, LEFT, DOWN)))));
    }
}
package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningSequenceTest {

    @Test
    void generateSequence() {
        // given
        final Integer[] seq = WinningSequence.generateSequence(5, 3, 10);

        // then
        assertThat(seq, Matchers.arrayContaining(new Integer[] {9,10,9,8,7}));
    }
}
package heap;

import org.junit.jupiter.api.Test;

import static heap.RearrangeString.rearrange;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RearrangeStringTest {

    @Test
    void rearrangeTest() {
        assertThat("abab", is(equalTo(rearrange("aabb", 2))));
        assertThat("", is(equalTo(rearrange("aaaa", 2))));
        assertThat("acbacb", is(equalTo(rearrange("aacbbc", 3))));
        assertThat("egkegkesfesro", is(equalTo(rearrange("geeksforgeeks", 3))));
    }
}
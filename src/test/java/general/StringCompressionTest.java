package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.StringCompression.compress;
import static org.hamcrest.MatcherAssert.assertThat;

class StringCompressionTest {

    @Test
    void compressTest() {
        final Character[] input1 = {'a','a','b','c','c','c','b','b'};
        final Character[] output1 = compress(input1);
        assertThat(output1, Matchers.arrayContaining('a','2','b','c','3','b','2',' '));
        assertThat("objects must be the same", input1 == output1);

        final Character[] input2 = {'a','a','a','a','a','a','a','a','a','a','a','a'};
        final Character[] output2 = compress(input2);
        assertThat(output2, Matchers.arrayContaining('a','1','2',' ',' ',' ',' ',' ',' ',' ',' ',' '));
        assertThat("objects must be the same", input2 == output2);

        final Character[] input3 = {'a','b','b','c','b','b'};
        final Character[] output3 = compress(input3);
        assertThat(output3, Matchers.arrayContaining('a','b','2','c','b','2'));
        assertThat("objects must be the same", input3 == output3);
    }
}
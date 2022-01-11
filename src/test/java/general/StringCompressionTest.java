package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.StringCompression.compress;
import static org.hamcrest.MatcherAssert.assertThat;

class StringCompressionTest {

    @Test
    void compressTest() {
        assertThat(compress(new Character[] {'a','a','b','c','c','c','b','b'}), Matchers.arrayContaining('a','2','b','c','3','b','2',' '));
        assertThat(compress(new Character[] {'a','a','a','a','a','a','a','a','a','a','a','a'}), Matchers.arrayContaining('a','1','2',' ',' ',' ',' ',' ',' ',' ',' ',' '));
        assertThat(compress(new Character[] {'a','b','b','c','b','b'}), Matchers.arrayContaining('a','b','2','c','b','2'));
    }
}
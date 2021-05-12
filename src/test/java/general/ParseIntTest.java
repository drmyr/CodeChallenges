package general;

import org.junit.jupiter.api.Test;

import static general.ParseInt.parseStringToInt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ParseIntTest {

    @Test
    void parseStringToIntTest() {
        assertThat(123456789L, is(equalTo(parseStringToInt("123456789"))));
        assertThat(3L, is(equalTo(parseStringToInt("3"))));
        assertThat(76L, is(equalTo(parseStringToInt("76"))));
    }
}
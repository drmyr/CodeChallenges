package general;

import org.junit.jupiter.api.Test;

import static general.ZigzagConversion.convert;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ZigzagConversionTest {

    @Test
    void convertTest() {
        assertThat("PAHNAPLSIIGYIR", is(equalTo(convert("PAYPALISHIRING", 3))));
    }
}
package general;

import org.junit.jupiter.api.Test;

import static general.LongestSubstringWithoutRepeatingChars.lengthOfLongestSubstringBeta;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class LongestSubstringWithoutRepeatingCharsTest {

    @Test
    void lengthOfLongestSubstringTest() {
        assertThat(2, is(equalTo(lengthOfLongestSubstringBeta("ada"))));
        assertThat(7, is(equalTo(lengthOfLongestSubstringBeta("abcdefga"))));
        assertThat(1, is(equalTo(lengthOfLongestSubstringBeta("aa"))));
        assertThat(7, is(equalTo(lengthOfLongestSubstringBeta("aaabethovenddd"))));
    }
}
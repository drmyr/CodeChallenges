package general;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static general.MostCommonWord.kMostFrequentWords;
import static general.MostCommonWord.mostCommonWord;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MostCommonWordTest {

    @Test
    void mostCommonWordTest() {
        assertThat("ball", is(equalTo(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[] {"hit"}))));
    }

    @Test
    void kMostFrequentWordsTest() {
        assertThat(new String[] {"i", "love"}, Matchers.arrayContaining(kMostFrequentWords(new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2)));
        assertThat(new String[] {"the", "is", "sunny", "day"}, Matchers.arrayContaining(kMostFrequentWords(new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)));
    }
}
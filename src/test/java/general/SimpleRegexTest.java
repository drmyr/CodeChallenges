package general;

import org.junit.jupiter.api.Test;

import static general.SimpleRegex.solution;
import static org.junit.jupiter.api.Assertions.*;

class SimpleRegexTest {

    @Test
    void solutionTest() {
//        assertFalse(solution("cat", "cannot"));
//        assertTrue(solution("ca.", "caterpillar"));
//        assertTrue(solution("cat", "caterpillar"));
//        assertFalse(solution("ca.", "houseca"));
//        assertFalse(solution("cat", "houseca"));
        assertTrue(solution("..cat", "bobcat"));
//        assertTrue(solution(".cat.", "cacata"));
//        assertFalse(solution("bob", "caterpillar"));
    }
}
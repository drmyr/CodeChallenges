package general;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static general.Palindrome.isPalindrome;

class PalindromeTest {

    @Test
    void isPalindromeTest() {
        assertTrue(isPalindrome("a"));
        assertTrue(isPalindrome("racecar"));
        assertFalse(isPalindrome("turtle"));
    }
}
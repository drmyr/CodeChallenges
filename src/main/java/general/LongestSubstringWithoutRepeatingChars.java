package general;

public class LongestSubstringWithoutRepeatingChars {

    /*
     https://leetcode.com/problems/longest-substring-without-repeating-characters
     */
    public static int lengthOfLongestSubstring(final String str) {
        if(str.length() == 0) return 0;

        final int[] map = new int[256];

        int longest = 1, tail = 0, head = 0;

        while(head < str.length()) {
            if(map[str.charAt(head)] == 0) {
                map[str.charAt(head)]++;
                longest = Math.max(longest, (head-tail+1));
                head++;
            } else {
                map[str.charAt(tail)]--;
                tail++;
            }
        }

        return longest;
    }
}

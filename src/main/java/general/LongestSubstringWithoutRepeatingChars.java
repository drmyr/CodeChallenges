package general;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChars {

    /**
     * https://leetcode.com/problems/longest-substring-without-repeating-characters
     */
    public static int lengthOfLongestSubstringAlpha(final String str) {
        if(str.length() == 0) return 0;

        final int[] map = new int[256];

        int longest = 1, tail = 0, head = 0;

        while(head < str.length()) {
            if(map[str.charAt(head)] == 0) {
                map[str.charAt(head)]++;
                longest = Math.max(longest, (head - tail + 1));
                head++;
            } else {
                map[str.charAt(tail)]--;
                tail++;
            }
        }

        return longest;
    }

    public static int lengthOfLongestSubstringBeta(final String str) {
        if(str == null || str.length() < 1) return 0;

        final Map<Character, Integer> characterCount = new HashMap<>();
        characterCount.put(str.charAt(0), 1);

        int max = 1, left = 0, right = 1;
        while(right < str.length()) {
            if(characterCount.containsKey(str.charAt(right)) && characterCount.get(str.charAt(right)) > 0) {
                characterCount.put(str.charAt(left), characterCount.get(str.charAt(left)) - 1);
                left++;
            } else {
                characterCount.put(str.charAt(right), 1);
                right++;
            }
            max = Math.max(max, right - left);
        }

        return max;
    }
}

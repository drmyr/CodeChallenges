package general;

import java.util.Locale;
import java.util.Stack;

public class DecodeStringFrequency {

    /**
     * https://cybergeeksquad.co/2022/02/decode-string-frequency-amazon-oa.html
     *
     * Amazon Web Services (AWS) is working on a new security feature to help encode text. Consider a string that consists of
     * lowercase English alphabetic letters (i.e., [a-z]) only. The following rules are used to encode all of its characters into string s:
     *
     *     a is encoded as 1, b is encoded as 2, c is encoded as 3, …, and i is encoded as 9.
     *     j is encoded as 10#, k is encoded as 11#, l is encoded as 12#, …, and z is encoded as 26#.
     *     If there are two or more consecutive occurrences of any character, then the character count is written within
     *     parentheses (i.e., (c), where c is an integer denoting the count of consecutive occurrences being encoded) immediately following the encoded character. For example, consider the following string encodings:
     *         String “abzx” encoded as s = “1226#24#”.
     *         String “aabccc” is encoded as s = “1(2)23(3)”.
     *         String “bajj” is encoded as s = “2110#(2)”.
     *         String “wwxyzwww” is encoded as s = “23#(2)24#25#26#23#(3)”.
     *
     * Given an encoded string s, determine the character counts for each letter of the original, decoded string.
     * Return an integer array of length 26 where index 0 contains the number of ‘a’ characters, index 1 contains the number of ‘b’ characters and so on.
     * Input
     *
     * s: an encoded string
     * Output
     *
     * an array of 26 integers as described
     * Examples
     * Example 1:
     *
     * Input: 1s = 1226#24#
     *
     * Output: “
     *
     * Explanation:
     *
     * String “abzx” encoded as s = “1226#24#”.
     * @param input
     * @return
     */
    public static int[] stringFrequencyTwo(final String input) {
        final int[] map = new int[26];
        final Stack<Integer> stack = new Stack<>();
        for(int i = input.length() - 1; i >= 0;) {
            if(isParens(input, i)) {
                final int multiplier = getFromParens(input, i);
                stack.push(multiplier);
                while(input.charAt(i) != '(') i--;
                i--;
            } else if(isHashTag(input, i)) {
                final int number = getFromHashTag(input, i);
                i -= 3;
                map[number - 1] += stack.isEmpty() ? 1 : stack.pop();
            } else {
                final int number = Integer.parseInt(String.valueOf(input.charAt(i)));
                map[number - 1] += stack.isEmpty() ? 1 : stack.pop();
                i--;
            }
        }

        return map;
    }

    private static boolean isHashTag(final String input, final int i) {
        return input.charAt(i) == '#';
    }

    private static int getFromHashTag(final String input, final int i) {
        return Integer.parseInt(input.substring(i - 2, i));
    }

    private static boolean isParens(final String input, final int i) {
        return input.charAt(i) == ')';
    }

    private static int getFromParens(final String input, final int i) {
        int span = i - 1;
        while(input.charAt(span) != '(') {
            span--;
        }
        return Integer.parseInt(input.substring(span + 1, i));
    }
}

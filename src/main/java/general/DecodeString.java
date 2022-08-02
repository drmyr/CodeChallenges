package general;

import java.util.Stack;

public class DecodeString {

    /**
     * https://leetcode.com/problems/decode-string/
     */
    public static String decodeString(final String s) {
        final Stack<StringBuilder> letters = new Stack<>();
        letters.push(new StringBuilder());
        final Stack<Integer> mult = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))) {
                int start = i;
                while(Character.isDigit(s.charAt(start))) {
                    start++;
                }
                mult.push(Integer.parseInt(s.substring(i, start)));
                i = start;
            }
            if(s.charAt(i) == '[') {
                letters.push(new StringBuilder());
            } else if(Character.isAlphabetic(s.charAt(i))) {
                letters.peek().append(s.charAt(i));
            } else {
                final String value = letters.pop().toString().repeat(mult.pop());
                letters.peek().append(value);
            }
        }

        return letters.pop().toString();
    }
}

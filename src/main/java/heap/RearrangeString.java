package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeString {

    /**
     * https://aonecode.com/amazon-interview-questions/rearrange-string-k-distance-apart
     *
     * Given a string str and an integer k, you need to rearrange the string in a way
     * that the same characters must be at least k distance away. If not possible, return "".
     *
     * Example1:
     * Input: str = "aabb", k = 2
     * Output: You can return either "abab" or "baba".
     *
     * Example2:
     * Input: str = "aaaa", k = 2
     * Output: return ""
     */

    public static String rearrange(final String input, final int k) {
        final Map<Character, Integer> frequencyMap = new HashMap<>();
        for(final char letter : input.toCharArray()) {
            frequencyMap.merge(letter, 1, Integer::sum);
        }

        final PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(Map.Entry.<Character, Integer>comparingByValue().reversed());
        for(final Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            heap.offer(entry);
        }

        final char[] result = new char[input.length()];
        int idx = 0;
        while(!heap.isEmpty()) {
            final Map.Entry<Character, Integer> letterFreq = heap.poll();
            for(int i = 0; i < letterFreq.getValue(); i++) {
                if(idx >= input.length()) {
                    return "";
                }
                result[idx] = letterFreq.getKey();
                idx += k;
            }
            int find = 0;
            while(find < input.length()) {
                if(result[find] == '\000') {
                    idx = find;
                    break;
                } else {
                    find++;
                }
            }
        }
        return new String(result);
    }
}

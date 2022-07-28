package heap;

import java.util.*;

public class ReorderDataInLogs {

    /**
     * https://cybergeeksquad.co/2021/06/reorder-data-in-log-files-solution-amazon-oa.html
     *
     * Reorder Data in Log Files Solution
     *
     * You are given an array of logs. Each log is a space-delimited string of words, where the first word is the identifier.
     *
     * There are two types of logs:
     *
     *     Letter-logs: All words (except the identifier) consist of lowercase English letters.
     *     Digit-logs: All words (except the identifier) consist of digits.
     *
     * Reorder these logs so that:
     *
     *     The letter-logs come before all digit-logs.
     *     The letter-logs are sorted lexicographically by their contents. If their contents are the same, then sort them lexicographically by their identifiers.
     *     The digit-logs maintain their relative ordering.
     *
     * Return the final order of the logs.
     *
     * Example 1:
     *
     * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
     * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
     * Explanation:
     * The letter-log contents are all different, so their ordering is "art can", "art zero", "own kit dig".
     * The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
     *
     * Example 2:
     *
     * Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
     * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
     */

    public static String[] reorderLogs(final String[] logs) {
        final PriorityQueue<String> letterLogs = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(final String o1, final String o2) {
                final String tail1 = o1.substring(o1.indexOf(" ") + 1);
                final String tail2 = o2.substring(o2.indexOf(" ") + 1);
                if(tail1.equals(tail2)) {
                    return o1.substring(0, o1.indexOf(" ")).compareTo(o2.substring(0, o2.indexOf(" ")));
                } else {
                    return tail1.compareTo(tail2);
                }
            }
        });

        final Queue<String> digitLogs = new ArrayDeque<>();

        for(final String log : logs) {
            final String[] words = log.split(" ");
            if(words[1].matches("[0-9]+")) {
                digitLogs.offer(log);
            } else {
                letterLogs.offer(log);
            }
        }

        final List<String> result = new ArrayList<>();
        while(!letterLogs.isEmpty()) result.add(letterLogs.poll());
        while(!digitLogs.isEmpty()) result.add(digitLogs.poll());

        return result.toArray(String[]::new);
    }
}

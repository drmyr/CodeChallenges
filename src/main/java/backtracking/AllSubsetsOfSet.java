package backtracking;

import java.util.HashSet;
import java.util.Set;

public class AllSubsetsOfSet {

    /**
     * Page 233 of Skiena- The Algorithm Design Manual
     * Generates all substrings of a string.
     *
     * Generating all subsets is a 2^(n) operation.
     * The set of just the number 1 has two subsets: {} & {1} (the empty set, and the set of 1)
     * The set of the numbers 1 & 2 have 4 subsets: {}, {1}, {2}, {1,2}
     * The set of the numbers 1,2 & 3 have 8 subests: {}, {1}, {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3}
     */
    public static Set<String> generateAllSubstringsOfString(final String input) {
        final Set<String> result = new HashSet<>();
        generateSubstringsRec(result, new StringBuilder(), input, 0);
        return result;
    }

    private static void generateSubstringsRec(final Set<String> result, final StringBuilder current, final String source, final int idx) {
        result.add(current.toString());

        for(int i = idx; i < source.length(); i++) {
            current.append(source.charAt(i));
            generateSubstringsRec(result, current, source, i + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }
}

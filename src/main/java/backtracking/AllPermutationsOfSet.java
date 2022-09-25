package backtracking;

import java.util.*;

public class AllPermutationsOfSet {

    /**
     * Page 247 of Skiena - The Algorithm Design Manual
     *
     * Generating all permutations of a set is an O(n!) operation
     *
     * "There are 'n' distinct choices for the value of the first element of a permutation. Once we have fixed a1,
     *  there are n−1 candidates remaining for the second position, since we can have any value except a1 (repetitions are forbidden in permutation).
     *  Repeating this argument yields a total of n! = ∏ from i = 1 to n of i distinct permutations."
     */
    public static Set<String> allPermutationsSampleOne(final String input) {
        final Set<String> result = new HashSet<>();
        allPermutationsRec(result, input.toCharArray(), input.length());
        return result;
    }

    private static void allPermutationsRec(final Set<String> result, final char[] input, final int swapPoint) {
        if(swapPoint == 1) {
            result.add(new String(input));
            return;
        }

        for(int i = 0; i < input.length; i++) {
            swap(input, i, swapPoint - 1);
            allPermutationsRec(result, input, swapPoint - 1);
            swap(input, swapPoint - 1, i);
        }
    }

    private static void swap(final char[] input, final int a, final int b) {
        final char temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }


    public static List<String> allPermutationsSampleTwo(final String input) {
        final List<String> result = new ArrayList<>();
        allPermutationsRecTwo(0, input.toCharArray(), result);
        return result;
    }

    private static void allPermutationsRecTwo(final int swapPoint, final char[] input, final List<String> result) {
        if(swapPoint == input.length - 1) {
            result.add(new String(input));
            return;
        }

        for(int i = swapPoint; i < input.length; i++) {
            swap(input, i, swapPoint);
            allPermutationsRecTwo(swapPoint + 1, input, result);
            swap(input, i, swapPoint);
        }
    }
}

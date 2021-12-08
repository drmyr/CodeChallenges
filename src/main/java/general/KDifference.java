package general;

import models.BinaryPair;

import java.util.*;

/**
 * https://aonecode.com/twitter-oa
 */
public class KDifference {
    /**
     * Given an array of distinct integers and a target value, determine the number of distinct
     * pairs of integers in the array with an absolute difference equal to the target amount.
     * Two pairs are distinct if they differ in at least one value.
     *
     * To illustrate, the pairs (1, 3) and (3, 2) are distinct. The pairs (1, 3) and (3, 1) are not.
     * For example, given the array a = [1,3,5] and a target difference k = 2.
     * There are two pairs: [1,3] and [3,5], that have the target difference.
     *
     * Function Description
     * Complete the function kDifference in the editor below. The function must return an integer that represents
     * the number of distinct pairs in a having a difference of k.
     * kDifference has the following parameter(s);
     * a[a[0]....a[n-1]]: array of integers
     * k: the target difference
     * Constraints
     * • 5 <= n <= 10^5
     * • 0 < a[i] <= 2 * 10^9
     * • Each a[i] is distinct, i.e. unique within a.
     * • 1 <= k <= 10^9
     *
     * Sample Input :
     * [1, 5, 3, 4, 2, 2]
     *
     * Sample Output :
     * 3
     *
     * Explanation
     * Count the number of pairs in a = [1,5, 3, 4, 2] whose difference is k = 2.
     * The following three pairs meet the criterion: (1,3), (5, 3), and (4,2).
     *
     * Sample Input :
     * [363374326,364147530,61825163,107306571,128124602,139946991,428047635,491595254,879792181,106926279]
     *
     * Sample Output:
     * 0
     *
     * Explanation:
     * Count the number of pairs in a =
     * [363374326, 364147530, 61825163, 107306571, 1281246024, 139946991, 428047635, 491595254, 879792181, 106926279]
     * whose difference is k = 1. Because no such pair of integers exists in a, return 0.
     *
     * Sample Input :
     * [6,2,4,6,8,10,12,2]
     *
     *
     * Sample Output :
     * 5
     *
     * Explanation:
     * Count the number of pairs in a =
     * [2, 4, 6, 8, 10, 12] whose difference is k = 2.
     * The following five pairs meet the criterion: (2,4),(4, 6), (6,8), (8, 10), and (10, 12).
     */
    public static int[][] kDifference(final int[] inputs, final int kdiff) {
        final Set<Integer> seen = new HashSet<>();
        final Set<BinaryPair<Integer>> result = new HashSet<>();

        for(final int input : inputs) {
            final int lesser = input - kdiff;
            if(seen.contains(lesser)) {
                result.add(new BinaryPair<>(lesser, input));
            }
            final int greater = input + kdiff;
            if(seen.contains(greater)) {
                result.add(new BinaryPair<>(input, greater));
            }
            seen.add(input);
        }

        return result.stream().map(p -> new int[] {p.getLeft(), p.getRight()}).toArray(int[][]::new);
    }
}

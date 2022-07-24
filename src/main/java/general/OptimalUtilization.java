package general;

import java.util.*;

import static java.util.Comparator.comparing;

public class OptimalUtilization {

    /**
     * https://cybergeeksquad.co/2021/06/optimal-utilization-solution-amazon-oa.html
     *
     * Optimal Utilization Solution
     *
     * Given 2 lists a and b. Each element is a pair of integers where the first integer represents
     * the unique id and the second integer represents a value. Your task is to find an element from
     * a and an element form b such that the sum of their values is less or equal to target and as
     * close to target as possible. Return a list of ids of selected elements. If no pair is possible, return an empty list.
     *
     * Example 1:
     *
     * Input:
     * a = [[1, 2], [2, 4], [3, 6]]
     * b = [[1, 2]]
     * target = 7
     *
     * Output: [[2, 1]]
     *
     * Explanation:
     * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
     * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
     * @param alphaIdsAndValues
     * @param betaIdsAndValues
     * @return     *
     *     Also See: Amazon OA Online Assessment 2021 Questions and Answers
     */
    public static List<List<Integer>> optimalCombo(final int[][] alphaIdsAndValues, final int[][] betaIdsAndValues, final int target) {
        Arrays.sort(alphaIdsAndValues, comparing((final int[] arr) -> arr[1]));
        Arrays.sort(betaIdsAndValues, comparing((final int[] arr) -> arr[1]));

        final TreeMap<Integer, List<List<Integer>>> map = new TreeMap<>();
        int alpha = 0;
        int beta = betaIdsAndValues.length - 1;
        while(alpha < alphaIdsAndValues.length && beta >= 0) {
            final int sum = alphaIdsAndValues[alpha][1] + betaIdsAndValues[beta][1];
            if(sum <= target) {
                final List<List<Integer>> next = new ArrayList<>();
                next.add(List.of(alphaIdsAndValues[alpha][0], betaIdsAndValues[beta][0]));
                map.merge(sum, next, (a, b) -> { a.addAll(b); return a; });
                if(sum < target) {
                    alpha++;
                }
                if(sum == target) {
                    beta--;
                }
            }
            if(sum > target) {
                beta--;
            }
        }

        return map.floorEntry(target).getValue();
    }
}

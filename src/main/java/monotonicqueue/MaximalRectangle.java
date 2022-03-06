package monotonicqueue;

import java.util.Stack;

public class MaximalRectangle {

    /**
     * https://leetcode.com/problems/maximal-rectangle/
     *
     * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
     *
     * Example 1:
     *
     * Input: matrix =
     *  [["1","0","1","0","0"],
     *   ["1","0","1","1","1"],
     *   ["1","1","1","1","1"],
     *   ["1","0","0","1","0"]]
     * Output: 6
     * @param matrix
     * @return
     */
    public static int maximalRectangle(final char[][] matrix) {
        int max = 0;
        final int[] runningRowSum = new int[matrix[0].length];
        for (final char[] row : matrix) {
            for (int c = 0; c < row.length; c++) {
                final int value = Character.getNumericValue(row[c]);
                runningRowSum[c] = value == 0 ? 0 : runningRowSum[c] + value;
            }
            max = Math.max(max, largestAreaOfHistogramMonotonicQueue(runningRowSum));
        }

        return max;
    }

    public static int largestAreaOfHistogramMonotonicQueue(final int[] histogram) {
        final Stack<Integer> indexOfPreviousShortestColumn = new Stack<>();

        int maxArea = 0;
        for(int i = 0; i <= histogram.length; i++) {
            final int heightOrReachedEnd = i == histogram.length ? 0 : histogram[i];
            while(!indexOfPreviousShortestColumn.isEmpty() && heightOrReachedEnd < histogram[indexOfPreviousShortestColumn.peek()]) {
                final int tallerColumnIndex = indexOfPreviousShortestColumn.pop();
                final int width = i - 1 - (indexOfPreviousShortestColumn.isEmpty() ? -1 : indexOfPreviousShortestColumn.peek());
                maxArea = Math.max(maxArea, histogram[tallerColumnIndex] * width);
            }
            indexOfPreviousShortestColumn.push(i);
        }

        return maxArea;
    }
}

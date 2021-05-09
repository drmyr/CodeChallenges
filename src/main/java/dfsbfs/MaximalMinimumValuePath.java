package dfsbfs;

public class MaximalMinimumValuePath {

    /**
     * https://aonecode.com/path-with-maximum-minimum-value
     *
     * The problem is very poorly worded on the website. The problem is trying to ask "given a 2-dimensional matrix,
     * how many unique paths are there that pass through the cell of the matrix with the lowest value". A unique path cannot
     * pass through the same cell twice. So, in the following matrix:
     *
     * [7,5,3]
     * [2,0,9]
     * [4,5,9]
     *
     * how many unique paths are there that pass through [1,1], ie the '0' cell?
     *
     * Answer: there are 3 such paths:
     * [7>5>0>9>9]
     * [7>2>0>9>9]
     * [7>2>0>5>9]
     *
     * The fact that you can move in any direction makes this problem a little more difficult.
     *
     * @param matrix
     * @return
     */
    public static int maxPathScore(final int[][] matrix) {
        throw new UnsupportedOperationException();
    }
}

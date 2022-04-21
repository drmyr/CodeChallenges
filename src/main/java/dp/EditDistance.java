package dp;

import java.util.function.UnaryOperator;

public class EditDistance {

    public static int editDistance(final String src, final String dst) {
        final String emptyPaddedSrc = " " + src;
        final String emptyPaddedDst = " " + dst;
        final int[][] dpTable = new int[emptyPaddedDst.length()][emptyPaddedSrc.length()];

        // This represents the cost of taking an empty string, and inserting each of the destination
        // letters one by one.
        for(int row = 1; row < emptyPaddedDst.length(); row++) dpTable[row][0] = row;

        // This represents the cost of converting the source string into an empty string, which means it represents
        // the cost of deleting each character one by one.
        for(int col = 1; col < emptyPaddedSrc.length(); col++) dpTable[0][col] = col;

        for(int row = 1; row < emptyPaddedDst.length(); row++) {
            for(int col = 1; col < emptyPaddedSrc.length(); col++) {
                if(emptyPaddedSrc.charAt(col) == emptyPaddedDst.charAt(row)) {
                    // Because the characters are the same, the edit distance is the
                    // same as if this character did not exist
                    dpTable[row][col] = dpTable[row - 1][col - 1];
                } else {
                    final int deleteCost = dpTable[row - 1][col];
                    final int insertCost = dpTable[row][col - 1];
                    final int replaceCost = dpTable[row - 1][col - 1];
                    dpTable[row][col] = Math.min(replaceCost, Math.min(deleteCost, insertCost)) + 1;
                }
            }
        }

        return dpTable[emptyPaddedDst.length() - 1][emptyPaddedSrc.length() - 1];
    }

    public static int easierToReadEditDistance(final String str1, final String str2) {
        final String source = " " + str1;
        final String target = " " + str2;

        final int[][] dp = new int[source.length()][target.length()];

        for(int i = 0; i < source.length(); i++) dp[i][0] = i;
        for(int i = 0; i < target.length(); i++) dp[0][i] = i;

        for(int r = 1; r < source.length(); r++) {
            for(int c = 1; c < target.length(); c++) {
                if(source.charAt(r) != target.charAt(c)) {
                    dp[r][c] = Math.min(dp[r - 1][c], Math.min(dp[r - 1][c - 1], dp[r][c - 1])) + 1;
                } else {
                    dp[r][c] = dp[r - 1][c - 1];
                }
            }
        }

        return dp[source.length() - 1][target.length() - 1];
    }

    public static int editDistanceMinimumSpace(final String src, final String dest) {
        final String emptyPaddedSrc = " " + src;
        final String emptyPaddedDest = " " + dest;

        final int[] memoizationAlpha = new int[emptyPaddedSrc.length()];
        final int[] memoizationBeta = new int[emptyPaddedSrc.length()];
        for(int row = 1; row < emptyPaddedSrc.length(); row++) memoizationAlpha[row] = row;

        final UnaryOperator<int[]> getInactiveRow = (final int[] curr) ->
            curr == memoizationAlpha ? memoizationBeta : memoizationAlpha;

        int[] activeRow = memoizationBeta;
        for(int row = 1; row < emptyPaddedDest.length(); row++) {
            final int[] inactiveRow = getInactiveRow.apply(activeRow);
            for(int col = 1; col < emptyPaddedSrc.length(); col++) {
                if(emptyPaddedSrc.charAt(col) == emptyPaddedDest.charAt(row)) {
                    // Because the characters are the same, the edit distance is the
                    // same as if this character did not exist
                    activeRow[col] = inactiveRow[col - 1];
                } else {
                    activeRow[col] = Math.min(inactiveRow[col - 1], Math.min(inactiveRow[col], activeRow[col - 1])) + 1;
                }
            }
            activeRow = inactiveRow;
        }

        return getInactiveRow.apply(activeRow)[emptyPaddedSrc.length() - 1];
    }
}

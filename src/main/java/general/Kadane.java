package general;

public class Kadane {

    /**
     * https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm
     *
     * @param entries
     * @return
     */
    public static int maxSubArray(final int[] entries) {
        int max = Integer.MIN_VALUE;
        int current = 0;
        for (final int entry : entries) {
            current = Math.max(0, current + entry);
            max = Math.max(max, current);
        }
        return max;
    }
}

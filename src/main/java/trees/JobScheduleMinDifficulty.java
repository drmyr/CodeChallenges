package trees;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

public class JobScheduleMinDifficulty {

    /*
     https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/

     First attempt at the problem. Appears to be a valid solution, but too slow on medium to large inputs, and times out.

     Imperatively creates all possible job divisions for the number of given days.
    */
    public static int minDifficultyTooSlow(final int[] jobDifficulty, final int days) {
        if(jobDifficulty.length < days) return -1;
        if(jobDifficulty.length == days) return Arrays.stream(jobDifficulty).reduce(0, Integer::sum);

        // TODO this should be optimized into a Max Range Query for better runtime.
        final Function<int[], Integer> findMax = (final int[] range) -> {
            int max = 0;
            for(int i = range[0]; i <= range[1]; i++) {
                max = Math.max(max, jobDifficulty[i]);
            }
            return max;
        };

        if(days == 1) return findMax.apply(new int[] {0, (jobDifficulty.length - 1)});

        // store a range as a "tuple", consisting of the current range start (range[0], mutable value),
        // the current range end (range[1], also mutable), and the day at which this range will
        // finalize at (range[2], immutable).
        // As an example, given a job schedule of {6,5,4,3,2,1} and days = 4, the initial range division would
        // be [[0,0,2], [1,1,3], [2,2,4], [3,5,5]], as the last day has to span the range of all remaining tasks.
        // The final range division would be [[0,2,2], [3,3,3], [4,4,4], [5,5,5]], as the first day has to span the
        // range of all preceding tasks.
        final int[][] ranges = new int[days][];
        for(int i = 0; i < ranges.length; i++) {
            if(i == (ranges.length - 1)) {
                ranges[i] = new int[] {i, (jobDifficulty.length - 1), (jobDifficulty.length - days + i)};
            } else {
                ranges[i] = new int[] {i, i, jobDifficulty.length - days + i};
            }
        }

        // The head and the tail are special, as head[0] and tail[1] will always be fixed to the start
        // and end of the job range, respectively.
        final int[] head = ranges[0];
        final int[] tail = ranges[days - 1];

        final Map<String, Integer> memoization = new HashMap<>();

        int minDifficulty = Integer.MAX_VALUE;
        boolean finalRun = false;

        while(head[1] <= head[2] && !finalRun) {
            if(head[1] == head[2]) finalRun = true;
            int sumOfCurrentRangeDivisions = 0;
            for(final int[] range : ranges) {
                final String key = range[0] + ":" + range[1];
                if(memoization.containsKey(key)) {
                    sumOfCurrentRangeDivisions += memoization.get(key);
                } else {
                    final int maxOfRange = findMax.apply(range);
                    sumOfCurrentRangeDivisions += maxOfRange;
                    memoization.put(key, maxOfRange);
                }
            }
            minDifficulty = Math.min(minDifficulty, sumOfCurrentRangeDivisions);
            if(tail[0] != tail[2]) {
                tail[0]++;
                ranges[days - 2][1]++;
            } else if(days == 2) {
                // if there are only two days, then there are no ranges in between the head and the tail
                // and as such we have already tried all range combinations
                return minDifficulty;
            } else {

                // Re-adjust the range spans, incrementally moving them towards the tail
                // until all spans have been moved to their final day. This will generate all possible divisions
                // of job tasks given the number of days. This is assuredly the part of the logic that needs
                // to be optimized away by DP.

                int currentRangeToModify = days - 2;
                int[] currentRange;

                while((currentRange = ranges[currentRangeToModify]) != head) {
                    if(currentRange[0] != currentRange[2]) {
                        // move the current range to its new starting point
                        currentRange[0]++;
                        currentRange[1] = currentRange[0];

                        // move the prior range to end right behind the current range;
                        ranges[currentRangeToModify - 1][1] = currentRange[0] - 1;

                        // reset the ranges moving back toward the tail
                        while((currentRange = ranges[++currentRangeToModify]) != tail) {
                            final int newStart = ranges[currentRangeToModify - 1][1] + 1;
                            currentRange[0] = newStart;
                            currentRange[1] = newStart;
                        }

                        tail[0] = ranges[days - 2][1] + 1;

                        break;
                    } else {
                        currentRangeToModify--;
                    }
                }
            }
        }

        return minDifficulty;
    }

    /*
      Not mine, just wanted the example handy.

      https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/JavaC++Python3-DP-O(nd)-Solution/811816
     */
//    public static int minDiff(int[] jobDifficulty, int D) {
//        final int N = jobDifficulty.length;
//        if(N < D) return -1;
//        int[][] dp = new int[D][N];
//
//        dp[0][0] = jobDifficulty[0];
//        for(int j = 1; j < N; ++j){
//            dp[0][j] = Math.max(jobDifficulty[j], dp[0][j - 1]);
//        }
//
//        for(int d = 1; d < D; ++d){
//            for(int len = d; len < N; ++len){
//                int localMax = jobDifficulty[len];
//                dp[d][len] = Integer.MAX_VALUE;
//                for(int schedule = len; schedule >= d; --schedule){
//                    localMax = Math.max(localMax, jobDifficulty[schedule]);
//                    dp[d][len] = Math.min(dp[d][len], dp[d - 1][schedule - 1] + localMax);
//                }
//            }
//        }
//
//        return dp[D - 1][N - 1];
//    }

//    public int minDifficulty(int[] A, int D) {
//        int n = A.length;
//        if (n < D) return -1;
//        int[] dp = new int[n], dp2 = new int[n], tmp;
//        Arrays.fill(dp, 1000);
//        Deque<Integer> stack = new ArrayDeque<Integer>();
//
//        for (int d = 0; d < D; ++d) {
//            stack.clear();
//            for (int i = d; i < n; i++) {
//                dp2[i] = i > 0 ? dp[i - 1] + A[i] : A[i];
//                while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
//                    int j = stack.pop();
//                    dp2[i] = Math.min(dp2[i], dp2[j] - A[j] + A[i]);
//                }
//                if (!stack.isEmpty()) {
//                    dp2[i] = Math.min(dp2[i], dp2[stack.peek()]);
//                }
//                stack.push(i);
//            }
//            tmp = dp;
//            dp = dp2;
//            dp2 = tmp;
//        }
//        return dp[n - 1];
//    }
}

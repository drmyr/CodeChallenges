package dp;

import java.util.*;
import java.util.function.*;

public class JobScheduleMinDifficulty {

    /*
     https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/

     identical to

     https://aonecode.com/Amazon-Online-Assessment-Minimum-Total-Container-Size

     First attempt at the problem. Appears to be a valid solution, but too slow on medium to large inputs, and times out.

     Imperatively creates all possible job divisions for the number of given days.
    */
    public static int minDifficultyTooSlow(final int[] jobDifficulties, final int days) {
        if(jobDifficulties.length < days) return -1;
        if(jobDifficulties.length == days) return Arrays.stream(jobDifficulties).reduce(0, Integer::sum);

        // TODO this should be optimized into a Max Range Query for better runtime.
        final Function<int[], Integer> findMax = (final int[] range) -> {
            int max = 0;
            for(int i = range[0]; i <= range[1]; i++) {
                max = Math.max(max, jobDifficulties[i]);
            }
            return max;
        };

        if(days == 1) return findMax.apply(new int[] {0, (jobDifficulties.length - 1)});

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
                ranges[i] = new int[] {i, (jobDifficulties.length - 1), (jobDifficulties.length - days + i)};
            } else {
                ranges[i] = new int[] {i, i, jobDifficulties.length - days + i};
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

      The fact that this logic is correct is proof that dynamic programming is nothing but witchcraft and sorcery.
      And not that innocent Disney's Sword In The Stone kind of witchcraft and sorcery. Nope. I mean LOTR, GOT,
      Harry Potter but towards the end of the series when he gets dark and mean kind of witchcraft and sorcery.
     */
    public static int minDifficultyDP(final int[] jobDifficulties, final int days) {
        final int numberOfJobs = jobDifficulties.length;

        if(numberOfJobs < days) return -1;

        final int[][] dp = new int[days][numberOfJobs];

        dp[0][0] = jobDifficulties[0];
        for(int job = 1; job < numberOfJobs; ++job) {
            dp[0][job] = Math.max(jobDifficulties[job], dp[0][job - 1]);
        }

        for(int day = 1; day < days; ++day) {
            for(int len = day; len < numberOfJobs; ++len) {
                int localMax = jobDifficulties[len];
                dp[day][len] = Integer.MAX_VALUE;
                for(int schedule = len; schedule >= day; --schedule) {
                    // max because the difficulty equals the most difficult job done today.
                    localMax = Math.max(localMax, jobDifficulties[schedule]);
                    // min because we want the minimum difficulty.
                    dp[day][len] = Math.min(dp[day][len], dp[day - 1][schedule - 1] + localMax);
                }
            }
        }

        return dp[days - 1][numberOfJobs - 1];
    }

    /*
      Not mine, just wanted the example handy.

      https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/discuss/490316/JavaC++Python3-DP-O(nd)-Solution/811816
     */
    public static int minDifficultyStack(final int[] jobDifficulties, final int days) {
        final int numberOfJobs = jobDifficulties.length;

        if (numberOfJobs < days) return -1;

        int[] dp = new int[numberOfJobs], dp2 = new int[numberOfJobs], tmp;
        Arrays.fill(dp, 1000);

        final Deque<Integer> stack = new ArrayDeque<>();

        for (int day = 0; day < days; ++day) {
            stack.clear();
            for (int i = day; i < numberOfJobs; i++) {
                if(i > 0) {
                    dp2[i] = dp[i - 1] + jobDifficulties[i];
                } else {
                    dp2[i] = jobDifficulties[i];
                }

                while (!stack.isEmpty() && jobDifficulties[stack.peek()] <= jobDifficulties[i]) {
                    final int j = stack.pop();
                    dp2[i] = Math.min(dp2[i], dp2[j] - jobDifficulties[j] + jobDifficulties[i]);
                }
                if (!stack.isEmpty()) {
                    dp2[i] = Math.min(dp2[i], dp2[stack.peek()]);
                }
                stack.push(i);
            }
            tmp = dp;
            dp = dp2;
            dp2 = tmp;
        }
        return dp[numberOfJobs - 1];
    }
}

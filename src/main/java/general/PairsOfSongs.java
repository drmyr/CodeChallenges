package general;


public class PairsOfSongs {

    /*
      https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

      The accepted solution (from the link above) is faster, but I wanted to document my solution below.
     */
    public static int numPairsDivisibleBy60(final int[] times) {
        final int[] map = new int[60];

        for(final int time : times) {
            map[time % 60]++;
        }

        // the edge cases are at 60 and 30, where the number of pairs can be calculated by summation: n (n - 1) / 2
        // https://en.wikipedia.org/wiki/Summation
        int pairs = (map[0] * (map[0] - 1) / 2) + (map[30] * (map[30] - 1) / 2);
        int left = 1, right = 59;
        while(left < right) {
            pairs += (map[left++] * map[right--]);
        }

        return pairs;
    }
}

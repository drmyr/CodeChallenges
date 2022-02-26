package general;

public class SmallestRangeII {

    /*
        Still not the right answer
     */
    public static int smallestRangeII(final int[] nums, final int k) {
        if(nums.length < 2) return 0;

        int maxOrig = Integer.MIN_VALUE, minOrig = Integer.MAX_VALUE;
        double avg = 0.0d;
        for(final int num : nums) {
            maxOrig = Math.max(maxOrig, num);
            minOrig = Math.min(minOrig, num);
            avg += num;
        }
        avg /= nums.length;

        int maxFinal = Integer.MIN_VALUE, minFinal = Integer.MAX_VALUE;
        final int[] numsFinal = nums.clone();
        double avgDistToAvg = 0.0d;
        int contributorsToAvg = 0;
        for(int i = 0; i < nums.length; i++) {
            numsFinal[i] = numsFinal[i] < avg ? numsFinal[i] + k : numsFinal[i] - k;
            maxFinal = Math.max(maxFinal, numsFinal[i]);
            minFinal = Math.min(minFinal, numsFinal[i]);
            if(Math.abs(avg - nums[i]) >= (k * 0.1d)) {
                avgDistToAvg += Math.abs(avg - nums[i]);
                contributorsToAvg++;
            }

        }
        avgDistToAvg /= contributorsToAvg;

        if(avgDistToAvg < (k / 2d)) {
            return maxOrig - minOrig;
        } else {
            return maxFinal - minFinal;
        }
    }
}

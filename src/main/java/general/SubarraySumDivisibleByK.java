package general;

public class SubarraySumDivisibleByK {

    public int subarraysDivByK(int[] nums, int k) {

        int counter = 0;

        for(int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if(value % k == 0) {
                counter++;
            }
            for(int j = i + 1; j < nums.length; j++) {
                value += nums[j];
                if(value % k == 0) {
                    counter++;
                }
            }
        }

        return counter;
    }
}

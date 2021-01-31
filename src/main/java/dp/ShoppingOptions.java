package dp;

public class ShoppingOptions {

    /*
     https://leetcode.com/discuss/interview-question/974912/amazon-phone-find-all-combinations-of-numbers-that-sum-to-a-target
     https://aonecode.com/aplusplus/interviewctrl/getInterview/90819289

     Not 100% sure if this logic is correct, since there is only one test case.
     */
    public static int getNumberOfOptions(final int[] jeans, final int[] shoes, final int[] skirts, final int[] tops, final int budget) {
        final int itemOptions = 5; // nothing, jeans, shoes, skirts, tops
        final int[][] closet = new int[itemOptions][];
        closet[1] = jeans;
        closet[2] = shoes;
        closet[3] = skirts;
        closet[4] = tops;
        final int[][] memoization = new int[itemOptions][budget + 1];

        for(int item = 1; item < itemOptions; item++) {
            for(int money = 1; money < (budget + 1); money++) {
                memoization[item][money] = Integer.MAX_VALUE;
                for(final int cost : closet[item]) {
                    if(money < cost) {
                        memoization[item][money] = memoization[item - 1][money - 1];
                    } else {
                        final int min = Math.min(memoization[item][money], memoization[item - 1][money]);
                        memoization[item][money] = Math.min(min, memoization[item][money - 1]) + 1;
                    }
                }
            }
        }

        return memoization[itemOptions - 1][budget];
    }
}

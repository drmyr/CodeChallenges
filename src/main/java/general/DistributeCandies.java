package general;

public class DistributeCandies {

    /**
     * https://leetcode.com/problems/candy/
     *
     *
     */
    public static int candy(final int[] ratings) {
        if(ratings.length == 0) return 0;
        if(ratings.length == 1) return 1;

        int candies = ratings.length;
        int twins = 0;
        for(int i = 0; i < ratings.length; i++) {
            if(i == 0) {
                while(ratings[i] > (ratings[i + 1] + 1)) ratings[i]--;
                candies += ratings[i];
                twins += (ratings[i] == ratings[i + 1]) ? 1 : 0;
            } else if(i == ratings.length - 1) {
                while(ratings[i] > (ratings[i - 1] + 1)) ratings[i]--;
                candies += ratings[i];
            } else {
                if(ratings[i] > ratings[i - 1] && ratings[i] < ratings[i + 1]) {
                    while(ratings[i] > (ratings[i - 1] + 1)) ratings[i]--;
                }
                if(ratings[i] < ratings[i - 1] && ratings[i] > ratings[i + 1]) {
                    while(ratings[i] > (ratings[i + 1] + 1)) ratings[i]--;
                }
                twins += (ratings[i] == ratings[i + 1]) ? 1 : 0;
                candies += ratings[i];
            }
        }

        return candies - (twins / 2);
    }
}

package general;

public class EfficientHarvestMaxProfit {

    /**
     * https://cybergeeksquad.co/2022/02/max-profit-amazon-oa-solution.html
     *
     * https://leetcode.com/discuss/interview-question/1321204/efficient-harvest-faang-oa-question-2021
     *
     * An Amazon seller is deciding which of their products to invest in for the next quarter to maximize their profits.
     * They have each of their products listed as segments of a circle. Due to varying market conditions,
     * the products do not sell consistently. The seller wants to achieve maximum profit using limited resources for investment.
     * The product list is segmented into a number of equal segments, and a projected profit is calculated for each segment.
     * The projected profit is the cost to invest versus the sale price of the product. The seller has chosen to invest
     * in a number of contiguous segments along with those opposite. Determine the maximum profit the seller can achieve using this approach.
     * Amazon OA Max Profit
     *
     * For example, the product list Is divided into n = 6 sections and will select k = 2 contiguous sections and those
     * opposite to invest in. The profit estimates are profit = [1, 5, 1, 3, 7, -3] respectively. The diagrams below show
     * the possible choices with profits[0] at the 9 o’clock position and filling counterclockwise.
     *
     * The profit levels, from left to right, are 1+5+7+3=16, 5+1+7+-3=10, and 1+3+-3+1=2. The maximum profit is 16.
     * Input
     *
     *     k: an integer that denotes half of the needed number of products within the list
     *     profit: an array of integers that denote the profit from investing in each of the products
     *
     * Output
     *
     * the maximum profit possible
     * Examples
     * Example 1:
     *
     * Input:
     *
     * 1k = 2
     * 2profit = [1, 5, 1, 3, 7 -3]
     * @return
     */
    public static int maxProfit(final int kNeighbors, final int[] profitSegments) {

        int maxProfit = Integer.MIN_VALUE;
        for(int i = 0; i < profitSegments.length / 2; i++) {
            int profit = 0;
            int neighborCount = kNeighbors;
            int index = i;
            while(neighborCount > 0) {
                profit += profitSegments[index] + profitSegments[(index + (profitSegments.length / 2)) % profitSegments.length];
                neighborCount--;
                index++;
            }
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }
}

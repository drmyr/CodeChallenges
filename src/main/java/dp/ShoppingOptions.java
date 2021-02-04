package dp;

public class ShoppingOptions {

    /*
     https://leetcode.com/discuss/interview-question/974912/amazon-phone-find-all-combinations-of-numbers-that-sum-to-a-target
     https://aonecode.com/aplusplus/interviewctrl/getInterview/90819289

     Find all the unique outfits that can be made given categories of items with their associated costs.
     The total price of the outfit cannot exceed the budget, and your outfit must have one and only one item
     from each category.

     Example:
     priceOfJeans = [2, 3], priceOfShoes = [4], priceOfSkirts = [2, 3], priceOfTops = [1, 2], budgeted = 10

     Here, 4 unique outfits are possible that do not exceed the 10 dollar budget: [2,4,2,2], [2,4,2,1], [3,4,2,1], [2,4,3,1]

         money  0  1  2  3  4  5  6  7  8  9  10

         jeans [0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2]
         shoes [0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2]
        skirts [0, 0, 0, 0, 0, 0, 0, 0, 1, 3, 4]
          tops [0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4]

     */
    public static int getNumberOfOptions(final int[] jeans, final int[] shoes, final int[] skirts, final int[] tops, final int budget) {
        final int itemOptions = 4; // jeans, shoes, skirts, tops
        final int[][] closet = new int[itemOptions][];
        closet[0] = jeans;
        closet[1] = shoes;
        closet[2] = skirts;
        closet[3] = tops;

        final int[][] memoization = new int[itemOptions][budget + 1];

        for(int item = 0; item < itemOptions; item++) {
            for(int money = 1; money < (budget + 1); money++) {
                for(final int cost : closet[item]) {
                    // For the first item (jeans in the example) as long as your money exceeds the cost of the most expensive
                    // item, have the option to buy one of any of them. So, for priceOfJeans = [2, 3], if you have $2, then you
                    // only have the option to buy one of the two. If you have $3 or more, then you have the option to buy either.

                    // Since there are only two pairs of jeans in the example, then no matter how much money you have over $3
                    // you will only ever have two options: the $2 pair, or the $3 pair.

                    // The 0th row has to be singled out like this, since for item = 0, there is no other item to compare it to.
                    if(item == 0) {
                        memoization[item][money] = (money >= cost) ? memoization[item][money] + 1 : memoization[item][money];
                    } else if(money >= cost) {
                        // Because you have sufficient money (money >= cost), you have the option to buy this item, and there is
                        // only one way to buy it (because there is only one of it).
                        final int waysToBuyThisItem = 1;

                        // But now you have to look back in the DP map to see if purchasing this item would eliminate your
                        // ability to purchase a previous item.

                        // Having reduced your available money by the cost of buying this item, you have to check how many
                        // ways were there to buy the previous item, with your remaining funds.
                        final int waysToBuyPreviousItem = memoization[item - 1][money - cost];

                        // The number of ways to buy this item is then equal to the current number of ways to buy it
                        // (ie, there could be multiple pairs of skirts that you can purchase at this price point),
                        // plus the product of the ways to buy it and the ways to buy the previous item.
                        // Multiplying here is important, because if by purchasing this item you don't have enough funds to
                        // purchase the previous item, then `waysToBuyPreviousItem` will be zero in the DP map,
                        // and obviously multiplication by zero is zero.
                        memoization[item][money] = memoization[item][money] + (waysToBuyThisItem * waysToBuyPreviousItem);
                    }
                }
            }
        }

        return memoization[itemOptions - 1][budget];
    }
}

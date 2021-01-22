package general;

public class FreshPromotion {

    /*
      https://leetcode.com/discuss/interview-question/1002811/Amazon-or-OA-20201-or-Fresh-Promotion

      One of the ugliest exercises I have ever done in pointer manipulation, but the pointer manipulation
      keeps the runtime O(n) in the best case as we can effectively just iterate through the shopping cart once.

      We iterate over the contents of the shopping cart. For each shopping cart item, we check to see if it
      matches the beginning of the next group in the `codeList`. If it matches, then see if the items ahead of it match the
      rest of the current group as well, otherwise move to the next item and start again.

      The items ahead in the shopping cart matching is the best case, because once the matching is complete we can
      effectively just move our `shoppingCartIndex` pointer to the index after wherever the match completed at
      (meaning we do not have to do O(n^2) comparisons)
     */
    public static int freshPromotion(final String[][] codeList, final String[] shoppingCart) {
        final String WILDCARD = "anything";

        int groupIndex = 0;
        int shoppingCartIndex = 0;

        OUTTER:
        while(shoppingCartIndex < shoppingCart.length && groupIndex < codeList.length) {
            if(WILDCARD.equals(codeList[groupIndex][0]) || codeList[groupIndex][0].equals(shoppingCart[shoppingCartIndex])) {

                // If we made it here, it means that the shoppingCartIndex is pointing at something that is at the head
                // of the next codeList group, so iterate through the rest of the group, and see if the rest match as well.
                int localShoppingCartIndex = shoppingCartIndex + 1;
                for(int i = 1; i < codeList[groupIndex].length; i++) {
                    // if we are at the end of the shopping cart and still trying to match groups
                    // then this shopping cart content cannot be a match
                    if(localShoppingCartIndex == shoppingCart.length) return 0;

                    if(WILDCARD.equals(codeList[groupIndex][i]) || codeList[groupIndex][i].equals(shoppingCart[localShoppingCartIndex])) {
                        localShoppingCartIndex++;
                    } else {
                        // If the next item did not match, then the group will not match.
                        // As such, go to the next item in the shopping cart, and start the search over
                        // by breaking to the outter while loop.
                        shoppingCartIndex++;
                        break OUTTER;
                    }
                }

                // If we made it here, it means everything in the group matched, so move on to the next group
                groupIndex++;

                // we can now skip ahead in the list, since as we evaluated the group, we were also looking ahead
                // in the shopping cart
                shoppingCartIndex = localShoppingCartIndex;
            } else {
                // if we don't find a match, just move to the next item.
                shoppingCartIndex++;
            }
        }

        // If we successfully evaluated every group in the codeList, then the groupIndex will equal the length of the codeList
        return groupIndex == codeList.length ? 1 : 0;
    }
}

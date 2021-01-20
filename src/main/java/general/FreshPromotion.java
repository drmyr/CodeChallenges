package general;

import java.util.List;

public class FreshPromotion {

    /*
      https://leetcode.com/discuss/interview-question/1002811/Amazon-or-OA-20201-or-Fresh-Promotion

      One of the ugliest exercises I have ever done in pointer manipulation, but the pointer manipulation
      keeps the runtime O(n) in the best case as we can effectively just iterate through the shopping cart once.
     */
    public static int freshPromotion(final String[][] codeList, final String[] shoppingCart) {
        final String WILDCARD = "anything";

        int groupIndex = 0;
        int itemIndex = 0;
        int shoppingCartIndex = 0;
        OUTTER:
        while(shoppingCartIndex < shoppingCart.length && groupIndex < codeList.length) {
            if(WILDCARD.equals(codeList[groupIndex][itemIndex]) || codeList[groupIndex][itemIndex].equals(shoppingCart[shoppingCartIndex])) {
                int localShoppingCartIndex = shoppingCartIndex + 1;
                for(int i = 1; i < codeList[groupIndex].length; i++) {
                    if(localShoppingCartIndex < shoppingCart.length && (WILDCARD.equals(codeList[groupIndex][i]) || codeList[groupIndex][i].equals(shoppingCart[localShoppingCartIndex]))) {
                        localShoppingCartIndex++;
                    } else {
                        shoppingCartIndex++;
                        break OUTTER;
                    }
                }
                groupIndex++;
                shoppingCartIndex = localShoppingCartIndex;
            } else {
                shoppingCartIndex++;
            }
        }

        return groupIndex == codeList.length ? 1 : 0;
    }
}

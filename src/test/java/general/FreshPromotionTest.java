package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static general.FreshPromotion.freshPromotion;

class FreshPromotionTest {

    @Test
    void freshPromotionTest() {
        final String[][] codeGroup1 = {{ "apple", "apple" }, {"banana", "anything", "banana" }};
        final String[] shoppingCart1 = { "orange", "apple", "apple", "banana", "orange", "banana" },
                       shoppingCart2 = { "apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana" },
                       shoppingCart3 = { "banana", "orange", "banana", "apple", "apple" },
                       shoppingCart4 = { "apple", "banana", "apple", "banana", "orange", "banana" },
                       shoppingCart5 = { "apple", "apple", "apple", "banana" };

        for(final String[] cart : List.of(shoppingCart1, shoppingCart2)) {
            assertThat(1, is(equalTo(freshPromotion(codeGroup1, cart))));
        }

        for(final String[] cart : List.of(shoppingCart3, shoppingCart4, shoppingCart5)) {
            assertThat(0, is(equalTo(freshPromotion(codeGroup1, cart))));
        }
    }
}
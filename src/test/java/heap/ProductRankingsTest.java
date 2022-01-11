package heap;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static heap.ProductRankings.rankingProducts;
import static org.hamcrest.MatcherAssert.assertThat;

class ProductRankingsTest {

    @Test
    void rankingProductsTest() {
        final Map<String, Integer[]> map = new HashMap<>();
        map.put("1", new Integer[] {10, 5});
        map.put("2", new Integer[] { 3, 3});
        map.put("3", new Integer[] {17, 4});
        map.put("4", new Integer[] { 9, 4});
        map.put("5", new Integer[] { 1, 5});
        final List<String> result = rankingProducts(5, map, 1, false, 3, 1);
        assertThat(result.toArray(String[]::new), Matchers.arrayContainingInAnyOrder("2", "5"));
    }
}
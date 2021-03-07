package general;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static general.FindRelatedProducts.findRelatedProducts;
import static org.hamcrest.MatcherAssert.assertThat;

class FindRelatedProductsTest {

    @Test
    void findRelatedProductsTest() {
        final List<List<String>> graph = List.of(List.of("1", "2", "3"), List.of("5", "2"), List.of("6", "7"), List.of("8", "7"));
        final Matcher<String[]> answer = Matchers.arrayContainingInAnyOrder(List.of("1","2","3","5").toArray(String[]::new));
        assertThat(findRelatedProducts(graph).toArray(String[]::new), answer);
    }
}
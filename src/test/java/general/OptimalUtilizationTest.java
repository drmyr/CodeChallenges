package general;

import org.junit.jupiter.api.Test;

import java.util.List;

import static general.OptimalUtilization.optimalCombo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.in;
import static org.junit.jupiter.api.Assertions.*;

class OptimalUtilizationTest {

    @Test
    void optimalComboTest() {
        record Test(List<List<Integer>> answer, int[][] alpha, int[][] beta, int target) {}

        final List<Test> tests = List.of(
            new Test(List.of(List.of(2,1)), new int[][] {{1,2},{2,4},{3,6}}, new int[][] {{1,2}}, 7),
            new Test(List.of(List.of(2,4), List.of(3,2)), new int[][] {{1,3},{2,5},{3,7},{4,10}}, new int[][] {{1,2},{2,3},{3,4},{4,5}}, 10),
            new Test(List.of(List.of(3,1)), new int[][] {{1,8},{2,7},{3,14}}, new int[][] {{1,5},{2,10},{3,14}}, 20),
            new Test(List.of(List.of(1,3), List.of(3,2)), new int[][] {{1,8},{2,15},{3,9}}, new int[][] {{1,8},{2,11},{3,12}}, 20)
        );

        for(final Test test : tests) {
            final List<List<Integer>> results = optimalCombo(test.alpha(), test.beta(), test.target());
            int index = 0;
            for(final List<Integer> result : results) {
                assertThat(test.answer().get(index++), containsInAnyOrder(result.toArray()));
            }
        }
    }
}
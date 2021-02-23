package dfsbfs;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dfsbfs.FindArticulationPoints.*;
import static org.hamcrest.MatcherAssert.assertThat;

class FindArticulationPointsTest {

    @Test
    void criticalNodesTest() {
        /*
         * B -- C -- D -- E -- F -- H
         * |  /           |  /
         * A              G
         */
        final Character[][] graphOne = {{'a','b'},{'b','c'},{'a','c'},{'c','d'},{'d','e'},{'e','g'},{'g','f'},{'e','f'},{'f','h'}};
        final Matcher<Character[]> graphOneAnswer = Matchers.arrayContainingInAnyOrder('c','d','e','f');

        for(final Character proposedRoot : List.of('a', 'd', 'h', 'e')) {
            assertThat(criticalNodes(proposedRoot, graphOne), graphOneAnswer);
        }

        /*
         * 0 -- 1
         * |    |
         * 2 -- 3 -- 4
         * |
         * 5 -- 6
         */
        final Integer[][] graphTwo = {{0,1}, {0,2}, {1,3}, {2,3}, {2,5}, {5,6}, {3,4}};
        final Matcher<Integer[]> graphTwoAnswer = Matchers.arrayContainingInAnyOrder(2,3,5);

        for(final Integer proposedRoot : List.of(0, 2, 4, 6)) {
            assertThat(criticalNodes(proposedRoot, graphTwo), graphTwoAnswer);
        }

        /*
         https://leetcode.com/problems/critical-connections-in-a-network/

          2 - 0
          |  /
          | /
          1 -- 3
         */
        final Integer[][] graphThree = {{0,1},{1,2},{2,0},{1,3}};
        final Matcher<Integer[]> graphThreeAnswer = Matchers.arrayContainingInAnyOrder(1);

        for(final Integer proposedRoot : List.of(1, 2, 3)) {
            assertThat(criticalNodes(proposedRoot, graphThree), graphThreeAnswer);
        }

        /*
         https://aonecode.com/amazon-interview-questions/find-critical-nodes

               1    7
               |    |
          2 -- 3 -- 6 -- 0
               |    |
               4    5
         */
        final Integer[][] graphFour = {{2,3},{1,3},{3,4},{3,6},{7,6},{6,5},{6,0}};
        final Matcher<Integer[]> graphFourAnswer = Matchers.arrayContainingInAnyOrder(3,6);

        for(final Integer proposedRoot : List.of(0,2,3)) {
            assertThat(criticalNodes(proposedRoot, graphFour), graphFourAnswer);
        }
    }

    @Test
    void criticalEdgesTest() {
        /*
        https://aonecode.com/amazon-online-assessment-data-center-critical-connection

        1 - 2
        |  /
        | /
        3 -- 4
         */
        final Integer[][] graphOne = {{1, 2}, {1, 3}, {3, 2}, {3, 4}};

        for(final Integer proposedRoot : List.of(1,2,3)) {
            final Integer[][] result = criticalConnections(proposedRoot, graphOne);
            assertThat(result[0], Matchers.arrayContainingInAnyOrder(3,4));
        }

    }
}
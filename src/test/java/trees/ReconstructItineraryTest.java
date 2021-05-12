package trees;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static trees.ReconstructItinerary.rebuildItinerary;

class ReconstructItineraryTest {

    @Test
    void rebuildItineraryTest() {
        final String[][] itineraryOne = new String[][] {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        final String[] itineraryOneAnswer = new String[] {"JFK","MUC","LHR","SFO","SJC"};
        assertThat(rebuildItinerary(itineraryOne), Matchers.arrayContainingInAnyOrder(itineraryOneAnswer));

        final String[][] itineraryTwo = new String[][] {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        final String[] itineraryTwoAnswer = new String[] {"JFK","ATL","JFK","SFO","ATL","SFO"};
        assertThat(rebuildItinerary(itineraryTwo), Matchers.arrayContainingInAnyOrder(itineraryTwoAnswer));

        final String[][] itineraryThree = new String[][] {{"JFK","A"},{"JFK","D"},{"A","C"},{"C","D"},{"C","JFK"},{"D","B"},{"D","A"},{"B","C"}};
        final String[] itineraryThreeAnswer = new String[] {"JFK", "A", "C", "D", "B", "C", "JFK", "D", "A"};
        assertThat(rebuildItinerary(itineraryThree), Matchers.arrayContainingInAnyOrder(itineraryThreeAnswer));
    }
}
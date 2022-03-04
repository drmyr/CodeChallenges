package graphs;

import org.junit.jupiter.api.Test;

import static graphs.Dijkstra.shortestPath;
import static graphs.Dijkstra.shortestPathLength;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class DijkstraTest {

    @Test
    void shortestPathTest() {
        // https://www.youtube.com/watch?v=pVfj6mxhdMw
        final String[][] mapOne = {
            {"A","B","6"},
            {"A","D","1"},
            {"D","B","2"},
            {"D","E","1"},
            {"B","E","2"},
            {"B","C","5"},
            {"E","C","5"}
        };

        assertThat(7, is(equalTo(shortestPathLength(mapOne, "A", "C").get())));

        // https://www.happycoders.eu/algorithms/dijkstras-algorithm-java/
        final String[][] mapTwo = {
            {"G","C","2"},
            {"G","H","4"},
            {"C","A","2"},
            {"C","D","3"},
            {"A","E","3"},
            {"D","E","1"},
            {"D","F","4"},
            {"F","H","7"},
            {"H","I","3"},
            {"I","B","15"},
            {"E","B","5"}
        };
        assertThat(11, is(equalTo(shortestPathLength(mapTwo, "G", "B").get())));
        assertThat(11, is(equalTo(shortestPathLength(mapTwo, "A", "I").get())));
        assertThat(9, is(equalTo(shortestPathLength(mapTwo, "H", "D").get())));
        System.out.println(shortestPath(mapTwo, "G", "B").get());
    }
}
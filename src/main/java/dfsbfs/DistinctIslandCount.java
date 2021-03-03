package dfsbfs;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

import static java.util.Comparator.comparingInt;

public class DistinctIslandCount {

    public static int numberOfDistinctIslands(final Integer[][] map) {
        final int rowLength = map.length;
        final int colLength = map[0].length;

        class Island {
            final Integer initRow;
            final Integer initCol;
            final Set<Integer[]> bodyCoords;
            Island(final Integer initRow, final Integer initCol) {
                this.initRow = initRow;
                this.initCol = initCol;
                this.bodyCoords = new TreeSet<>(comparingInt((final Integer[] coord) -> coord[0])
                        .thenComparing((final Integer[] coord) -> coord[1]));
            }

            private void addCoord(final Integer row, final Integer col) {
                final int zeroedRow = initRow - row;
                final int zeroedCol = initCol - col;
                this.bodyCoords.add(new Integer[] {zeroedRow, zeroedCol});
            }

            @Override
            public int hashCode() {
                return this.bodyCoords.hashCode();
            }

            @Override
            public boolean equals(final Object other) {
                return (other instanceof Island) && this.bodyCoords.equals(((Island)other).bodyCoords);
            }
        }

        final Set<Island> islands = new HashSet<>();
        final boolean[][] visited = new boolean[map.length][map[0].length];
        final Integer[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        final BiPredicate<Integer, Integer> canEnter = (final Integer row, final Integer col) ->
                row >= 0 && row < rowLength && col >= 0 && col < colLength && map[row][col] == 1 && !visited[row][col];

        final BiConsumer<Integer, Integer> landFound = (final Integer row, final Integer col) -> {
            final Island island = new Island(row, col);
            final Deque<Integer[]> stack = new ArrayDeque<>();
            stack.push(new Integer[] {row, col});

            while(!stack.isEmpty()) {
                final Integer[] land = stack.pop();
                island.addCoord(land[0], land[1]);
                visited[land[0]][land[1]] = true;
                for(final Integer[] dir : dirs) {
                    final Integer newRow = row + dir[0];
                    final Integer newCol = col + dir[1];
                    if(canEnter.test(newRow, newCol)) {
                        stack.push(new Integer[] {newRow, newCol});
                    }
                }
            }
            islands.add(island);
        };

        for(Integer row = 0; row < rowLength; row++) {
            for(Integer col = 0; col < colLength; col++) {
                if(canEnter.test(row, col)) {
                    landFound.accept(row, col);
                }
            }
        }

        return islands.size();
    }
}

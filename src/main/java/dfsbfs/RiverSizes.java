package dfsbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RiverSizes {

    public static List<Integer> riverSizes(int[][] matrix) {
        final boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        final List<Integer> riverSizes = new ArrayList<>();

        final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        final Predicate<int[]> canEnter = (coords) -> {
            return coords[0] >= 0 && coords[0] < matrix.length &&
                   coords[1] >= 0 && coords[1] < matrix[coords[0]].length &&
                   !visited[coords[0]][coords[1]] && matrix[coords[0]][coords[1]] == 1;
        };

        final Consumer<int[]> exploreRiver = (coords) -> {
            final Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(coords);

            int size = 0;
            while(!queue.isEmpty()) {
                int[] next = queue.poll();
                visited[next[0]][next[1]] = true;
                size++;

                for(int[] dir : dirs) {
                    int newRow = dir[0] + next[0];
                    int newCol = dir[1] + next[1];
                    int[] newCoord = new int[] {newRow, newCol};
                    if(canEnter.test(newCoord)) {
                        queue.offer(newCoord);
                    }
                }
            }

            riverSizes.add(size);
        };

        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[r].length; c++) {
                if(matrix[r][c] == 1 && !visited[r][c]) {
                    exploreRiver.accept(new int[] {r, c});
                }
            }
        }

        return riverSizes;
    }
}

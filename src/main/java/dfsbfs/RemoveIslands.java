package dfsbfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class RemoveIslands {

    public static int[][] removeIslands(final int[][] matrix) {

        final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        final boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        final Predicate<int[]> canEnter = (dir) ->
            dir[0] >= 0 && dir[0] < matrix.length && dir[1] >= 0 &&
            dir[1] < matrix[dir[0]].length && !visited[dir[0]][dir[1]];

        final Predicate<int[]> isEdge = (dir) ->
            dir[0] == 0 || dir[0] == matrix.length - 1 ||
            dir[1] == 0 || dir[1] == matrix[dir[0]].length - 1;

        final Consumer<int[]> bredthFirstSearch = (coords) -> {
            final Queue<int[]> bfs = new ArrayDeque<>();
            final List<int[]> coordsToRemove = new ArrayList<>();
            bfs.offer(coords);
            coordsToRemove.add(coords);

            while(!bfs.isEmpty()) {
                final int[] next = bfs.poll();
                visited[next[0]][next[1]] = true;

                for(final int[] dir : dirs) {
                    final int[] newCoord = { next[0] + dir[0], next[1] + dir[1] };
                    if(matrix[newCoord[0]][newCoord[1]] == 1) {
                        if(isEdge.test(newCoord)) {
                            return;
                        }
                        if(canEnter.test(newCoord)) {
                            bfs.offer(newCoord);
                            coordsToRemove.add(newCoord);
                        }
                    }
                }
            }

            for(final int[] toRemove : coordsToRemove) {
                matrix[toRemove[0]][toRemove[1]] = 0;
            }
        };

        for(int r = 1; r < matrix.length - 1; r++) {
            for(int c = 1; c < matrix[r].length - 1; c++) {
                if(matrix[r][c] == 1 && matrix[r - 1][c] != 1) { // Don't Look Up
                    bredthFirstSearch.accept(new int[] {r, c});
                }
            }
        }

        return matrix;
    }
}

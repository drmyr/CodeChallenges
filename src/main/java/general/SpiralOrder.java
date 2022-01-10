package general;

import java.util.*;
import java.util.function.UnaryOperator;

public class SpiralOrder {

    // https://www.youtube.com/watch?v=BdQ2AkaTgOA
    public static List<Integer> spiralOrder(final int[][] matrix) {
        final int[] RIGHT = {  0,  1 },
                    LEFT  = {  0, -1 },
                    DOWN  = {  1,  0 },
                    UP    = { -1,  0 };

        final UnaryOperator<int[]> changeDirection = (final int[] dir) -> {
            if(dir == RIGHT) return DOWN;
            if(dir == DOWN) return LEFT;
            if(dir == LEFT) return UP;
            return RIGHT;
        };

        class Point {
            final int[] direction;
            final int row, col;
            Point(final int row, final int col, final int[] direction) {
                this.row = row;
                this.col = col;
                this.direction = direction;
            }

            private boolean canEnter(final Point pt, final Set<Point> visited) {
                return pt.row >= 0 && pt.row < matrix.length && pt.col >= 0 && pt.col < matrix[pt.row].length && !visited.contains(pt);
            }

            Point nextPoint(final Set<Point> visited) {
                final Point proposed = new Point(this.row + this.direction[0], this.col + this.direction[1], this.direction);
                if(canEnter(proposed, visited)) {
                    return proposed;
                } else {
                    final int[] nextProposed = changeDirection.apply(this.direction);
                    return new Point(this.row + nextProposed[0], this.col + nextProposed[1], nextProposed);
                }
            }

            @Override
            public int hashCode() {
                return Objects.hash(this.row, this.col);
            }

            @Override
            public boolean equals(final Object obj) {
                if(obj instanceof final Point other) {
                    return this.row == other.row && this.col == other.col;
                }
                return false;
            }
        }

        final Set<Point> visited = new HashSet<>();
        final List<Integer> result = new ArrayList<>();

        Point currPoint = new Point(0, -1, RIGHT);
        Point nextPoint;
        while(!visited.contains((nextPoint = currPoint.nextPoint(visited)))) {
            currPoint = nextPoint;
            result.add(matrix[currPoint.row][currPoint.col]);
            visited.add(currPoint);
        }

        return result;
    }
}

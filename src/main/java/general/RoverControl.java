package general;

import models.TwoDimensionalDirection;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class RoverControl {

    /*
      https://leetcode.com/discuss/interview-question/985703/Amazon-or-OA-or-Rover-Control
     */
    public static int moveRover(final int matrixSize, final List<TwoDimensionalDirection> directions) {

        final BiPredicate<Integer, Integer> canEnter = (final Integer row, final Integer col) ->
            row >= 0 && row < matrixSize && col >= 0 && col < matrixSize;

        final Function<TwoDimensionalDirection, int[]> directionToCoords = (final TwoDimensionalDirection direction) ->
            switch (direction) {
                case UP    -> new int[] { -1,  0 };
                case DOWN  -> new int[] {  1,  0 };
                case LEFT  -> new int[] {  0, -1 };
                case RIGHT -> new int[] {  0,  1 };
            };

        final int[] currentPosition = new int[] {0, 0};
        for(final TwoDimensionalDirection direction : directions) {
            final int[] move = directionToCoords.apply(direction);
            final int newRow = currentPosition[0] + move[0];
            final int newCol = currentPosition[1] + move[1];
            if(canEnter.test(newRow, newCol)) {
                currentPosition[0] = newRow;
                currentPosition[1] = newCol;
            }
        }
        return (currentPosition[0] * matrixSize) + currentPosition[1];
    }
}

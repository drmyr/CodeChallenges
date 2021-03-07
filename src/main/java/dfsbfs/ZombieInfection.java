package dfsbfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ZombieInfection {

    /**
     * https://aonecode.com/amazon-online-assessment-zombie-matrix
     *
     * Given a 2D grid, each cell is either a zombie or a human.
     * Zombies can turn adjacent (up/down/left/right) human beings
     * into zombies every day. Find out how many days does it take to infect all humans?
     *
     * @param grid
     * @return
     */
    public int daysTilTotalInfection(final int[][] grid) {
        final Deque<int[]> alphaQueue = new ArrayDeque<>();
        final Deque<int[]> betaQueue = new ArrayDeque<>();

        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[r].length; c++) {
                if(grid[r][c] == 1) alphaQueue.offer(new int[] {r,c});
            }
        }

        final Predicate<int[]> canEnter = (final int[] cellOfMatrix) -> {
            final int row = cellOfMatrix[0];
            final int col = cellOfMatrix[1];
            return row >= 0 && row < grid.length &&
                    col >= 0 && col < grid[row].length &&
                    grid[row][col] != 1;
        };

        final UnaryOperator<Deque<int[]>> getInactiveQueue =
                (final Deque<int[]> active) -> active.equals(alphaQueue) ? betaQueue : alphaQueue;

        final int[][] dirs = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

        int days = 0;
        Deque<int[]> activeQueue = alphaQueue;
        while(!activeQueue.isEmpty()) {
            final Deque<int[]> inactiveQueue = getInactiveQueue.apply(activeQueue);
            final int[] nextCell = activeQueue.poll();
            for(final int[] dir : dirs) {
                final int[] newInfection = new int[] {(nextCell[0] + dir[0]), (nextCell[1] + dir[1])};
                if(canEnter.test(newInfection)) {
                    inactiveQueue.offer(newInfection);
                    grid[newInfection[0]][newInfection[1]] = 1;
                }
            }
            if(activeQueue.isEmpty()) {
                activeQueue = inactiveQueue;
                if(!alphaQueue.isEmpty() || !betaQueue.isEmpty()) days++;
            }
        }
        return days;
    }
}

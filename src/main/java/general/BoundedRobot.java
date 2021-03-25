package general;

import java.util.function.BinaryOperator;

public class BoundedRobot {

    /**
     * https://leetcode.com/problems/robot-bounded-in-circle/
     *
     * @param instructions
     * @return
     */
    public static boolean isRobotBounded(final String instructions) {
        final char N = 'N', E = 'E', S = 'S', W = 'W';

        final BinaryOperator<Character> changeDirection = (final Character currentDir, final Character instruction) -> {
            if(instruction == 'L') {
                if(currentDir == N) return W;
                if(currentDir == W) return S;
                if(currentDir == S) return E;
            } else {
                if(currentDir == N) return E;
                if(currentDir == E) return S;
                if(currentDir == S) return W;
            }
            return N;
        };

        char dir = N;
        final Integer[] coord = new Integer[] {0, 0};
        for(int i = 0; i < instructions.length(); i++) {
            final char instruction = instructions.charAt(i);
            if(instruction == 'G') {
                if(dir == N) coord[1]++;
                if(dir == E) coord[0]++;
                if(dir == S) coord[1]--;
                if(dir == W) coord[0]--;
            } else {
                dir = changeDirection.apply(dir, instruction);
            }
        }

        return coord[0] == 0 && coord[1] == 0 || dir != N;
    }
}

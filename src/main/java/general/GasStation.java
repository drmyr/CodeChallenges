package general;

import java.util.TreeSet;

import static java.util.Comparator.comparing;

public class GasStation {

    /**
     * https://leetcode.com/problems/gas-station/
     *
     * There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
     *
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to
     * its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
     *
     * Given two integer arrays gas and cost, return the starting gas station's index if you can travel
     * around the circuit once in the clockwise direction, otherwise return -1.
     * If there exists a solution, it is guaranteed to be unique
     *
     *
     */
    public static int canCompleteCircuit(final int[] gas, final int[] cost) {
        int totalCost = 0;
        int surplusGas = 0;
        int start = 0;
        for(int i = 0; i < gas.length; i++) {
            final int thisRun = (gas[i] - cost[i]);
            totalCost += thisRun;
            surplusGas += thisRun;
            if(surplusGas < 0) {
                start++;
                surplusGas = 0;
            }
        }

        return totalCost < 0 ? -1 : start;
    }
}

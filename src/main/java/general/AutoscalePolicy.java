package general;

public class AutoscalePolicy {

    /**
     * A scaling computing system checks its average utilization every second while it monitors.
     * It implements an autoscale policy to increase or reduce instances depending on the current load as described below.
     * Once an action of increasing or reducing the number of instances is performed, the system will stop monitoring for 10 seconds.
     * During that time, the number of instances does not change.
     *
     *     Also See: Amazon OA Online Assessment 2021 Questions and Answers
     *
     *     If the average utilization < 25%, then an action is instantiated to reduce the number of instances by half
     *     if the number of instances is greater than 1. Take the ceiling if the number is not an integer.
     *     If the number of instances is 1, take no action.
     *     If 25% s average utilization s 60%, take no action.
     *     If the average utilization > 60%, then an action is instantiated to double the number of instances
     *     if the doubled value does not exceed 2* 108. If the number of instances exceeds this limit upon doubling, take no action.
     */
    public static int computeScalePolicy(final int initialInstances, final int[] averageLoad) {
        int instanceCount = initialInstances;
        for(int i = 0; i < averageLoad.length; i++) {
            if(averageLoad[i] < 25) {
                instanceCount = Math.max(1, instanceCount / 2);
                i += 10;
                continue;
            }
            if(averageLoad[i] > 60 && instanceCount * 2 < (2 * 108)) {
                instanceCount *= 2;
            }
        }
        return instanceCount;
    }
}

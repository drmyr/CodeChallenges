package general;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ThrottlingGateway {

    /**
     * https://aonecode.com/amazon-online-assessment-throttling-gateway
     *
     * A planetarium has multiple entrances. Only the special Entrance X has cable cars carrying visitors into
     * the planetarium while other entrances have walking tunnels. Everyone visiting the planetarium prioritizes entering from Entrance X.
     * An empty cable car arrives every minute at Entrance X and takes at most 3 passengers.
     *
     * For safety and better user experience, Entrance X has the following constraints:
     * The number of visitors on a cable car in any given minute cannot exceed 3.
     * The number of visitors going though Entrance X in any given 10-minute period cannot exceed 20.
     * A ten-minute period includes all visitors arriving from any time max(1, T-9) to T (inclusive of both) for any valid time T.
     * The number of visitors in any given hour cannot exceed 60. Similar to above, 1 hour is from max(1, T-59) to T.
     * Any visitor that exceeds any of the above limits will be assigned to other entrances instantly.
     * Given the times at which different visitors arrive sorted ascending, write an algorithm to find how many
     * people will be assigned to other entrances.
     *
     * Input
     * The input to the function consists of two arguments:
     * num, an integer representing the total number of visitors at X;
     * arriveTime, a list of integers representing the times of various visitor arrivals.
     *
     * Output
     * Return an integer representing the total number of visitors NOT entering through Entrance X.
     *
     * Note
     * Even if a visitor is assigned to other entrances, he/she is still considered for future calculations.
     * Although, if a visitor is to be re-assign due to multiple constraints, he/she is still counted only once.
     */
    public static int cableCarThrottling(final int visitorCount, final int[] arrivalTimes) {
        int minute = 1;
        int index = 0;
        int carCount = 0;

        int tenMinWindowCount = 0;
        final Map<Integer, Integer> tenMinMap = new HashMap<>();

        int sixtyMinWindowCount = 0;
        final Map<Integer, Integer> sixtyMinMap = new HashMap<>();

        int rejected = 0;
        while(index < arrivalTimes.length) {
            while(index < arrivalTimes.length && arrivalTimes[index] == minute) {
                if(carCount < 3 && tenMinWindowCount < 20 && sixtyMinWindowCount < 60) {
                    carCount++;
                } else {
                    rejected++;
                }
                index++;
                tenMinMap.merge(minute, 1, Integer::sum);
                tenMinWindowCount++;
                sixtyMinMap.merge(minute, 1, Integer::sum);
                sixtyMinWindowCount++;
            }
            minute++;
            carCount = 0;

            if(minute > 10) {
                tenMinWindowCount -= tenMinMap.containsKey(minute - 10) ? tenMinMap.remove(minute - 10) : 0;
            }
            if(minute > 60) {
                sixtyMinWindowCount -= sixtyMinMap.containsKey(minute - 60) ? sixtyMinMap.remove(minute - 60) : 0;
            }
        }

        return rejected;
    }
}

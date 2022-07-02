package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static List<int[]> fourNumberSum(final int[] array, final int targetSum) {
        List<int[]> result = new ArrayList<>();

        if(array.length < 4) return result;

        Arrays.sort(array);

        int first = 0, second = 1, third = 2, fourth = 3;

        while(first < array.length - 4) {
            int sum = array[first] + array[second] + array[third] + array[fourth];
            if(sum == targetSum) {
                result.add(new int[] {array[first], array[second], array[third], array[fourth]});
            }

            fourth++;
            if(fourth > array.length - 1) {
                third++;
                fourth = third + 1;
            }
            if(third > array.length - 2) {
                second++;
                third = second + 1;
                fourth = third + 1;
            }
            if(second > array.length - 3) {
                first++;
                second = first + 1;
                third = second + 1;
                fourth = third + 1;
            }
        }

        return result;
    }
}

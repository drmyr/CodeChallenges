package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletSumsToZero {

    public static Integer[][] findAllTripletsThatSumToZero(final int[] array) {
        Arrays.sort(array);
        final List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < array.length - 2; i++) {
            int l = i + 1;
            int r = array.length - 1;
            while(r > l) {
                final int sum = array[i] + array[l] + array[r];
                if(sum == 0) {
                    result.add(List.of(array[i], array[l], array[r]));
                    l++;
                    r--;
                } else if(sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return result.stream().map(list -> list.toArray(Integer[]::new)).toArray(Integer[][]::new);
    }
}

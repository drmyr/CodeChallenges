package general;

public class WinningSequence {


    public static Integer[] generateSequence(final int range, final int low, final int high) {
        int start = 1;
        int rangeCheck = range - 1;
        while((high - rangeCheck) < low) {
            start++;
            rangeCheck--;
        }

        int value = high;
        final Integer[] result = new Integer[range];
        for(int i = start; i < result.length; i++) {
            result[i] = value--;
        }
        value = high - 1;
        for(int i = start - 1; i >= 0; i--) {
            result[i] = value--;
        }

        return result;
    }
}

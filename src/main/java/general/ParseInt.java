package general;

import java.util.function.Function;

public class ParseInt {

    public static long parseStringToInt(final String number) {
        final Function<Character, Long> charToInt = (final Character character) ->
            switch (character) {
                case '0' -> 0L;
                case '1' -> 1L;
                case '2' -> 2L;
                case '3' -> 3L;
                case '4' -> 4L;
                case '5' -> 5L;
                case '6' -> 6L;
                case '7' -> 7L;
                case '8' -> 8L;
                default -> 9L;
        };

        long value = 0L;
        long power = 1L;
        for(int i = number.length() - 1; i >= 0; i--) {
            value += (charToInt.apply(number.charAt(i)) * power);
            power *= 10L;
        }

        return value;
    }
}

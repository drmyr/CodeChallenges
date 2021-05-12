package general;

import java.util.function.Function;

public class ParseInt {

    public static int parseStringToInt(final String number) {
        final Function<Character, Integer> charToInt = (final Character character) ->
            switch (character) {
                case '0' -> 0;
                case '1' -> 1;
                case '2' -> 2;
                case '3' -> 3;
                case '4' -> 4;
                case '5' -> 5;
                case '6' -> 6;
                case '7' -> 7;
                case '8' -> 8;
                default -> 9;
        };

        int value = 0;
        int power = 1;
        for(int i = number.length() - 1; i >= 0; i--) {
            value += (charToInt.apply(number.charAt(i)) * power);
            power *= 10;
        }

        return value;
    }
}

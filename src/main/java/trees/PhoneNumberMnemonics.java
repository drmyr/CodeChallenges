package trees;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public class PhoneNumberMnemonics {

    private static final Map<String, String[]> keypadMap = Map.of(
        "0", new String[] {"0"},
        "1", new String[] {"1"},
        "2", new String[] {"a","b","c"},
        "3", new String[] {"d","e","f"},
        "4", new String[] {"g","h","i"},
        "5", new String[] {"j","k","l"},
        "6", new String[] {"m","n","o"},
        "7", new String[] {"p","q","r","s"},
        "8", new String[] {"t","u","v"},
        "9", new String[] {"w","x","y","z"}
    );

    public static List<String> generateMnemonicsRecursive(final String phoneNumber) {
        final List<String> result = new ArrayList<>();
        rec(phoneNumber, 0, "", result);
        return result;
    }

    private static void rec(final String phoneNumber, final int idx, final String current, final List<String> result) {
        if(idx == phoneNumber.length()) {
            result.add(current);
        } else {
            for(final String letter : keypadMap.get(String.valueOf(phoneNumber.charAt(idx)))) {
                rec(phoneNumber, idx + 1, current + letter, result);
            }
        }
    }

    public static List<String> generateMnemonicsImperative(final String phoneNumber) {

        final Stack<String> stack = new Stack<>();
        for(final String letter : keypadMap.get(String.valueOf(phoneNumber.charAt(0)))) stack.push(letter);

        final List<String> result = new ArrayList<>();

        while(!stack.isEmpty()) {
            final String top = stack.peek();
            if(top.length() == phoneNumber.length()) {

                while(!stack.isEmpty() && stack.peek().length() == phoneNumber.length()) result.add(stack.pop());

                String pop;
                do {
                    pop = stack.isEmpty() ? "" : stack.pop();
                } while (!stack.isEmpty() && pop.length() > stack.peek().length());

            } else {
                for(final String letter : keypadMap.get(String.valueOf(phoneNumber.charAt(top.length())))) {
                    stack.push(top + letter);
                }
            }
        }

        return unmodifiableList(result);
    }
}

package general;

import java.util.*;

public class BalancedBrackets {

    public static boolean balancedBrackets(final String str) {
        final Stack<Character> stack = new Stack<>();
        final Set<Character> contains = Set.of('(', '[', '{', ')', ']', '}');
        final Map<Character, Character> map = Map.of('(', ')', '[', ']', '{', '}');

        for (int i = 0; i < str.length(); i++) {
            final char next = str.charAt(i);
            if (!contains.contains(next)) {
                continue;
            }
            if (map.containsKey(next)) {
                stack.push(next);
            } else {
                if (stack.isEmpty()) return false;
                final char pair = stack.pop();
                if (!map.get(pair).equals(next)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
package general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {

    public static List<String> partition(final String src) {
        final Map<Character, Integer> lastIndexOf = new HashMap<>();
        for(int i = 0; i < src.length(); i++) {
            lastIndexOf.put(src.charAt(i), i);
        }

        final List<String> result = new ArrayList<>();

        int start = 0;
        int end = 0;
        for(int i = 0; i < src.length(); i++) {
            final int current = lastIndexOf.get(src.charAt(i));
            end = Math.max(end, current);
            if(end == i) {
                result.add(src.substring(start, end));
                start = end + 1;
            }
        }
        return result;
    }
}

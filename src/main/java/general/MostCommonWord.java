package general;

import java.util.*;
import java.util.function.Function;

import static java.util.Map.entry;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class MostCommonWord {

    public static String mostCommonWord(final String paragraph, final String[] banned) {
        final Set<String> bannedSet = Arrays.stream(banned).collect(toSet());
        return Arrays.stream(paragraph.split("\\W+")).filter(word -> !bannedSet.contains(word))
            .map(String::toLowerCase).collect(toMap(Function.identity(), word -> 1, Integer::sum, HashMap::new))
            .entrySet().stream().max(Map.Entry.comparingByValue()).orElseGet(() -> entry("", 0)).getKey();
    }

    public static String[] kMostFrequentWords(final String[] words, final int k) {
        final TreeSet<String> mostFrequent = new TreeSet<>();
        final Map<String, Integer> frequencyCount = new HashMap<>();

        for(final String word : words) {
            frequencyCount.merge(word, 1, Integer::sum);
            if(frequencyCount.get(word) >= k) {
                mostFrequent.add(word);
            }
        }

        return mostFrequent.toArray(String[]::new);
    }
}

package general;

import java.util.*;
import java.util.function.Consumer;

public class TransactionLogs {

    /*
     https://aonecode.com/amazon-online-assessment-transaction-logs
     */
    public static String[] processLogFile(final String[] logs, final int threshold) {
        final Map<String, Integer> userFrequencyMap = new HashMap<>();
        final Set<String> usersPastThreshold = new HashSet<>();

        final Consumer<String> entryUpdater = (final String entry) -> {
            final Integer frequency = userFrequencyMap.getOrDefault(entry, 0) + 1;
            if(frequency >= threshold) usersPastThreshold.add(entry);
            userFrequencyMap.put(entry, frequency);
        };

        for(final String log : logs) {
            final String[] entry = log.split(" ");
            if(!entry[0].equals(entry[1])) {
                entryUpdater.accept(entry[0]);
                entryUpdater.accept(entry[1]);
            }
        }

        return usersPastThreshold.toArray(String[]::new);
    }
}

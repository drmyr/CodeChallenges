package general;

import java.util.*;

import static java.util.Collections.emptySet;

public class FindRelatedProducts {

    /**
     * https://aonecode.com/amazon-online-assessment-find-related-books
     *
     * This question is based on the product recommendation system on Amazon.
     * Every time you open the a product page on Amazon you can see a section
     * “People who viewed this also viewed”. Now given a product relationship
     * represented as a graph(adjacent list), find out the largest connected component on this graph...
     *
     * @param graph
     * @return
     */
    public static List<String> findRelatedProducts(final List<List<String>> graph) {
        final Map<String, Set<String>> disjointSetMap = new HashMap<>();

        Set<String> largestSet = emptySet();
        for(final List<String> set : graph) {
            final Set<String> newSet = new HashSet<>();
            for(final String entry : set) {
                final Set<String> disjointSet = disjointSetMap.get(entry);
                if(disjointSet == null) {
                    newSet.add(entry);
                    disjointSetMap.put(entry, newSet);
                } else {
                    newSet.addAll(disjointSet);
                    for(final String existingEntry : disjointSet) {
                        disjointSetMap.put(existingEntry, newSet);
                    }
                }
                if(newSet.size() > largestSet.size()) {
                    largestSet = newSet;
                }
            }
        }

        return new ArrayList<>(largestSet);
    }
}

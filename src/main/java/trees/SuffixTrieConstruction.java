package trees;

import java.util.HashMap;
import java.util.Map;

public class SuffixTrieConstruction {

    static class TrieNode {
        final Map<Character, TrieNode> children = new HashMap<>();
    }

    static class SuffixTrie {
        final TrieNode root = new TrieNode();
        final char endSymbol = '*';

        public SuffixTrie(final String str) {
            populateSuffixTrieFrom(str);
        }

        public void populateSuffixTrieFrom(final String str) {
            int index = 0;
            while(index < str.length()) {
                final char[] letters = str.substring(index).toCharArray();
                TrieNode next = root.children.computeIfAbsent(letters[0], (ignore) -> new TrieNode());
                for(int i = 1; i < letters.length; i++) {
                    next = next.children.computeIfAbsent(letters[i], (ignore) -> new TrieNode());
                }
                next.children.put(endSymbol, null);
                index++;
            }
        }

        public boolean contains(final String str) {
            int index = 0;
            Map<Character, TrieNode> map = root.children;
            while(index < str.length()) {
                if(!map.containsKey(str.charAt(index))) {
                    return false;
                }
                map = map.get(str.charAt(index)).children;
                index++;
            }
            return map.containsKey(endSymbol);
        }
    }
}

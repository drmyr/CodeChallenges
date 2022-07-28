package general;

public class CountingBinarySubstrings {

    /**
     * https://cybergeeksquad.co/2022/02/counting-binary-substrings-amazon-oa.html
     *
     * Kindle Direct Publishing, Amazon’s e-book self-publishing platform, is working on a new feature to help authors
     * track the use of text strings in different ways. A substring is a group of contiguous characters in a string.
     * For instance, all substring of abc are [a, b, c, ab, bc, abc].
     *
     * Given a binary representation of a number, determine the total number of substring present that match the following conditions:
     *
     *     The 0s and 1s are grouped consecutively (e.g., 01, 10, 0011, 1100, 000111, etc.).
     *     The number of 0s in the substring is equal to the number of 1s in the substring.
     *
     * Input
     *
     *     s: a string representation of a binary integer
     *
     * Output
     *
     * the number of substrings of s that satisfy the two conditions
     * Examples
     * Example 1:
     *
     * Input: 1s = 001101
     *
     * Output: 4
     *
     * Explanation:
     *
     * The 4 substrings matching the two conditions include [0011, 01, 10, 01]. Note that 01 appears twice, from indices 1-2 and 4-5.
     * There are other substrings, e.g. 001 and 011 that match the first condition but not the second.
     * @param binaryString
     * @return
     */
    public static int countBinarySubstrings(final String binaryString) {
        if(binaryString.length() < 2) return 0;

        int count = 0;
        for(int i = 0; i < binaryString.length() - 1; i++) {
            if(zerosLeftOnesRight(i, i + 1, binaryString)) {
                count++;
                int left = i - 1;
                int right = i + 2;
                while(left >= 0 && right < binaryString.length()) {
                    if(zerosLeftOnesRight(left, right, binaryString)) {
                        count++;
                    }
                    left--;
                    right++;
                }
            }
            if(onesLeftZerosRight(i, i + 1, binaryString)) {
                count++;
                int left = i - 1;
                int right = i + 2;
                while(left >= 0 && right < binaryString.length()) {
                    if(onesLeftZerosRight(left, right, binaryString)) {
                        count++;
                    }
                    left--;
                    right++;
                }
            }
        }

        return count;
    }

    private static boolean zerosLeftOnesRight(final int indexA, final int indexB, final String source) {
        return (source.charAt(indexA) == '0' && source.charAt(indexB) == '1');
    }

    private static boolean onesLeftZerosRight(final int indexA, final int indexB, final String source) {
        return (source.charAt(indexA) == '1' && source.charAt(indexB) == '0');
    }
}

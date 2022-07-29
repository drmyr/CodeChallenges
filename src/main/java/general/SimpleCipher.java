package general;

public class SimpleCipher {

    /**
     * https://cybergeeksquad.co/2022/02/simple-cipher-amazon-oa-solution.html
     *
     * As part of a Day 1 Challenge, your new team at Amazon has created a basic alphabet-based encryption and has
     * asked members to test the cipher. A simple cipher is built on the alphabet wheel which has uppercase english letters[‘A’-‘Z’] written on it:
     *
     * Given an encrypted string consisting of english letters[‘A’-‘Z’] only, decrypt the string by replacing each
     * character with the kth character away on the wheel in counter clockwise direction. Counter-clockwise is the
     * opposite direction is which the hands on a clock usually move.
     * Input
     *
     *     encryped: a string
     *     k: an integer
     *
     * Output
     *
     * the decrypted string
     * Examples
     * Example 1:
     *
     * Input:
     *
     * 1encryped = VTAOG
     * 2k = 2
     *
     * Output: TRYME
     *
     * Explanation:
     *
     * Looking back 2 from ‘V’ returns ‘T’, from ‘T’ returns ‘R’ and so on. The decrypted string is ‘TRYME’.
     *
     * encrypted string will only be uppercase
     */

    public static String decrypt(final int offset, final String encrypted) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < encrypted.length(); i++) {
            final int newChar = encrypted.charAt(i) - offset;
            sb.append(newChar < 65 ? (char)(newChar + 26) : (char)newChar);
        }

        return sb.toString();
    }
}

package dp;

public class MinimumCoinFlips {

    /**
     * https://leetcode.com/problems/flip-string-to-monotone-increasing/
     *
     * https://cybergeeksquad.co/2022/02/minimum-coin-flips-amazon-oa.html
     *
     * Given the initial sequence of a coins, find the minimum number of coins that can be flipped to obtain a beautiful sequence.
     * All head facing coins or tails facing coins sequence is also valid.
     *
     * Input : THHHTH
     * Output : 2
     * Explanation : flip first and last coin to obtain the sequence.
     *
     * https://www.reddit.com/r/learnprogramming/comments/qt3y7h/help_me_approach_this_problem_the_beautiful_coin/
     *
     * Best explanation I have found thus far:
     * "This is a dynamic programming problem. The way to think about this to recognize we can have all H, all T, or a mix of H and T.
     * So that means we can iterate through the string and when we see a T we keep it's count b/c we want T's at the end of the string.
     * But when we encounter an H we have a decision to make, should we flip the H or should we flip the number of T's we've seen so far.
     *
     * Essentially, keep a count for T and a count for flip.
     *
     * See a T increment count. See an H increment flip and take the minimum of either the current flip count or the T count. Return that final flip count at the end."
     *
     * credit unknown
     * @param headsTailsList
     * @return
     */
    public static int minimumFlipsMonotoneIncreasing(final String headsTailsList) {
        int tailCount = 0, flipDecision = 0;

        for(int i = 0; i < headsTailsList.length(); i++) {
            if(headsTailsList.charAt(i) == 'T') {
                tailCount++;
            } else {
                flipDecision++;
            }

            flipDecision = Math.min(tailCount, flipDecision);
        }

        return flipDecision;
    }
}

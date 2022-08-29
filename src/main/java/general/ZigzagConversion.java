package general;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion {

    /**
     * https://leetcode.com/problems/zigzag-conversion/
     *
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     * (you may want to display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * And then read line by line: "PAHNAPLSIIGYIR"
     *
     * Write the code that will take a string and make this conversion given a number of rows:
     *
     * string convert(string s, int numRows);
     *
     * Example 1:
     *
     * Input: s = "PAYPALISHIRING", numRows = 3
     * Output: "PAHNAPLSIIGYIR"
     *
     * Example 2:
     *
     * Input: s = "PAYPALISHIRING", numRows = 4
     * Output: "PINALSIGYAHRPI"
     * Explanation:
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     */
    public static String convert(final String s, final int numRows) {
        final List<StringBuilder> stringBuilderList = new ArrayList<>();
        for(int i = 0; i < numRows; i++) stringBuilderList.add(new StringBuilder());

        boolean direction = true; //true = down, false = up;
        int rowIdx = 0;
        for(int i = 0; i < s.length(); i++) {
            stringBuilderList.get(rowIdx).append(s.charAt(i));
            if(direction) {
                if(rowIdx < numRows - 1) {
                    rowIdx++;
                } else {
                    direction = false;
                    rowIdx--;
                }
            } else {
                if(rowIdx > 0) {
                    rowIdx--;
                } else {
                    direction = true;
                    rowIdx++;
                }
            }
        }
        return stringBuilderList.stream().reduce(new StringBuilder(), (sb1, sb2) -> { sb1.append(sb2); return sb1; }).toString();
    }
}

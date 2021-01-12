package round2.string;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/23/2020 8:32 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/zigzag-conversion/
 * @description
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 *
 *
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 *
 * Input: s = "A", numRows = 1
 * Output: "A"
 *
 */
public class L6_ZigZagConversion {
    public String convert(String s, int numRows) {
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }
        char[] c = s.toCharArray();
        int i = 0, n = s.length();
        while (i < n) {
            int verticalI = 0;
            while (i < n && verticalI < numRows) {
                sb[verticalI].append(c[i++]);
                verticalI++;
            }
            int obliqueI = numRows - 2;
            while (i < n && obliqueI >= 1) {
                sb[obliqueI].append(c[i++]);
                obliqueI--;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuffer line : sb) {
            res.append(line);
        }
        return res.toString();
    }
    @Test
    public void test() {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
}

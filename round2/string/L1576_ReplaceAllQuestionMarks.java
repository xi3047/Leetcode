package round2.string;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 2/2/21 1:13 上午
 * @topic round2.string
 * @link https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 * @description
 * Given a string s containing only lower case English letters and the '?' character, convert all the '?' characters
 * into lower case letters such that the final string does not contain any consecutive repeating characters. You cannot modify the non '?' characters.
 *
 * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
 *
 * Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution,
 * return any of them. It can be shown that an answer is always possible with the given constraints.
 *
 * Example 1:
 *
 * Input: s = "?zs"
 * Output: "azs"
 * Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid. Only "z" is an invalid
 * modification as the string will consist of consecutive repeating characters in "zzs".
 * Example 2:
 *
 * Input: s = "ubv?w"
 * Output: "ubvaw"
 * Explanation: There are 24 solutions for this problem. Only "v" and "w" are invalid modifications as the strings
 * will consist of consecutive repeating characters in "ubvvw" and "ubvww".
 * Example 3:
 *
 * Input: s = "j?qg??b"
 * Output: "jaqgacb"
 * Example 4:
 *
 * Input: s = "??yw?ipkj?"
 * Output: "acywaipkja"
 */
public class L1576_ReplaceAllQuestionMarks {
    public String modifyString(String s) {
        char[] charArray = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (charArray[i] == '?') {
                for (int j = 'a'; j < 'z'; j++) {
                    if (i > 0 && charArray[i - 1] == j) continue;
                    if (i < n - 1 && charArray[i + 1] == j) continue;
                    charArray[i] = (char) j;
                    break;
                }
            }
        }
        return new String(charArray);
    }

    @Test
    public void test() {
        System.out.println(modifyString("?zs"));
    }


}

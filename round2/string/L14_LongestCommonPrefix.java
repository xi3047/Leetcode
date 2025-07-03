package round2.string;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/22/2020 7:32 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/longest-common-prefix/
 * @description
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class L14_LongestCommonPrefix {
    /**
     * Horizontal Scanning
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


    @Test
    public void test() {
        System.out.println(longestCommonPrefix(new String[]{"abc", "flow", "flight"}));
    }
}

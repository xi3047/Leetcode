package round2.bdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/21/2020 8:47 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/palindrome-partitioning/
 * @description
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.

 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 */
public class L131_PalindromePartitioning {
    public List<List<String>> partition(String s) {
        if(s == null || s.length() == 0) return new ArrayList<>();

        List<List<String>> result = new ArrayList<>();
        helper(s, new ArrayList<>(), result);
        return result;
    }
    public void helper(String s, List<String> step, List<List<String>> result) {
        if (s == null || s.length() == 0) {
            result.add(new ArrayList<>(step));
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String temp = s.substring(0, i);
            if (!isPalidrome(temp)) continue;

            step.add(temp);
            helper(s.substring(i, s.length()), step, result);
            step.remove(step.size() - 1);
        }
    }

    public boolean isPalidrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }
}

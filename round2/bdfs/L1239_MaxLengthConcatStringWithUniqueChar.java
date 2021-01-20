package round2.bdfs;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 1/16/2021 10:14 AM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 * @description
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.

 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 */
public class L1239_MaxLengthConcatStringWithUniqueChar {
    static class Solution_1 {
        int max = 0;
        public int maxLength(List<String> arr) {
            Set<Character> set = new HashSet<>();
            backtrack(arr, new StringBuilder(), set, 0);
            return max;
        }

        private void backtrack(List<String> arr, StringBuilder cur, Set<Character> set, int index) {
            if (index == arr.size()) return;
            for (int i = index; i < arr.size(); i++) {

                if (!containDupChar(arr.get(i)) && canConcat(arr.get(i), set)) {
                    int length = arr.get(i).length();
                    String toAdd = arr.get(i);
                    cur.append(toAdd);
                    addToSet(toAdd, set);
                    max = Math.max(max, cur.length());
                    backtrack(arr, cur, set, i + 1);
                    cur.setLength(cur.length() - length);
                    removeFromSet(toAdd, set);
                }
            }
        }

        private boolean containDupChar(String s) {
            int[] set = new int[26];
            for (char c : s.toCharArray()) {
                set[c - 'a']++;
                if (set[c - 'a'] > 1) return true;
            }
            return false;
        }

        private boolean canConcat(String s, Set<Character> set) {
            for (char c : s.toCharArray()) {
                if (set.contains(c)) return false;
            }
            return true;
        }

        private void addToSet(String s, Set<Character> set) {
            for (char c : s.toCharArray()) {
                set.add(c);
            }
        }

        private void removeFromSet(String s, Set<Character> set) {
            for (char c : s.toCharArray()) {
                set.remove(c);
            }
        }
    }

    class Solution_2{
        /**
         * If we used a string to concat, we don't to need to backtrack,
         * because a new String object is created at DFS (different call stacks)
         * unlike StringBuilder where all stacks referred to the same object
         */
        private int max = 0;
        public int maxLength(List<String> arr) {
            dfs(arr, 0, "");
            return max;
        }

        public void dfs(List<String> arr, int index, String concatenatStr) {
            if (isUnique(concatenatStr)) max = Math.max(max, concatenatStr.length());
            if (index == arr.size() || !isUnique(concatenatStr))  return;
            for (int i = index; i < arr.size(); i++) {
                dfs(arr, i + 1, concatenatStr + arr.get(i));
            }
        }
        public boolean isUnique(String s) {
            int[] alpha = new int[26];
            for (int i = 0; i < s.length(); i++) alpha[s.charAt(i) - 'a']++;
            for (int i = 0; i < alpha.length; i++) if (alpha[i] > 1) return false;
            return true;
        }
    }

    @Test
    public void test() {
        List<String> arr = Arrays.asList("a", "abc", "def");
        System.out.println(new Solution_2().maxLength(arr));
    }

    public static void main(String[] args) {
        Solution_1 sol1 = new Solution_1();
        Solution_2 sol2 = new L1239_MaxLengthConcatStringWithUniqueChar().new Solution_2();
    }
}

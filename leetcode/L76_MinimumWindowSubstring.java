package leetcode;

import org.junit.Test;

import java.util.HashMap;

/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   15:52

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class L76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        // use a hashmap, key is the distinct character in the string t
        // value indicates the frequency of it in string t
        // Note: while traversing through String s, the value means the how many more characters left we need to match
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0, minLeft = 0, minLen = s.length() + 1, matchCount = 0;
        for (int right = 0; right < s.length(); right++) {
            char r = s.charAt(right);
            // if the current character is in T, decrease the number of the character we need to match by 1.
            if (map.containsKey(r)) {
                map.put(r, map.get(r) - 1);
                if (map.get(r) >= 0) matchCount++;
            }
            // when the current substring is a valid one
            while (matchCount == t.length()) {
                // update minimum window size, left and right pointer if a smaller valid window is found
                if (right - left + 1 < minLen) {
                    minLeft = left;
                    minLen = right - left  + 1;
                }
                char l = s.charAt(left);
                if (map.containsKey(l)) {
                    map.put(l, map.get(l) + 1);
                    if (map.get(l) > 0) matchCount--; // >0 means current window is invalid, there are yet more characters to be matched
                }
                left++;
            }
        }
        return minLen == s.length() + 1 ? "" : s.substring(minLeft, minLeft + minLen);
    }

    @Test
    public void test() {
        String s = "adobecodebanc";
        String t = "abc";
        System.out.println(minWindow(s, t));
        //System.out.println(new String(s.toCharArray(), 2 , 4));
    }
}

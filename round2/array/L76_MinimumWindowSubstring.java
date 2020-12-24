package round2.array;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author Xi Zhang
 * @date 12/13/2020 9:19 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/minimum-window-substring/
 * @description
 */
public class L76_MinimumWindowSubstring {
    /*
    Using int array
     */
    public String minWindow(String s, String t) {
        int [] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (map[c1] > 0) counter--;
            map[c1]--;
            while (counter == 0) {
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    minStart = start;
                }
                char c2 = s.charAt(start);
                map[c2]++;
                if (map[c2] > 0) counter++;
                start++;
            }
            end++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    /**
     * Using map
     */
    public String minWindow2(String s, String t) {
        // use a hashmap, key is the distinct character in the round1_misc.string t
        // value indicates the frequency of it in round1_misc.string t
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
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}

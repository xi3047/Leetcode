package leetcode;

import java.util.*;

public class L1_lengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int slow = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int fast = 0; fast < s.length(); fast++) {
            char c = s.charAt(fast);
            if (!map.containsKey(c)) map.put(c, fast);
            else {
                if (map.get(c) >= slow) {
                    slow = map.get(c)  + 1;
                }
                map.put(c, fast);
            }
            longest = Math.max(longest, fast - slow + 1);
        }


        return longest;
    }

    public static void main(String[] args) {
        System.out.print(lengthOfLongestSubstring("abcabcbb"));
    }
}

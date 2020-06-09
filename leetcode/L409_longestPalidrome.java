package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-02-28
    @time:   17:44

    Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

import java.util.*;

public class L409_longestPalidrome {
    public int longestPalidrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        // put all characters into the map with character as key, frequency as value
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int sum = 0;
        boolean hasSingle = false;
        for (int n : map.values()) {
            if (n > 1) {
                sum += n;
            }
            if (n == 1) {
                hasSingle = true;
            }
        }
        return hasSingle? (sum/2)*2 + 1: (sum/2)*2;
    }

    // hashSet solution
    public int longestPalidromeHashSet(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                count++;
            } else {
                set.add(c);
            }
        }
        return set.isEmpty()? count * 2: count * 2 + 1 ;

    }
}

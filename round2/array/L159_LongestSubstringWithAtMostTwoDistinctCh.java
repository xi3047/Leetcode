package round2.array;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/10/2020 4:32 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * @description
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "e c e b a"
 *
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "f c c d a a b b b g"
 *
 *           get last occuring index of previous ch
 *      max      4
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 */
public class L159_LongestSubstringWithAtMostTwoDistinctCh {
    /*
    My solution, very complex, need to slim
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxLength = 0;
        int left = 0;
        int distinctCount = 0;
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            map.put(s.charAt(right), right);
            if (set.add(s.charAt(right))) distinctCount++;
            if (distinctCount > 2) {
                List<Character> previousTwoCharacters = new LinkedList<>();
                for (Character ch : set) {
                    if (!ch.equals(s.charAt(right))) previousTwoCharacters.add(ch);
                }
                Character first = previousTwoCharacters.get(0);
                Character second = previousTwoCharacters.get(1);
                int lastOccurrence = Math.min(map.get(first), map.get(second));
                map.remove(s.charAt(lastOccurrence));
                set.remove(s.charAt(lastOccurrence));
                left = lastOccurrence + 1;
                distinctCount--;
            }
            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }

    /*
    Leetcode solution
     */
    public int length(String s) {
        int n = s.length();
        if (n < 3) return n;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            // when the slidewindow contains less than 3 characters
            hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == 3) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abaccc"));
    }
}

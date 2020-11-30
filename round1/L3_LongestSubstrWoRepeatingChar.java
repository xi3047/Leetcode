package round1;

import java.util.HashMap;
import java.util.Map;

/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   15:16

Given a round1_misc.string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

 */
public class L3_LongestSubstrWoRepeatingChar {
    // Idea: 使用hashmap存储每个字符上一次出现的位置，如果当前的字符重复并且上一次出现的位置大于或等于slow指针，更新slow指针
    public int lengthOfLongestSubstring(String s) {
        int slow = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int fast = 0; fast < s.length(); fast++) {
            char c = s.charAt(fast);
            if (!map.containsKey(c)) {
                map.put(c, fast);
            } else {
                if (map.get(c) >= slow) {
                    slow = map.get(c) + 1;
                }
                map.put(c, fast);
            }
            longest = Math.max(longest, fast - slow + 1);
        }
        return longest;
    }
}

package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   20:49

Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
 */
public class L159_LongestSubstringWithAtMostTwoDistinctChar {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int left = 0;
        int uniqueChars = 0;
        int maxLen = 0;
        int[] charCount = new int[256];

        for (int right = 0; right < s.length(); right++) {
            // 无脑把当前字母的频率加一，如果它是第一次出现，unique字母加一
            // Note: ++在后面是因为我们先要判断当前字母是否新新来的
            if (charCount[s.charAt(right)]++ == 0) {
                // charCount[s.charAt(right)]++
                uniqueChars++;
            }
            // 如果unique字母大于k，要移动左index直到unique等于k为止
            // Note: --在前面是因为我们要先判断减掉一次频率的字母是否为0
            while (uniqueChars > 2) {
                if (--charCount[s.charAt(left)] == 0) uniqueChars--;
                left++;
            }
            // 更新最大substring的值
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

}

package round1;

public class L340_LongestSubstringKDistinctChars {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() < k) return -1;

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
            while (uniqueChars > k) {
                if (--charCount[s.charAt(left) ] == 0) uniqueChars--;
                left++;
            }

            // 更新最大substring的值
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "karkppd";
        System.out.println(lengthOfLongestSubstringKDistinct(s,3));
    }
}

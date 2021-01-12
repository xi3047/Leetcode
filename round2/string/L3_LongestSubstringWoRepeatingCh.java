package round2.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 11/15/2020 9:27 PM
 * @topic round2.array
 * @link
 */
public class L3_LongestSubstringWoRepeatingCh {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int left = 0;
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                if (map.get(c) >= left) {
                    left = map.get(c) + 1;
                }
            }
            map.put(c, right);
            longest = Math.max(longest, right - left +1);
        }
        return longest;
    }
}

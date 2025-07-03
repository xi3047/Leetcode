package round4.string;

import java.util.HashMap;
import java.util.Map;

public class L76_MinimumWindowSubstring {
    /**
     * t = ABC, A:1, B:1, C:1
     * t  = ABC, A:1 B:1 C:1
     *   Window: A:1 B:1 C:1
     *   formed: 2   required: 3
     *                     l
       A D O B E C O D E B A N C
                                 r
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int required = map.size();
        int formed = 0;

        int left = 0, right = 0;
        int minLeft = 0, minLen = Integer.MAX_VALUE;
        Map<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char r = s.charAt(right);
            if (map.containsKey(r)) {
                window.put(r, window.getOrDefault(r, 0) + 1);
                if(window.get(r).intValue() == map.get(r).intValue()) {
                    formed++;
                }
            }

            while (formed == required) {
                int curLen = right - left + 1;
                if (curLen < minLen) {
                    minLen = curLen;
                    minLeft = left;
                }
                char l = s.charAt(left);
                if (map.containsKey(l)) {
                    window.put(l, window.get(l) - 1);
                    if (window.get(l) < map.get(l)) {
                        formed--;
                    }
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);

    }
}

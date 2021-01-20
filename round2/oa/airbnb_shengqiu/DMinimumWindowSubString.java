package round2.oa.airbnb_shengqiu;

import java.util.HashMap;
import java.util.Map;

public class DMinimumWindowSubString {
    public String minWindow(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char c : t.toCharArray()) {
            map1.putIfAbsent(c, 0);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        int found = 0;
        int min = Integer.MAX_VALUE;
        String res = "";
        int i = 0;
        int j = 0;
        while (j < s.length()) {
            // if not enough match, move j
            if (found < t.length()) {
                if (map2.containsKey(s.charAt(j))) {
                    map1.put(s.charAt(j), map1.get(s.charAt(j)) + 1);
                    if (map1.get(s.charAt(j)) <= map2.get(s.charAt(j))) { // ignore over match
                        found++;
                    }
                }
                j++;
            }
            // if found enough match, move i
            while (found == t.length()) {
                if (j - i < min) {
                    min = j - i;
                    res = s.substring(i, j);
                }
                if (map2.containsKey(s.charAt(i))) {
                    map1.put(s.charAt(i), map1.get(s.charAt(i)) - 1);
                    if (map1.get(s.charAt(i)) < map2.get(s.charAt(i))) { // ignore over match
                        found--;
                    }
                }
                i++;
            }
        }
        return res;
    }
}
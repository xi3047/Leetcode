package round2.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 11/6/2020 8:48 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/is-subsequence/
 */
public class L392_IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int p1 = 0, p2 = 0;
        while (p1 < s.length() && p2 < t.length()) {
            while (t.charAt(p2) != s.charAt(p1)) {
                p2++;
                if (p2 == t.length()) {return false;}
            }
            p1++;
            p2++;
        }
        return p1==s.length();
    }

    /**
     * Follow-up
     * If we check each sk in this way, then it would be O(kn) time where k is the number of s and t is the length of t.
     * This is inefficient.
     * Since there is a lot of s, it would be reasonable to preprocess t to generate something that is easy to search for if a character of s is in t.
     * Sounds like a HashMap, which is super suitable for search for existing stuff.
     */
    public boolean isSubsequence2(String s, String t) {
        if (s == null || t == null) return false;

        Map<Character, List<Integer>> map = new HashMap<>(); //<character, index>

        //preprocess t
        for (int i = 0; i < t.length(); i++) {
            char curr = t.charAt(i);
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<Integer>());
            }
            map.get(curr).add(i);
        }

        int prev = -1;  //index of previous character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (map.get(c) == null)  {
                return false;
            } else {
                List<Integer> list = map.get(c);
                prev = binarySearch(prev, list);
                if (prev == -1) return false;
                prev++;
            }
        }

        return true;
    }

    private int binarySearch(int index, List<Integer> list) {
        int start = 0, end = list.size() -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < index) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start == list.size() ? -1 : list.get(start);
    }
}

package round1;
/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   01:16
    Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
 */
import org.junit.Test;

import java.util.*;

public class L205_isomorphic {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i <s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (!map1.containsKey(c1)) map1.put(c1,1);
            else {
                int count = map1.get(c1);
                count++;
                map1.put(c1, count);
            }
            if (!map2.containsKey(c2)) map2.put(c2,1);
            else {
                int count = map2.get(c2);
                count++;
                map2.put(c2, count);
            }
            if (map1.get(c1) != map2.get(c2)) return false;
        }
        return true;
    }

    // Solution 2
    public boolean isIsomoprhic2(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for (int i = 0; i < s.length(); i++) {
            if (map1[s.charAt(i)] != map2[t.charAt(i)]) return false;
            map1[s.charAt(i)] = map2[t.charAt(i)] = i+1;
        }
        return true;
    }

    @Test
    public void test() {
        String s = "egg";
        String t = "add";
        System.out.println(isIsomoprhic2(s, t));
    }

}

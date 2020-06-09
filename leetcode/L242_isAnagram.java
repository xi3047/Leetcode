package leetcode;

import java.util.*;

/*
Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 */
public class L242_isAnagram {

    // hashmap two pass
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),1);
            }
            else {
                int count = map.get(s.charAt(i));
                count++;
                map.put(s.charAt(i),count);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                int count = map.get(t.charAt(i));
                count--;
                if (count < 0) return false;
                map.put(t.charAt(i), count);
            }
            else {
                return false;
            }
        }
        return true;
    }

    // two pass buckets
    public boolean isAnagramBucket(String s, String t) {
        int[] bucket = new int[26];
        for (char c: s.toCharArray()) bucket[c - 'a']++;
        for (char c: t.toCharArray()) if (--bucket[c - 'a'] < 0) return false;
        return true;
    }

    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        char c = 'a';
        System.out.println(map.put(c,0));
        char b = 'a';
        System.out.println(map.put(b,1));
    }
}

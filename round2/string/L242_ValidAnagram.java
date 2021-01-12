package round2.string;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/10/2020 1:20 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/valid-anagram/
 * @description
 * iven two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 * Note:
 * You may assume the string contains only lowercase alphabets.
 *
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class L242_ValidAnagram {
    /*
    Use a bucket that counts freq of characters
     */
    public boolean isAnagram(String s, String t) {
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) bucket[s.charAt(i) -'a']++;
        for (int i = 0; i < t.length(); i++) bucket[t.charAt(i) - 'a']--;
        for (int i: bucket) if (i != 0) return false;
        return true;
    }

    @Test
    public void test() {
        System.out.println(isAnagram("cat", "tac"));
    }
}

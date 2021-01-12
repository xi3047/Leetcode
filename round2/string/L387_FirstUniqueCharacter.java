package round2.string;

/**
 * @author Xi Zhang
 * @date 1/10/21 9:12 PM
 * @topic round2.string
 * @link https://leetcode.com/problems/first-unique-character-in-a-string/
 * @description
 * Given a string, find the first non-repeating character in it and return its index.
 * If it doesn't exist, return -1.
 *
 * Examples:
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode"
 * return 2.
 */
public class L387_FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}

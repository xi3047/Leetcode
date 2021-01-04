package round2.array;

/**
 * @author Xi Zhang
 * @date 12/28/2020 5:38 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/string-compression/
 * @description
 * Given an array of characters chars, compress it using the following algorithm:
 *
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 *
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * The compressed string s should not be returned separately, but instead be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 *
 * After you are done modifying the input array, return the new length of the array.
 *
 *
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 *
 *
 * Example 1:
 *
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 * Example 2:
 *
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a single character.
 */
public class L443_StringCompression {
    public int compress(char[] chars)  {
        if (chars == null || chars.length == 0) return 0;
        int slow = 0, fast = 0, len = chars.length;
        while (fast < len) {
            int count = 0;
            char curChar = chars[fast];
            while (fast < len && chars[fast] == curChar) {
                count++;
                fast++;
            }
            chars[slow++] = curChar;
            if (count != 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[slow++] = c;
                }
            }
        }
        return slow;
    }
}

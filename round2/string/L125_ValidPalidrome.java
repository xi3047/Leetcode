package round2.string;

/**
 * @author Xi Zhang
 * @date 12/10/2020 9:51 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/valid-palindrome/
 * @description
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 */
public class L125_ValidPalidrome {
    /*
    Solution 1: use regex to trim string then two pointers
    O(n) space
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /*
    Solution 2
    O(1) space
     */
    public boolean isPalindrome2(String s) {
        int start = 0;
        int end = s.length() - 1;
        while(start <= end) {
            while(start <= end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while(start <= end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if(start <= end && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}


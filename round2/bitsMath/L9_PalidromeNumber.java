package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 12/21/2020 10:56 AM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/palindrome-number/
 * @description
 */
public class L9_PalidromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int reversed = 0;
        int copyX = x;
        while (copyX != 0) {
            reversed = reversed * 10 + copyX % 10;
            copyX /= 10;
        }
        return reversed == x;
    }
}

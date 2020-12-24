package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 12/21/2020 10:59 AM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/reverse-integer/
 * @description
 */
public class L7_ReverseInteger {
    public int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int remainder = x % 10;
            int temp = reversed * 10 + remainder;


            // check for integer overflow, if the input is -2^31, reversed * 10 / 10 will not be equal to reversed
            // for example, when reversing Integer.MAX_VALUE, 2,147,483,647
            // in the last iteration, temp will be a negative int cuz of overflow
            if (temp / 10 != reversed) {
                return 0;
            }
            reversed = temp;
            x /= 10;
        }

        return reversed;
    }
}

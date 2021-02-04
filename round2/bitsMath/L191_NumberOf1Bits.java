package round2.bitsMath;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 1/28/21 10:12 下午
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/number-of-1-bits/
 * @description
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
 *
 * Note:
 *
 * Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as a
 * signed integer type. It should not affect your implementation, as the integer's internal binary representation is the
 * same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above,
 * the input represents the signed integer. -3.
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class L191_NumberOf1Bits {
    /**
     * >> is arithmetic shift right, >>> is logical shift right.
     *
     * In an arithmetic shift, the sign bit is extended to preserve the signedness of the number.
     *
     * For example: -2 represented in 8 bits would be 11111110 (because the most significant bit has negative weight).
     * Shifting it right one bit using arithmetic shift would give you 11111111, or -1. Logical right shift, however,
     * does not care that the value could possibly represent a signed number; it simply moves everything to the right
     * and fills in from the left with 0s. Shifting our -2 right one bit using logical shift would give 01111111.

     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }
    @Test
    public void test() {

    }
}

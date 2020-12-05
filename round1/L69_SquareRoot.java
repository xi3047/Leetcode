package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   1/30/19
    @time:   8:36 PM
 */
/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
             
             Followup: 要求结果小数点后两位
             Solution: 用long，x先乘10000
 */
public class L69_SquareRoot {
    // Solution 1: use binary search to find the sqrt
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int low = 1, high = x;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (mid == x / mid) return mid;
            else if (mid < x / mid)  low = mid;
            else { high = mid;}
        }
        return low;
    }

    // Solution 2: use Newton's method
    public int mySqrt2(int x) {
        if (x==0) return 0;
        long k = x;
        while (k * k > x) {
            k = (k + x / k) / 2;
        }
        return (int)k;
    }


    @Test
    public void test() {
        System.out.println(mySqrt(8));
    }
    
}

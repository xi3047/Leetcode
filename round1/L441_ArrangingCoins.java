package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   18:06

    You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */
public class L441_ArrangingCoins {
    //Solution 1; O(n) search
    public int arrangeCoins(int n) {
        if (n  < 1) return 0;
        for (int i = 1; ;i++) {
            n -= i;
            if (n < 0) {
                return i - 1;
            }
        }
    }

    // Solution 2: binary search
    public int arrangeCoinsBinary(int n) {
        long nLong = (long) n;
        long start = 0;
        long end = nLong;

        while (start <= end) {
            long mid = start + (end- start) / 2;
            if ( (mid * (mid + 1)) / 2 <= nLong) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return (int) (start - 1);
    }

    @Test
    public void test() {
        System.out.println(arrangeCoinsBinary(8));

    }

}

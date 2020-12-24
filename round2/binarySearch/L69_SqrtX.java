package round2.binarySearch;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 11/3/2020 11:37 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/sqrtx/
 */
public class L69_SqrtX {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int left = 1, right = x / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid < x / mid) left = mid + 1;
            else if (mid > x / mid) right = mid - 1;
            else return mid;
        }
        return right;
    }

    public double sqrt(double x) {
        if (x <= 1.0) return x;
        double left = 1.0, right = x / 2.0;
        while (left <= right) {
            double mid = left + (right - left) / 2.0;
            if (mid < x / mid) left = mid + 0.000000001;
            else if (mid > x / mid) right = mid - 0.000000001;
            else if (Math.abs(mid - x/mid) == (10^-10)) return mid;
        }
        return right;
    }

    @Test
    public void test () {
        System.out.println(sqrt(25));
    }
}

package round2.binarySearch;

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
}

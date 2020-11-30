package round1;
/*
    @author: Xi Zhang
    @date:   2019-06-02
    @time:   01:55
    Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104

 */
import java.util.ArrayList;
import java.util.List;

public class L658_findKclosestElement {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //-------- Main idea behind the binary search algorithm ----------//
        // 1) res will be a consecutive subarray of k size
        // 2) say if we need to pick 4 elems, now we r looking at 5 elem n1, n2, n3, n4, n5
        //    we need to compare two ends: diff(x, n1) and diff(x, n5), the number with bigger diff on the end will be eleminated
        //----------------------------------------------------------------//
        // lo and hi: range of all possible start of subarray with size k + 1, so we could compare both ends
        int lo = 0, hi = arr.length - k - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // for subarray starting at mid with size k+1, we compare element of two ends to eliminate the loser
            if (Math.abs(x - arr[mid]) > Math.abs(x - arr[mid+k])) {
                lo = mid + 1; // arr[mid] is the one further away from x, eliminate range[lo, mid]
            } else {
                hi = mid - 1; // arr[mid+k] is the one further away, all [mid, hi] will have simiar situation, elimiate them
            }
        }
        // return subarray
        List<Integer> res = new ArrayList(k);
        for (int i = 0; i < k; i++) {
            res.add(arr[lo + i]);
        }
        return res;
    }
}

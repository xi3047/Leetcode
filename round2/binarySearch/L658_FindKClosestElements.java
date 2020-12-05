package round2.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xi Zhang
 * @date 11/5/2020 3:46 AM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/find-k-closest-elements/
 */
public class L658_FindKClosestElements {
    /**
     * Solution 1, using two pointers
      */

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int s = 0;
        int e = arr.length-1;

        while (e-s+1>k){
            // 当两边任意的一个abs dist大于另外一边时，舍弃大的
            if (Math.abs(arr[s]-x) > Math.abs(arr[e]-x))
                s++;
            else
                e--;
        }

        List<Integer> res = new ArrayList();

        for (int i=s;i<=e;i++){
            res.add(arr[i]);
        }

        return res;
    }


    /**
     * Solution 2, using binary search
     */
    public List<Integer> findClosestElements2(int[] A, int k, int x) {
        int left = 0, right = A.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - A[mid] > A[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
    }
}

package leetcode;

/*
 * Leetcode 34 Search for range
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Date: Dec 16, 2018
 */

import org.junit.Test;

public class L34_searchRange {
        public int[] searchRange(int[] nums, int target) {
            int[] result = {-1,-1};
            if(nums == null || nums.length == 0) {
                return result;
            }
            int i = 0;
            int j = nums.length - 1;

            while(i <= j) {
                int mid = (i+j)/2;
                if(nums[mid] < target) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }

            if(i >= nums.length || nums[i] != target) {
                return result;
            } else {
                result[0] = i;
            }

            j = nums.length - 1;
            while(i <= j) {
                int mid = (i+j)/2 ;
                if(nums[mid] <= target) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
            result[1] = j;
            return result;
        }



    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[] {-1 , -1};
        int start = 0;
        int end = nums.length - 1;
        int startIndex = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (start <= nums.length - 1) startIndex = nums[start] == target ? start : -1;

        start = 0;
        end = nums.length - 1;
        int lastIndex = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (end >= 0) lastIndex = nums[end] == target? end : -1;

        return new int[] {startIndex, lastIndex};
    }

    @Test
    public void test() {
        System.out.println(searchRange2(new int[]{1}, 1));


    }
}

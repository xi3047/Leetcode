package leetcode;
/*
Leetcode 33 Search in a Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
public class L33_searchRotatedArray {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int rotateIndex = getIndex(nums);
        if (nums[rotateIndex] <= target && target <= nums[nums.length-1]) {
            return binarySearch(nums,target,rotateIndex,nums.length-1);
        }
        else {
            return binarySearch(nums,target,0,rotateIndex-1);
        }
    }

    private static int binarySearch(int[] nums, int target, int left, int right) {
        //int left = 0, right = nums.length -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else { right = mid - 1;}
        }
        return -1;
    }

    private static int getIndex(int[] nums) {
        if (nums[nums.length - 1] >= nums[0]) return 0;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid != 0 && nums[mid] < nums[mid - 1]) return mid;
            else if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] < nums[right]) right = mid - 1;
        }
        return 0;
    }


    // Solution 2, use a -INF and +INF to mark mid point value
    public static int search2(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int num;
            //nums [ mid ] 和 target 在同一段
            if ((nums[mid] < nums[0]) == (target < nums[0])) {
                num = nums[mid];
                //nums [ mid ] 和 target 不在同一段，同时还要考虑下变成 -inf 还是 inf。
            } else {
                num = target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            if (num < target)
                lo = mid + 1;
            else if (num > target)
                hi = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    // Solution 3 consider the array as sorted in at least one side
    public static int search3(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            //左半段是有序的
            if (nums[start] <= nums[mid]) {
                //target 在这段里
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //右半段是有序的
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }



    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        //System.out.println(getIndex(nums));
        System.out.println(search3(nums,0));
    }
}


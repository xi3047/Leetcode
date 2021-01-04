package round2.bitsMath;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Xi Zhang
 * @date 12/25/2020 6:18 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/missing-number/
 * @description
 *
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 */
public class L268_MissingNumber {
    // Solution 1: using XOR on all numbers and the indices
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i = 0; i < nums.length; i++){
            res ^= i ^ nums[i];
        }
        return res;
    }

    // Solution 2: use math formula, 0 + 1 + 2 + 3 + ... + n = n(n+1) / 2, then subtract the sum of numbers from it
    public int missingNumber2(int[] nums) {
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2;
        for(int i = 0; i < len; i++)
            sum -= nums[i];
        return sum;
    }

    // Solution 3: binary search
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // Solution 4: HashSet, two pass
    public int missNumber4(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int res = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res = i;
                break;
            }
        }
        return res;
    }
}

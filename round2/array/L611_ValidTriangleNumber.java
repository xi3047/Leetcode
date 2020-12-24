package round2.array;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 12/10/2020 11:30 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/valid-triangle-number/
 * @description
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 *
 * In a triangle, sum of two sides is always greater than the third side
 */
public class L611_ValidTriangleNumber {
    /*
    Use 3 pointers
     */
    public int triangleNumber(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int k = nums.length - 1; k > 1; k--) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                if (nums[i] + nums[j] > nums[k]) {
                    count += j - i;
                    j--;
                } else {
                    i++;
                }
            }
        }
        return count;
    }

}

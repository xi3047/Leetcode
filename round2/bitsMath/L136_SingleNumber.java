package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 1/13/21 11:59 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/single-number/
 * @description
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 *=
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:t
 *
 * Input: nums = [1]
 * Output: 1
 */
public class L136_SingleNumber {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return  res;
    }
}

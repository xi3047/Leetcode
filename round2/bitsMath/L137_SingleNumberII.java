package round2.bitsMath;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 1/14/21 12:39 AM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/single-number-ii/
 * @description
 * Given an integer array nums where every element appears three times except for one,
 * which appears exactly once. Find the single element and return it.
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 */
public class L137_SingleNumberII {
    /**
     * Solution
     * https://www.youtube.com/watch?v=puXcQpwgcD0&ab_channel=%E4%BB%A3%E7%A0%81%E7%AC%94%E8%AE%B0%E5%93%A
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];

        int res = 0, mask = 1;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                //对于每个位取出第i个位进行累加
                // & 1 是取出最后一位的bit
                sum += (num >> i) & 1;
                // 对3取模
                sum %= 3;
            }
            //返还 左移
            res = res | (sum << i);
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println((101 << 0) |  0);
    }
}

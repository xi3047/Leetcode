package round2.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 12/22/2020 12:19 AM
 * @topic round2.array
 * @link https://leetcode.com/problems/longest-consecutive-sequence/
 * @description
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

Follow up: Could you implement the O(n) solution?
Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
 */
public class L128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }
        int longest = 0;
        for (int n : nums) {
            if (!set.contains(n - 1)) {
                int curN = n;
                int currentStreak = 1;
                while (set.contains(curN + 1)) {
                    curN++;
                    currentStreak++;
                }
                longest = Math.max(longest, currentStreak);

            }
        }
        return longest;
    }
}

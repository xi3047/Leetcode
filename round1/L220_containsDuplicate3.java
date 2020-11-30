package round1;

import java.util.TreeSet;

/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */
public class L220_containsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // sliding window / bucket, with length of k
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) { // n
            int val = nums[i];
            // REMOVE
            if (i > k) {
                set.remove((nums[i - k])); // log n
            }

            // SEARCH
            int upperBound = val + t;
            int lowerBound = val - t;
            // find the greatest element less or equal to the upperbound
            Integer res = set.floor(upperBound); // log n
            if (res != null &&  res >= lowerBound) {
                return true;
            }

            // ADD
            set.add(val);  // log n
        }
        return false;

    }
}

package round1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;

public class L169_MajorityElement {
    /*
    Solution 1: Sort the array then return the the middle element,
    Time: O(nlogn)
    Space: O(1)
     */
    public int majorityElementSort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /*
     Solution 2: using a hashmap to count the frequency
     Time complexity: one pass O(n)
     Space complexity: O(n)
      */

    public int majorityElementMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 2) {
                res = num;
                break;
            }
        }
        return res;
    }

    /*
    Solution 3: Voting algorithm, 打擂台
     Time: O(n)
     Space: O(1)
    */
    public int voting(int[] nums) {
        int count = 0;
        int res = 0;
        for (int num : nums) {
            if (count == 0) { //如果没血了，就换人
                res = num;
            }
            if (num != res) count--; //如果来个挑战者，就减一滴血
            else count++; //来了自己人就加一滴血
        }
        return res;
    }

}

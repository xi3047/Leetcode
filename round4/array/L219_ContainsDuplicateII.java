package round4.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L219_ContainsDuplicateII {
    /* k = 3
        0  1  2  3  4
        1  2  3  4  7

     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }
    // hashmap version
    public boolean containsDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastIndex = new HashMap<>();
        for (int i= 0; i < nums.length; i++) {
            int val = nums[i];
            if (lastIndex.containsKey(val)) {
                int prev = lastIndex.get(val);
                if (i - prev <= k) return true;
            }
            lastIndex.put(val, i);
        }
        return false;
    }
}

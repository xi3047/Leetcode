package round4.array;

import java.util.HashMap;
import java.util.Map;

public class L1679_MaxNumberKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int pairs = 0;
        for (int num : nums) {
            int complement = k - num;
            if (freq.getOrDefault(num, 0) > 0 && freq.getOrDefault(complement, 0) > 0) {
                if (num == complement && freq.get(num) < 2) {
                    continue; // Not enough to make a pair
                }
                pairs++;
                freq.put(num, freq.get(num) - 1);
                freq.put(complement, freq.get(complement) - 1);
            }
        }
        return pairs;
    }
}

package round4.array;

import java.util.HashSet;
import java.util.Set;

public class L128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n :nums) {
            set.add(n);
        }
        int longest = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int current = num;
                int len = 1;
                while (set.contains(current + 1)) {
                    current++;
                    len++;
                }
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }
}

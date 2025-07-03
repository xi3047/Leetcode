package round4.string;

import java.util.*;

public class L3534_MinimumDeletions {
    public int minDeletion(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        if (map.size() < k) return 0;

        List<Integer> freq = new ArrayList<>(map.values());
        Collections.sort(freq);

        int deletions = 0;
        for (int i = 0; i < freq.size() - k; i++) {
            deletions += freq.get(i);
        }
        return deletions;
    }

}

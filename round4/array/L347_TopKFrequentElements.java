package round4.array;

import java.util.*;

public class L347_TopKFrequentElements {
    /**
     * Top K frequent
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0 ) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        int size = list.size();
        quickSelect(list, map, 0, size - 1, size - k);

        int[] res = new int[k];
        for (int i = size - k; i < size; i++) {
            res[i - k - 1] = list.get(i);
        }
        return res;
    }

    private void quickSelect(List<Integer> list, Map<Integer, Integer> map, int left, int right, int k) {
        int pivotFreq = map.get(list.get(list.size()-1));
        int split = left;
        for (int i = left; i < right; i++) {
            if (map.get(list.get(i)) < pivotFreq) {
                Collections.swap(list, i, split);
                split++;
            }
        }
        Collections.swap(list, split, right);

        if (split == k) return;
        else if (split < k) quickSelect(list, map, split + 1, right, k);
        else quickSelect(list, map, left, split - 1, k);
    }
}

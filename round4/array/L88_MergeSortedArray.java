package round4.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class L88_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while (j >= 0) {
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }

    public List<Integer> mergeKSortedArray(int[][] arrays) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Entry> minHeap = new PriorityQueue<>((a ,b) -> a.value - b.value);
        for (int i = 0; i < arrays.length; i++) {
            minHeap.offer(new Entry(arrays[i][0], i, 0));
        }
        while (!minHeap.isEmpty()) {
            Entry cur = minHeap.poll();
            res.add(cur.value);
            int nextPosition = cur.position + 1;
            int arrayIndex = cur.arrayIndex;
            if (nextPosition < arrays[arrayIndex].length) {
                minHeap.offer(new Entry(arrays[arrayIndex][nextPosition], arrayIndex, nextPosition));
            }
        }
        return res;
    }

    static class Entry {
        int value;
        int arrayIndex;
        int position;
        public Entry (int v, int aI, int p) {
            value = v;
            arrayIndex = aI;
            position = p;
        }
    }
}

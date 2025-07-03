package round4.array;

import java.util.PriorityQueue;
import java.util.Random;

public class L215_KthLargestElementInArray {
    final Random rand = new Random();
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        quickSelect(nums, 0 , n - 1, n - k);
        return nums[n - k];
    }

    private void quickSelect(int[] nums, int l, int r, int k) {
        int pivot = nums[r];
        int split = l;
        for (int i = l; i < r; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, split);
                split++;
            }
        }
        swap(nums, split, r);

        if (split == k) return;
        else if (split < k) quickSelect(nums, split + 1, r, k);
        else quickSelect(nums, l, split - 1, k);
    }

    private void swap(int[] nums, int i, int j) {
        int temp= nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.offer(n);
            if (!minHeap.isEmpty() && minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        L215_KthLargestElementInArray sol = new L215_KthLargestElementInArray();
        System.out.println(sol.findKthLargest(new int []{1,1,1,1} , 1));
    }
}

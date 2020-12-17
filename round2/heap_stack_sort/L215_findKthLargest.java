package round2.heap_stack_sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author Xi Zhang
 * @date 11/30/2020 5:52 PM
 * @topic round2.heap
 * @link
 * @description
 */
public class L215_findKthLargest {
    // Solution 1: Divide and Conquer -- QuickSelect
    // Time Complexity: O(n)
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        return findKthLargest(nums, 0, nums.length-1, nums.length - k);
    }
    public int findKthLargest(int[] nums, int start, int end, int k) {
        int pivot = nums[end];
        int pIndex = start;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                swap(nums,pIndex,i);
                pIndex++;
            }
        }
        swap(nums,pIndex,end);
        if (pIndex == k) return nums[pIndex];
        else if (pIndex < k) return findKthLargest(nums, pIndex + 1, end, k);
        else {return findKthLargest(nums,start, pIndex -1, k);}
    }

    private void swap (int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void shuffle(int a[]) {
        Random random = new Random();
        for (int ind = 1; ind < a.length; ind++) {
            int r = random.nextInt(ind + 1);
            swap(a, ind, r);

        }
    }

    private void shuffleFisherYate(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            swap(array, i, index);
        }
    }

    private void shuffleArray(int[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            swap(array, index, i);
        }
    }

    // Solution 2: using heap
    // Time complexity: O(nlogk)
    public static int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int el : nums) {
            minHeap.offer(el);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }

    public static void main(String[] args) {
        int[] n = {9,1,0,2,3,4,6,8,7,10,5};
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(2);
        for (int i : n) {
            q.add(i);
            if (q.size() > 2) {
                q.poll();
            }
        }
        Object[] arr = q.toArray();
        System.out.print(Arrays.toString(arr));
    }

    /**
     * Quick Select Solution
     */
    public int findKthLargestQ(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }
    private int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = nums[end];
        // all numbers on left of spit are bigger than pivot number
        int split = start;
        for (int i = start; i < end; i++) {
            if(nums[i] >= pivot) {
                swap(nums, split, i);
                split++;
            }
        }
        swap(nums, end, split);
        if (split == k) return nums[split];
        else if (split > k) return quickSelect(nums, 0, split - 1, k);
        else return quickSelect(nums, split + 1, end, k);
    }
}

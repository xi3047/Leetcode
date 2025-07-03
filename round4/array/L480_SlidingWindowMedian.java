package round4.array;

import java.util.*;

public class L480_SlidingWindowMedian {
    private PriorityQueue<Long> minHeap = new PriorityQueue<>();
    private PriorityQueue<Long> maxHeap = new PriorityQueue<>((a,b) -> Long.compare(b, a));

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i < n; i++) {
            long num = nums[i];
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            balanceHeaps();

            if (i >= k) {
                long toRemove = nums[i - k];
                if (toRemove <= maxHeap.peek()) {
                    maxHeap.remove(toRemove);
                } else {
                    minHeap.remove(toRemove);
                }
                balanceHeaps();
            }

            if (i >= k - 1) {
                if (k % 2 == 0) {
                    result[i - k + 1]= (maxHeap.peek() + minHeap.peek()) / 2.0;
                } else {
                    result[i - k + 1] = (double) maxHeap.peek();
                }
            }
        }
        return result;
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * TreeSet solution
     */
    public double[] medianSlidingWindow2(int[] nums, int k) {
        // Custom comparator: compare by value in nums; tie-break using index
        Comparator<Integer> comparator = new Comparator<>() {
            public int compare(Integer a, Integer b) {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]); // compare values
                } else {
                    return a - b; // if same value, compare indices to avoid duplicates
                }
            }
        };

        // TreeSet for left (max-heap side), reversed comparator
        TreeSet<Integer> left = new TreeSet<>(comparator.reversed());
        // TreeSet for right (min-heap side)
        TreeSet<Integer> right = new TreeSet<>(comparator);

        double[] res = new double[nums.length - k + 1];

        // Initialize the first window with the first k elements
        for (int i = 0; i < k; i++) {
            left.add(i);  // insert indices
        }

        // Balance left and right
        balance(left, right);
        res[0] = getMedian(k, nums, left, right);

        // Slide the window
        int r = 1;
        for (int i = k; i < nums.length; i++) {
            // Remove index i-k (outgoing element) from one of the sets
            if (!left.remove(i - k)) {
                right.remove(i - k);
            }

            // Insert new index i into right
            right.add(i);
            // Always move the smallest of right into left to rebalance
            left.add(right.pollFirst());

            // Rebalance sizes
            balance(left, right);

            // Compute median
            res[r] = getMedian(k, nums, left, right);
            r++;
        }

        return res;
    }

    // Ensure left.size() <= right.size() and balance correctly
    private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
        while (left.size() > right.size()) {
            right.add(left.pollFirst());
        }
    }

    // Get median based on size of k
    private double getMedian(int k, int[] nums, TreeSet<Integer> left, TreeSet<Integer> right) {
        if (k % 2 == 0) {
            return ((double) nums[left.first()] + nums[right.first()]) / 2;
        } else {
            return (double) nums[right.first()];
        }
    }
}

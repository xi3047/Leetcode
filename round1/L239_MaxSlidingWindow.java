package round1;
import java.util.*;

/*
    @author: Xi Zhang
    @date:   1/28/19
    @time:   7:13 PM
    @Given an array nums, there is a sliding window of size k which is moving
        from the very left of the array to the very right. You can only see the
         k numbers in the window. Each time the sliding window moves right by one
          position. Return the max sliding window.
 */
public class L239_MaxSlidingWindow {
    /*
        Solution 1: naive solution
        use queue for every possible position, iterate the elements within the sliding window to find current max
        Time complexity: O(n - k + 1) * k
        Accepted
     */
    public static int[] maxSlidingWindowSolution1 (int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        // add elements to the initial window
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
            if (nums[i] > max) max = nums[i];
        }
        for (int i = 0; i <= nums.length - k; i++) {
            max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) max = nums[j];
            }
            res.add(max);
            queue.poll();
            if (i != nums.length -k) queue.offer(nums[i + k]);

        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    /*
        Solution2: use a maxHeap to optimize the process to find max
        Time complexity: O(n - k + 1) * (log k -> k)
     */
    public static int[] maxSlidingWindowSolution2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>((x,y) -> y - x);
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = 0; i <= nums.length - k; i++) {
            res.add(heap.peek());
            heap.remove(nums[i]);
            if (i != nums.length - k) heap.offer(nums[i + k]);
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    /*
        Solution3:
        Time complexity: O(n - k + 1) * Amortized O (1)
     */
    public static int[] maxSlidingWindowSolution3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        // Initialize the deque
        for (int i = 0; i < k; i++) {
            //if (i!= 0 && nums[i] > nums[i - 1]) deque.removeFirst();
            if (i>0) {
                while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                    deque.removeLast();
                }
            }
            deque.offer(nums[i]);

        }
        // Go through all the numbers
        for (int i = 0; i <= nums.length - k; i++) {
            res.add(deque.peekFirst());
            if (nums[i] == deque.peekFirst()) {
                deque.removeFirst();
            }
            if (i != nums.length - k) {
                while (!deque.isEmpty() && deque.peekLast() < nums[i + k]) {
                    deque.removeLast();
                }
                deque.offer(nums[i + k]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public static int[] maxSlidingWindow4(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove elements out of range k
            if (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }

            // remove smaller elements in deque
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // add the new element
            deque.offer(i);
            // add to res
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[deque.peek()];
            }

        }

        return res;

    }


    public static void main(String[] args) {
        int[] test = {8, 1, 5, 3, 6, 3 ,9 };
        int[] result = maxSlidingWindow4(test, 3);
        System.out.print(Arrays.toString(result));


    }
}

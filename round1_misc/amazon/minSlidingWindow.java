package round1_misc.amazon;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
    @author: Xi Zhang
    @date:   2019-02-23
    @time:   19:17

    Amazon OA question,
    lowest temperature within certain days frame

    Solution: 使用deque
 */
public class minSlidingWindow {
    public static int[] minSlidingWindow(int[] nums, int k) {
        // cc
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int idx = 0;
        Deque<Integer> window = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            // remove elements on the left that are out of range of the window
            while (!window.isEmpty() && window.peek() < i - k + 1) {
                window.poll();
            }

            // remove elements greater than the current index from the window cuz they are useless
            while (!window.isEmpty() && nums[window.peekLast()] > nums[i]) {
                window.pollLast();
            }

            window.offer(i);

            // add the leftmost element (minimum of current window) to the result
            if (i >= k - 1) {
                res[idx++] = nums[window.peek()];
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 12, 11, 7, 3, 9};
        int[] res = minSlidingWindow(a, 3);
        System.out.println(Arrays.toString(res));

    }
}

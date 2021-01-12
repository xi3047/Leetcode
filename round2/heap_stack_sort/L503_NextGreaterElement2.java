package round2.heap_stack_sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 1/9/21 9:15 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/next-greater-element-ii/
 * @description
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element. The Next Greater Number of a number x is the first
 * greater number to its traversing-order next in the array, which means you could search circularly to
 * find its next greater number. If it doesn't exist, output -1 for this number.
 *
 * Example 1:
 * Input: [7,2,5,6,3] 7,2,5,6,3
 *  stack: 6 3
 *  map: (2,5) (5,6)
 * Output: [2,-1,2]
 * Explanation: The first 1's next greater number is 2;
 * The number 2 can't find next greater number;
 * The second 1's next greater number needs to search circularly, which is also 2.
 */
public class L503_NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int [] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                res[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return res;
    }

    /**
     * HashMap solution
     */
    public int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int [] res = new int[n];

        Map<Integer, Integer> nextGreater = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                nextGreater.put(stack.pop(), i % n);
            }
            stack.push(i % n);
        }
        for (int i = 0; i < res.length; i++) {
            int index = nextGreater.getOrDefault(i, -1);
            if (index == -1) {
                res[i] = -1;
            } else {
                res[i] = nums[index];
            }
        }
        return res;
    }
}

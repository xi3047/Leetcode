package round2.design;

import java.util.Stack;

/**
 * @author Xi Zhang
 * @date 11/28/2020 10:58 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/min-stack/
 * @description
 */
public class L155_MinStack {

    /*
    Solution 1: using two stacks
    one stack for keeping the elements
    one stack for tracking minimum element
     */
    /** initialize your data structure here. */
    Stack<Integer> ele_stack;
    Stack<Integer> min_stack;
    public L155_MinStack() {
        ele_stack = new Stack<>();
        min_stack = new Stack<>();
    }

    /*
    push the element to element stack regardless first
    then check min_stack, if it's empty or current value is smaller than min, update min by pushing x to min_stack,
    otherwise (when x is not smaller than min) we get the min and push the min back to min_stack.
     */
    public void push(int x) {
        ele_stack.push(x);
        if (min_stack.isEmpty() || x < min_stack.peek()) {
            min_stack.push(x);
        } else {
            min_stack.push(min_stack.peek());
        }
    }

    public void pop() {
        if (ele_stack.isEmpty()) return;
        ele_stack.pop();
        min_stack.pop();
    }

    public int top() {
        if (ele_stack.isEmpty()) throw new RuntimeException();
        return ele_stack.peek();
    }

    public int getMin() {
        if (min_stack.isEmpty()) throw new RuntimeException();
        return min_stack.peek();
    }
}

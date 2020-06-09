package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-02-17
    @time:   13:25
 */
import java.util.Stack;

public class L155_minStack {
    class MinStack {
        private Stack<Integer> stack_ele;
        private Stack<Integer> stack_min;

        public MinStack() {
            stack_ele = new Stack<Integer>();
            stack_min = new Stack<Integer>();
        }

        public void push(int x) {
            stack_ele.push(x);
            if (stack_min.isEmpty() || x < stack_min.peek()) {
                stack_min.push(x);
            } else {
                stack_min.push(stack_min.peek());
            }
        }

        public void pop() {
            if (stack_ele.isEmpty()) return;
            stack_min.pop();
            stack_ele.pop();
            return;
        }

        public int top() {
            if (stack_ele.isEmpty()) return -1;
            return stack_ele.peek();
        }

        public int getMin() {
            if (stack_min.isEmpty()) return -1;
            return stack_min.peek();
        }



    }




}

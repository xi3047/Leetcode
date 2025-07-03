package round4.otherDataStructure;

import java.util.Stack;

public class L155_MinStack {
    class MinStack {
        Stack<Node> stack;
        public MinStack() {
            stack = new Stack<>();

        }

        public void push(int val) {
            if (!stack.isEmpty()) {
                int min = stack.peek().min;
                if (val < min) {
                    stack.push(new Node(val, val));
                } else {
                    stack.push(new Node(val, min));
                }
            } else {
                stack.push(new Node(val, val));
            }

        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek().value;
        }

        public int getMin() {
            return stack.peek().min;
        }

        class Node {
            int value;
            int min;

            public Node(int v, int min) {
                this.value = v;
                this.min = min;
            }
        }
    }
}

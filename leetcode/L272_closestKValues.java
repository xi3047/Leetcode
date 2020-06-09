package leetcode;

/*
T(n) = O (h + k)
 */
import org.junit.Test;

import java.util.*;

public class L272_closestKValues {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {

        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        initializeStack(left,right,root,target);  // O(h)

        List<Integer> res = new ArrayList<>();
        while (k-- > 0) {  //O(k)
            if (!left.isEmpty() && !right.isEmpty()) {
                TreeNode l = left.peek();
                TreeNode r = right.peek();
                if (Math.abs(l.val - target) < Math.abs(r.val - target)) {
                    res.add(l.val);
                    getPrev(left);
                } else {
                    res.add(r.val);
                    getNext(right);
                }
            } else if (left.isEmpty()) {
                res.add(right.peek().val);
                getNext(right);
            }
            else { // right is empty
                res.add(left.peek().val);
                getPrev(left);
            }
        }
        return res;
    }

    private void initializeStack (Stack<TreeNode> left, Stack<TreeNode> right, TreeNode root, double target) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < target) {
                left.push(cur);
                cur = cur.right;
            } else {
                right.push(cur);
                cur = cur.left;
            }
        }
    }

    private Stack<TreeNode> generateLeft(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return stack;
    }

    private Stack<TreeNode> generateRight(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return stack;
    }

    private TreeNode getNext(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return top;
    }

    private TreeNode getPrev(Stack<TreeNode> stack) {
        TreeNode top = stack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return top;
    }

    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> list = new ArrayList<Integer>();
        //Queue<Integer> heap2 = new PriorityQueue<Integer>((a, b) -> (int)Math.abs(a - target) - (int)Math.abs(b - target) );
        // 使用lambda 会出integer overflow
        Queue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer arg0, Integer arg1){
                if(Math.abs(arg0-target) > Math.abs(arg1-target)) return 1;
                else if(Math.abs(arg0-target) < Math.abs(arg1-target)) return -1;
                else return 0;
            }
        });
        addToHeap(root, heap);
        for(int i=0; i<k; i++) {
            list.add(heap.poll());
        }
        return list;
    }

    public void addToHeap(TreeNode root, Queue<Integer> heap) {
        if(root == null) return;
        heap.offer(root.val);
        addToHeap(root.left, heap);
        addToHeap(root.right, heap);
    }

    @Test
    public void test() {
        double target = -1500000000.0;
        Queue<Integer> heap = new PriorityQueue<Integer>((a, b) -> (int)Math.abs(a - target) - (int)Math.abs(b - target) );
        heap.offer(1500000000);
        heap.offer(1400000000);
        System.out.println(heap.poll());
        System.out.println(1500000000 + 1400000000 );
//        2147483647
//        1500000000
    }
}

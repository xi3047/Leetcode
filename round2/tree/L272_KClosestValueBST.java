package round2.tree;

import round1.TreeNode;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/10/2020 6:42 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 * @description
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 *
 * Note:
 *
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: [4,3]
 * Queue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> (int) (Math.abs(b - target) - Math.abs(a - target)));
 */
public class L272_KClosestValueBST {
    /*
    Solution 1:
    Inorder + maxHeap of size k
    T(n) = O(nlogk) beats 21%
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> nums = new ArrayList();

        // init heap 'less close element first'
        Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> Math.abs(o1 - target) > Math.abs(o2 - target) ? -1 : 1);
        inorder(root, nums, heap, k);
        return new ArrayList<>(heap);
    }
    public void inorder(TreeNode r, List<Integer> nums, Queue<Integer> heap, int k) {
        if (r == null)
            return;

        inorder(r.left, nums, heap, k);
        heap.add(r.val);
        if (heap.size() > k)
            heap.remove();
        inorder(r.right, nums, heap, k);
    }


    /*
    Solution 2:
    InOrder + maintaining list of closest k values
    T(n) = O(n)  beats 100%
     */
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        inOrder(root, target, k, res);
        return res;
    }

    private boolean inOrder(TreeNode root, double target, int k, LinkedList<Integer> list) {
        if (root == null) return false;

        if (inOrder(root.left, target, k, list)) return true;

        if (list.size() == k) {
            if (Math.abs(list.getFirst() - target) < Math.abs(root.val - target)) return true;
            else {
                list.removeFirst();
            }
        }
        list.addLast(root.val);
        return inOrder(root.right, target, k, list);
    }

    /*
    Solution 3:
    Quick Select
    T(n) Avg O(n) Worst O(n^2) beats 67%
     */
    List<Integer> nums;
    double target;

    public List<Integer> closestKValues3(TreeNode root, double target, int k) {
        nums = new ArrayList();
        this.target = target;
        inorder(root, nums);
        quickselect(0, nums.size() - 1, k);
        return nums.subList(0, k);
    }

    public void swap(int a, int b) {
        int tmp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, tmp);
    }

    public void inorder(TreeNode r, List<Integer> nums) {
        if (r == null)
            return;

        inorder(r.left, nums);
        nums.add(r.val);
        inorder(r.right, nums);
    }

    public int partition(int left, int right, int pivotIndex) {
        double pivotDist = dist(pivotIndex);
        // 1. move pivot to end
        swap(pivotIndex, right);
        int storeIndex = left;

        // 2. move more close elements to the left
        for (int i = left; i <= right; i++) {
            if (dist(i) < pivotDist) {
                swap(storeIndex, i);
                storeIndex++;
            }
        }

        // 3. move pivot to its final place
        swap(storeIndex, right);

        return storeIndex;
    }

    public void quickselect(int left, int right, int kSmallest) {
        /*
        Sort a list within left..right till kth less close element
        takes its place.
        */

        // base case: the list contains only one element
        if (left >= right) return;

        // select a random pivot_index
        Random randomNum = new Random();
        int pivotIndex = left + randomNum.nextInt(right - left);

        // find the pivot position in a sorted list
        pivotIndex = partition(left, right, pivotIndex);

        // if the pivot is in its final sorted position
        if (kSmallest == pivotIndex) {
            return;
        } else if (kSmallest < pivotIndex) {
            // go left
            quickselect(left, pivotIndex - 1, kSmallest);
        } else {
            // go right
            quickselect(pivotIndex + 1, right, kSmallest);
        }
    }

    public double dist(int idx) {
        return Math.abs(nums.get(idx) - target);
    }

    /*
    Solution 4:
    Two Stack
     */
    public List<Integer> closestKValues4(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k-- > 0) {
            if (s1.isEmpty())
                res.add(s2.pop());
            else if (s2.isEmpty())
                res.add(s1.pop());
            else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
                res.add(s1.pop());
            else
                res.add(s2.pop());
        }

        return res;
    }

    // inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) return;

        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
        // track the value of current node
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }


}

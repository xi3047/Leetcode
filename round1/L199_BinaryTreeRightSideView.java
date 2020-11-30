package round1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    @author: Xi Zhang
    @date:   2019-02-18
    @time:   15:47


    Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

    Example:

    Input: [1,2,3,null,5,null,4]
    Output: [1, 3, 4]
    Explanation:

       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---

 */
public class L199_BinaryTreeRightSideView {

    // Solution 1: BFS, level order travesal, add the last Node of each level to the result list
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1) res.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null ) queue.offer(cur.right);
            }
        }
        return res;
    }

    // Solution 2: DFS, preOrder to the right child, add the right node first
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res, 0);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> result, int depth) {
        if (root == null) return;
        if(depth == result.size()){
            result.add(root.val);
        }
        traverse(root.right, result, depth + 1);
        traverse(root.left, result, depth + 1);
    }


}

package round2.tree;

import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 11/14/2020 11:26 AM
 * @topic round2.tree
 * @link
 */
public class L111_MinDepthBinaryTree {
    /**
     * BFS Solution
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int depth = 1;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            // for each level
            for(int i=0;i<size;i++){
                TreeNode node = q.poll();
                if(node.left == null && node.right == null){
                    return depth;
                }
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }


    /**
     * DFS, not optimal
     */
    public int minDepthDFS(TreeNode root) {
        if(root == null) return 0;
        int left = minDepthDFS(root.left);
        int right = minDepthDFS(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;

    }
}

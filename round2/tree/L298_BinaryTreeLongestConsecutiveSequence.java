package round2.tree;

import round1.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 11/16/2020 9:20 PM
 * @topic round2.tree
 * @link
 */
public class L298_BinaryTreeLongestConsecutiveSequence {
    /**
     * BFS
     */
    class Pair {
        TreeNode node;
        int len;

        Pair(TreeNode node, int len) {
            this.node = node;
            this.len = len;
        }
    }

    public int longestConsecutiveApproach2(TreeNode root) {
        if (root == null)
            return 0;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 1));

        int longest = 1;

        while (!q.isEmpty()) {
            int cursize = q.size();
            // query all neighbours in size of 'cursize'

            for (int i = 0; i < cursize; ++i) {
                Pair pair = q.poll();
                TreeNode cur = pair.node;
                int curlen = pair.len, lLen = 0, rLen = 0;

                // query left child
                if (cur.left != null) {
                    lLen = cur.left.val == cur.val + 1 ? curlen + 1 : 1;
                    q.add(new Pair(cur.left, lLen));
                }

                // query right child
                if (cur.right != null) {
                    rLen = cur.right.val == cur.val + 1 ? curlen + 1 : 1;
                    q.add(new Pair(cur.right, rLen));
                }

                longest = Math.max(longest, Math.max(lLen, rLen));
            } // end for
        } // end while

        return longest;
    }

    /**
     * DFS
     */
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, null, 0);

    }

    private int dfs(TreeNode node, TreeNode parent, int length) {
        if (node == null) return length;
        int newLen = parent != null && node.val == parent.val + 1 ? length + 1 : 1;
        int leftLen = dfs(node.left, node, newLen);
        int rightLen = dfs(node.right, node, newLen);
        return Math.max(length, Math.max(leftLen, rightLen));
    }
}

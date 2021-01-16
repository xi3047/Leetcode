package round2.tree;

import round1.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/12/21 10:47 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/leaf-similar-trees/
 * @description
 */
public class L872_LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> leaves1 = new ArrayList<>();
        getLeaves(root1, leaves1);

        List<Integer> leaves2 = new ArrayList<>();
        getLeaves(root2, leaves2);
        return leaves1.equals(leaves2);
    }

    private void getLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) leaves.add(root.val);
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);

    }
}

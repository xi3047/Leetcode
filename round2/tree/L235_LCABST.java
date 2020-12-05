package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/18/2020 4:18 PM
 * @topic round2.tree
 * @link
 * @description
 */
public class L235_LCABST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if ((root.val > p.val) && (root.val > q.val)) root = root.left; // if they are both lower, go left
            else if ( (root.val < p.val) && (root.val < q.val)) root = root.right; // if they are both greater, go right
            else break; // return the node where they go down different paths
        }
        return root;
    }
}

package round1;

/*
    @author: Xi Zhang
    @date:   2019-04-22
    @time:   15:01

    Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */
public class L105_buildTreePreInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, 0, preorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int pre_st, int in_st, int in_end) {
        if (pre_st > preorder.length || in_st > in_end) return null;

        TreeNode current = new TreeNode(preorder[pre_st]);
        int i = in_st;
        while (i <= in_end) {
            if (inorder[i] == preorder[pre_st]) break;
            i++;
        }
        current.left = buildTreeHelper(preorder, inorder, pre_st + 1, in_st, i - 1);
        current.right = buildTreeHelper(preorder, inorder, pre_st + (i - in_st + 1), i + 1, in_end);

        return current;



    }
}

package round2.tree;

import round1.TreeNode;

/**
 * @author Xi Zhang
 * @date 11/19/2020 11:28 AM
 * @topic round2.tree
 * @link https://leetcode.com/problems/inorder-successor-in-bst/
 * @description
 */
public class L285_InOrderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null) return null;
        //case 1:
        //pre is used to save the last node whose left substree
        TreeNode pre = null;
        //find node p
        while(root.val!=p.val)
        {
            if(p.val<root.val)
            {
                //only update pre when p.val<root.val
                pre = root;
                root = root.left;
            }
            else if(p.val>root.val)
                root = root.right;
        }

        //at this point root.val=p.val
        if(root.right==null)
            return pre;
        else
            //case 2
            return getLeftMost(root.right);
    }

    //find the leftmode/smallest node in a tree
    public TreeNode getLeftMost(TreeNode root)
    {
        while(root.left!=null)
        {
            root = root.left;
        }
        return root;
    }
}

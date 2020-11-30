package round1;

/*
    @author: Xi Zhang
    @date:   4/08/2019
    @time:   10:20 PM

    Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Idea: First find the node with the target value, then we have 3 different cases to handle
    Case 1: if the node has both children, set node's value to the smallest from right subtree / the largest from left subtree, then remove the smallest node using recursion
    case 2: if the node has only one child, replace node with non-null child so that the parent of the node is connected directly to the the child
    case 3: if the node has no child, simply remove the node by setting it to null
 */
public class L450_DeleteNodeBST {
    // 在右子树中取最小的
    public TreeNode deleteNode(TreeNode root, int target) {
        if (root == null) return null;
        if (root.val == target) {
            if (root.left != null && root.right != null) {
                root.val = findMin(root.right).val;  // 把当前节点的值设为右子树的最小值
                root.right = deleteNode(root.right, root.val); // 在右子树删掉最小值的节点 然后拼到当前节点右边
            } else {
                return root.left != null? root.left: root.right; // 哪边不是null return哪个，如果都是return的是null
            }
        } else if (root.val > target) {
            root.left = deleteNode(root.left, target);
        } else {
            root.right = deleteNode(root.right, target);
        }
        return root;
    }

    private TreeNode findMin(TreeNode root) {
        if (root.left == null) return root;
        return findMin(root.left);
    }


    // 在左子树取最大的
    public TreeNode deleteNode2(TreeNode root, int target) {
        if (root == null) return null;
        if (root.val == target) {
            if (root.left != null && root.right != null) {
                root.val = findMax(root.left).val;
                root.left = deleteNode(root.left, root.val);
            } else {
                return root.left != null? root.left: root.right;
            }

        } else if (root.val > target) {
            root.left = deleteNode(root.left, target);
        } else {
            root.right = deleteNode(root.right, target);
        }
        return root;
    }

    private TreeNode findMax(TreeNode root) {
        if (root.right == null) return root;
        return findMax(root.right);
    }

}

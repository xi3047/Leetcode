package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-02-25
    @time:   16:09
 */
public class L235_LCABST {


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (true) {
            if ((root.val > p.val) && (root.val > q.val)) root = root.left; // if they are both lower, go left
            else if ( (root.val < p.val) && (root.val < q.val)) root = root.right; // if they are both greater, go right
            else break; // return the node where they go down different paths
        }
        return root;
    }



    public static void main(String[] args) {
//        TreeNode root = TreeNode.createTree(6,2,8,0,4,7,9,null,null,3,5);
//        TreeNode.visualize(root);
//        TreeNode res = lowestCommonAncestor(root, new TreeNode(0), new TreeNode(3));
//
//        System.out.println(res.val);

    }
}

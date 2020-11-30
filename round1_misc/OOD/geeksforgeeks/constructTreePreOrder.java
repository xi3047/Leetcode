package round1_misc.OOD.geeksforgeeks;
/**
 * @author
 * @date
 * @time
 */

import round1.TreeNode;
import org.junit.Test;

public class constructTreePreOrder {
    public TreeNode constructTreePreOrder(int[] nodes, char[] pre) {
        int[] indexPointer = new int[0];
        return constructHelper(nodes, pre, indexPointer, new TreeNode(0));
    }

    private TreeNode constructHelper(int[] nodes, char[] pre, int[] indexPointer, TreeNode temp) {
        int index = indexPointer[0];
        if (index >= pre.length) {
            return null;
        }
        temp = new TreeNode(nodes[index]);
        indexPointer[0]++;
        if (pre[index] == 'N') {
            temp.left = constructHelper(nodes, pre, indexPointer,  temp.left);
            temp.right = constructHelper(nodes, pre, indexPointer, temp.right);
        }
        return temp;
    }

    @Test
    public void test() {
        int[] nodes = {1, 2, 4, 5, 3, 6};
        char[] pre = {'N', 'N', 'L', 'L', 'N', 'L'};
        TreeNode root = constructTreePreOrder(nodes, pre);
        TreeNode.visualize(root);
    }
}

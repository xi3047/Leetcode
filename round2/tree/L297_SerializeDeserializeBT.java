package round2.tree;

import org.junit.Test;
import round1.BinaryTreePrinter;
import round1.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 12/13/2020 6:09 PM
 * @topic round2.tree
 * @link https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * @description
 */
public class L297_SerializeDeserializeBT {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        String n = "null", sep = ",";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    sb.append(n);
                } else {
                    sb.append(cur.val);
                    queue.offer(cur.left);
                    queue.offer(cur.right);
                }
                sb.append(sep);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] vals = data.split(",");
        if (vals.length == 0) return null;

        String n = "null";
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                for (int j = index; j < index + 2 && j < vals.length; j++) {
                    if (vals[j].equals(n)) {
                        if (j % 2 == 1) {
                            cur.left = null;
                        } else {
                            cur.right = null;
                        }
                    } else {
                        TreeNode next = new TreeNode(Integer.parseInt(vals[j]));
                        queue.offer(next);
                        if (j % 2 == 1) {
                            cur.left = next;
                        } else {
                            cur.right = next;
                        }
                    }
                }
                index += 2;
            }
        }
        return root;
    }

    @Test
    public void test() {
        String root = "1,2,3,null,null,4,5,null,null,null,null";
        TreeNode tree = deserialize(root);
        BinaryTreePrinter.printNode(tree);
    }
}

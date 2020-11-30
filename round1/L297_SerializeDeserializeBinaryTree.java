package round1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L297_SerializeDeserializeBinaryTree {
    /*
        @author: Xi Zhang
        @date:   2019-02-15
        @time:   23:47

        Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
        stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
        the same or another computer environment.

        Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
        serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
        serialized to a round1_misc.string and this round1_misc.string can be deserialized to the original tree structure.

        Example:

        You may serialize the following tree:

            1
           / \
          2   3
             / \
            4   5

        as "[1,2,3,null,null,4,5]"

        Solution: Using Pre-Order
     */
    public String serialize(TreeNode root) {
        return (serial(root, new StringBuilder()).toString());
    }

    private StringBuilder serial(TreeNode root, StringBuilder sb) {
        if (root == null) sb.append("#");
        sb.append(root.val).append(",");
        serial(root.left, sb).append(",");
        serial(root.right, sb);
        return sb;
    }

    public TreeNode deserialize(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private TreeNode deserial(Queue<String > q) {
        String val = q.poll();
        if (val.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(q);
        root.right = deserial(q);
        return root;
    }
}

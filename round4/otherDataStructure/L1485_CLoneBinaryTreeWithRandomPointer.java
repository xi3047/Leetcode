package round4.otherDataStructure;

import java.util.HashMap;
import java.util.Map;

public class L1485_CLoneBinaryTreeWithRandomPointer {
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> map = new HashMap<>();
        dfs(map, root);
        copy(map, root);
        return map.get(root);
    }
    private void dfs(Map<Node, NodeCopy> map, Node root) {
        if (root == null) return;
        map.put(root, new NodeCopy(root.val));
        dfs(map, root.left);
        dfs(map, root.right);
    }

    private void copy(Map<Node, NodeCopy> map, Node node) {
        if (node == null) return;

        NodeCopy clone = map.get(node);
        clone.left = map.get(node.left);
        copy(map, node.left);
        clone.right = map.get(node.right);
        copy(map, node.right);
        clone.random = map.get(node.random);

    }


    class Node {
        int val;
        Node left;
        Node right;
        Node random;

        Node() {}
        Node (int val) {
            this.val = val;
        }
        Node (int val, Node left, Node right, Node random) {
            this.val = val;
            this.left = left;
            this.right= right;
            this.random = random;
        }
    }

    class NodeCopy {
        int val;
        NodeCopy left;
        NodeCopy right;
        NodeCopy random;

        NodeCopy() {}
        NodeCopy (int val) {
            this.val = val;
        }
        NodeCopy (int val, NodeCopy left, NodeCopy right, NodeCopy random) {
            this.val = val;
            this.left = left;
            this.right= right;
            this.random = random;
        }
    }
}

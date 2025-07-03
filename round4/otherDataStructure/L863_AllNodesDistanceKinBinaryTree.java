package round4.otherDataStructure;

import com.sun.source.tree.Tree;
import round1.TreeNode;

import java.util.*;

public class L863_AllNodesDistanceKinBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildMap(root, null, parentMap);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> seen = new HashSet<>();
        queue.offer(target);
        seen.add(target);
        int distance = 0;
        while (!queue.isEmpty()) {
            if (distance == k) break;
            int size = queue.size();
            while (size -- > 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null && !seen.contains(cur.left)) {
                    queue.offer(cur.left);
                    seen.add(cur.left);
                }
                if (cur.right != null && !seen.contains(cur.right)) {
                    queue.offer(cur.right);
                    seen.add(cur.right);
                }
                TreeNode parent = parentMap.get(cur);
                if (parent != null && !seen.contains(parent)) {
                    queue.offer(parent);
                    seen.add(parent);
                }

            }
            distance++;
        }
        List<Integer> res = new ArrayList<>();
        for (TreeNode node : queue) {
            res.add(node.val);
        }
        return res;
    }

    private void buildMap(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) return;
        parentMap.put(root, parent);
        buildMap(root.left, root, parentMap);
        buildMap(root.right, root, parentMap);
    }
}

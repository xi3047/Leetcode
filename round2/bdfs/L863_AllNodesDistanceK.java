package round2.bdfs;

import leetcode.TreeNode;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/22/2020 3:32 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/submissions/
 * @description
 */
public class L863_AllNodesDistanceK {
    /**
     * Build a graph then use bfs
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, List<TreeNode>> map = new HashMap();
        List<Integer> res = new ArrayList<Integer> ();
        if (root == null || K < 0) return res;
        buildMap(map, root, null);
        if (!map.containsKey(target)) return res;
        Set<TreeNode> visited = new HashSet<TreeNode>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(target);
        visited.add(target);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size ; i++) res.add(q.poll().val);
                return res;
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                for (TreeNode next : map.get(node)) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.add(next);
                }
            }
            K--;
        }
        return res;
    }

    private void buildMap(Map<TreeNode, List<TreeNode>> map, TreeNode node, TreeNode parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<TreeNode>());
            if (parent != null)  {
                map.get(node).add(parent);
                map.get(parent).add(node) ;
            }
            buildMap(map, node.left, node);
            buildMap(map, node.right, node);
        }
    }
}

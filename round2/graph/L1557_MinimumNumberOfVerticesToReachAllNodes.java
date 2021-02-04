package round2.graph;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 2/1/21 5:51 下午
 * @topic round2.graph
 * @link https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
 * @description
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi]
 * represents a directed edge from node fromi to node toi.
 *
 * Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique
 * solution exists.
 *
 * Notice that you can return the vertices in any order.
 */
public class L1557_MinimumNumberOfVerticesToReachAllNodes {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (List<Integer> edge : edges) {
            inDegree.putIfAbsent(edge.get(0), 0);
            inDegree.put(edge.get(1), inDegree.getOrDefault(edge.get(1), 0) + 1);
        }
        for (int node: inDegree.keySet()) {
            if (inDegree.get(node) == 0) res.add(node);
        }
        return res;
    }
}

package round2.graph;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 2/1/21 1:30 下午
 * @topic round2.graph
 * @link https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * @description
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 * Example 2:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 *
 * Output:  1
 */
public class L323_NumberOfConnectedComponentsinUDG {
    /**
     * Union Find 1, using UnionFindSet class
     */
    public int countComponents(int n, int[][] edges) {
        UnionFindSet unionFindSet = new UnionFindSet(n);
        for (int[] edge : edges) {
            unionFindSet.union(edge[0], edge[1]);
        }
        return unionFindSet.size;
    }

    /**
     * Union Find 2, easier union find
     */
    public int countComponents2(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        for (int[] edge : edges) {
            int r1 = find(parents, edge[0]);
            int r2 = find(parents, edge[1]);
            if (r1 != r2) {
                parents[r1] = r2;
                n--;
            }
        }
        return n;
    }
    private int find(int[] parents, int cur) {
        while (cur != parents[cur]) {
            parents[cur] = parents[parents[cur]];
            cur = parents[cur];
        }
        return cur;
    }

    /**
     * DFS
     */
    public int countComponents3(int n, int[][] edges) {
        if (n <= 1)
            return n;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) {
                dfsVisit(i, map, visited);
                count++;
            }
        }
        return count;
    }

    private void dfsVisit(int i, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        for (int j : map.get(i)) {
            if (visited.add(j)) {
                dfsVisit(j, map, visited);
            }
        }
    }

    @Test
    public void test() {
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(countComponents(5, edges));
    }

}

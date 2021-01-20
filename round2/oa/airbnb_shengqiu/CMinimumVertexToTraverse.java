package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. input is 2d array represents the edges
2. return the list of minimum nodes that can traverse the whole graph
3. if both nodeA and nodeB can traverse whole map, return either one

Approach:
The high level idea is first build the graph using the given edges.
Then we can just start any node, let's say node A, if from this node, we can
visit all the graph, then this is the node to return. Otherwise, we start from
unvisited node, let's say node B. try to visit the whole graph, if we can reach
the previous node A some point, this means node B can reach the nodes where node A
can reach, so we do not need A. Keep this process until all nodes are marked visited.

Time: O(E^2)
Imagine we have such case, we start from F, E, D, C, B, A accordingly, that is
1 + 2 + 3 + ... + E
A -> B -> C -> D -> E -> F
Space: O(E)
O(E) to store the edges
O(E) for the call stack, the depth of recursion tree at most E
 */
public class CMinimumVertexToTraverse {
    public static void main(String args[]) {
        CMinimumVertexToTraverse sol = new CMinimumVertexToTraverse();
        int[][] edge1 = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
        int[][] edge2 = {{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}, {4, 1}};
        int[][] edge3 = {{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
        int[][] edge4 = {{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
        System.out.println(sol.findMin(edge1));
        System.out.println(sol.findMin(edge2));
        System.out.println(sol.findMin(edge3));
        System.out.println(sol.findMin(edge4));
    }

    public List<Integer> findMin(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] num : edges) {
            map.putIfAbsent(num[0], new ArrayList<>());
            map.putIfAbsent(num[1], new ArrayList<>());
            map.get(num[0]).add(num[1]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i : map.keySet()) {
            if (!visited.contains(i)) {
                res.add(i);
                Set<Integer> temp = new HashSet<>();
                dfs(map, res, temp, i, i);
                visited.addAll(temp);
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(Map<Integer, List<Integer>> map, Set<Integer> res, Set<Integer> temp, int start, int cur) {
        if (temp.contains(cur)) {
            return;
        }
        temp.add(cur);
        if (res.contains(cur) && cur != start) { // we do not remove at the first level of recursion tree
            res.remove(cur);
            return;
        }
        for (int next : map.get(cur)) {
            dfs(map, res, temp, start, next);
        }
    }
}

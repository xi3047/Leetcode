package round4.otherDataStructure;

import java.util.*;

public class L261_GraphValidTree {
    // dfs
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        Set<Integer> visited = new HashSet<>();
        if (hasCycle(0, -1, graph, visited)) return false;
        return visited.size() == n;
    }

    private boolean hasCycle(int cur, int parent, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(cur);

        for (int neighbor : graph.get(cur)) {
            if (neighbor == parent) continue;
            if (visited.contains(neighbor)) return true;

            if (hasCycle(neighbor, cur, graph, visited)) {
                return true;
            }
        }
        return false;
    }

    // bfs
    public boolean validTreeBFS(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int neighbor : graph.get(cur)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    graph.get(neighbor).remove((Integer) cur); // removes the element with Integer casting
                }
            }
        }
        return visited.size() == n;
    }

    // union find
    public boolean validTreeUF(int n, int[][] edges) {
        if (edges.length != n - 1) return false;

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i]= i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int rootU = find(parent, u);
            int rootV = find (parent, v);
            if (rootU ==  rootV) return false;
            parent[rootU] = rootV;
        }

        return true;

    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}

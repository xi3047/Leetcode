package round4.bdfs;

import java.util.HashMap;
import java.util.Map;

public class L947_MostStonesRemoved {
    public int removeStones(int[][] stones) {
        // Use Union-Find to connect rows and columns
        UnionFind uf = new UnionFind();

        for (int[] stone : stones) {
            int row = stone[0];
            int col = stone[1] + 10000; // offset columns to avoid collision with rows
            uf.union(row, col);
        }

        // Count unique connected components (roots)
        return stones.length - uf.getComponentCount();
    }

    class UnionFind {
        private Map<Integer, Integer> parent = new HashMap<>();
        private int components = 0;

        // Find with path compression
        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                components++; // New component
            }

            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x))); // Path compression
            }
            return parent.get(x);
        }

        // Union two nodes if they are in different components
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                parent.put(rootX, rootY); // Merge sets
                components--;             // Merged, so one fewer component
            }
        }

        // Return the number of disjoint components
        public int getComponentCount() {
            return components;
        }
    }

    /**
     * DFS version
     */
    public int removeStonesDFS(int[][] stones) {
        int n = stones.length;
        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                dfs(i, stones, visited);
                components++;
            }
        }
        return n - components;
    }

    private void dfs(int i, int[][] stones, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < stones.length; j++) {
            if (visited[j]) continue;
            if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                dfs(j, stones, visited);
            }
        }
    }
}

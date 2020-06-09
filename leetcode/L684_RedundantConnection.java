package leetcode;

import org.junit.Test;

public class L684_RedundantConnection {
    class UnionFindSet {
        private int[] parents_;
        private int[] ranks_;

        public UnionFindSet(int n) {
            parents_ = new int[n + 1];
            ranks_ = new int[n + 1];
            for (int i = 0; i < parents_.length; ++i) {
                parents_[i] = i;
                ranks_[i] = 1;
            }
        }

        public boolean Union(int u, int v) {
            int pu = Find(u);
            int pv = Find(v);
            if (pu == pv) return false;

            if (ranks_[pv] > ranks_[pu])
                parents_[pu] = pv;
            else if (ranks_[pu] > ranks_[pv])
                parents_[pv] = pu;
            else {
                parents_[pv] = pu;
                ranks_[pu] += 1;
            }

            return true;
        }

        public int Find(int u) {
            while (parents_[u] != u) {
                parents_[u] = parents_[parents_[u]];
                u = parents_[u];
            }
            return u;
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        UnionFindSet s = new UnionFindSet(edges.length);
        for (int[] edge : edges)
            if (!s.Union(edge[0], edge[1])) // true代表他们merge， false代表已经在一个cluster里面
                return edge;
        return null;
    }

    @Test
    public void test() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] res = findRedundantConnection(edges);
        for (int i : res) {
            System.out.println(i);
        }

    }
}

package round2.graph;

/**
 * @author Xi Zhang
 * @date 2/1/21 3:11 下午
 * @topic round2.graph
 * @link
 * @description Union Find template, with int array
 */
public class UnionFindSet {
    private int[] parents;
    private int[] ranks;
    int size;

    public UnionFindSet(int n) {
        parents = new int[n + 1];
        ranks = new int[n + 1];
        size = n;
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            ranks[i]= 1;
        }
    }

    public int find(int u) {
        while (parents[u] != u) {
            parents[u] = parents[parents[u]];
            u = parents[u];
        }
        return u;
    }
    // return true if we can union (different roots)
    // return false if already in the same cluster (same roots)
    public boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);
        if (pu == pv) {
            return false;
        }
        // path ranking
        if (ranks[pu] > ranks[pv]) {
            parents[pv] = pu;
        } else if (ranks[pv] > ranks[pu]) {
            parents[pu] = pv;
        } else {
            parents[pu] = pv;
            ranks[pv]++;
        }
        size--;
        return true;
    }
}

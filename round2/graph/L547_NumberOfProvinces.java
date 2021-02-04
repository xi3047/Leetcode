package round2.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 2/1/21 4:53 下午
 * @topic round2.graph
 * @link https://leetcode.com/problems/number-of-provinces/
 * @description
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and
 * city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly
 * connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */
public class L547_NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFindSet unionFindSet = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) unionFindSet.union(i, j);
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(unionFindSet.find(i));
        }
        return set.size();
    }
}

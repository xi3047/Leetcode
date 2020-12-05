package round2.bdfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/20/2020 9:53 PM
 * @topic round2.bdfs
 * @link
 * @description
 */
public class L364_NestedListWeightSum2 {
    /**
     * DFS
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0) return 0;
        int h = helper(nestedList);
        int res = getSum(nestedList, h);
        return res;
    }
    private int getSum(List<NestedInteger> l, int layer) {
        int sum = 0;
        if(l == null || l.size() == 0) return sum;
        for(NestedInteger n : l) {
            if(n.isInteger()) sum += n.getInteger() * layer;
            else sum += getSum(n.getList(), layer - 1);
        }
        return sum;
    }
    private int helper(List<NestedInteger> l) {
        if(l == null || l.size() == 0) return 0;
        int max = 0;
        for(NestedInteger n : l) {
            if(n.isInteger()) max = Math.max(max, 1);
            else max = Math.max(max, helper(n.getList()) + 1);
        }
        return max;
    }

    /**
     * BFS
     */
    public int depthSumInverseBFS(List<NestedInteger> nestedList) {
        int prevSum = 0, totalSum = 0;
        Deque<NestedInteger> queue = new ArrayDeque();
        for (NestedInteger ni : nestedList) {
            queue.offerLast(ni);
        }

        while (!queue.isEmpty()) {
            int size = queue.size(), levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.pollFirst();
                if (current.isInteger()) {
                    levelSum += current.getInteger();
                } else {
                    for (NestedInteger ni: current.getList()) {
                        queue.offerLast(ni);
                    }
                }
            }
            prevSum += levelSum;
            totalSum += prevSum;
        }
        return totalSum;
    }
}

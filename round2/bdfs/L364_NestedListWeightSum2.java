package round2.bdfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public int depthSumInverseBFS(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        for (NestedInteger nI: nestedList) {
            queue.offer(nI);
        }
        int prevUnweighted = 0;
        int totalSum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // only keep track of sum of current level numbers
            int levelSum = 0;
            while (size -- > 0) {
                NestedInteger cur = queue.poll();
                if (cur.isInteger()) {
                    levelSum += cur.getInteger();
                } else {
                    for (NestedInteger ni: cur.getList()) {
                        queue.offer(ni);
                    }
                }
            }
            // previous unweighted means one section
            prevUnweighted = prevUnweighted + levelSum;
            // whenever we go down a deeper level, we add previous unweighted/single section to the total sum
            totalSum = totalSum + prevUnweighted;
        }
        return totalSum;
    }


}

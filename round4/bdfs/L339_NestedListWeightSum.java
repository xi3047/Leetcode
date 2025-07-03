package round4.bdfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class L339_NestedListWeightSum
{
    public interface NestedInteger {
        // Constructor initializes an empty nested list.

        // Constructor initializes a single integer.

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // The result is undefined if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // The result is undefined if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        int level = 1;
        Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger ni : nestedList) {
            q.offer(ni);
        }

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                NestedInteger ni = q.poll();
                if (ni.isInteger()) {
                    sum += ni.getInteger() * level;
                } else {
                    q.addAll(ni.getList());
                }
            }
            level++;
        }
        return sum;
    }
}


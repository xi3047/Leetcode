package round2.bdfs;

import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/20/2020 6:40 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/nested-list-weight-sum/
 * @description Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1.
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class L339_NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(nestedList, 1);
    }
    public int depthSum(List<NestedInteger> nestedList, int level) {
        int result = 0;
        for(NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                result = result + (level * ni.getInteger());
            }else {
                result = result + depthSum(ni.getList(), level+1);
            }
        }
        return result;
    }

}

interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();public void setInteger(int value);
    public void add(NestedInteger ni);
    public List<NestedInteger> getList();
}
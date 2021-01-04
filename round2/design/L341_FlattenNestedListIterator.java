package round2.design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Xi Zhang
 * @date 1/1/2021 4:05 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/flatten-nested-list-iterator/
 * @description
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 */
public class L341_FlattenNestedListIterator implements Iterator<Integer> {

    private List<Integer> integers = new ArrayList<>();
    private int position = 0;

    public L341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        flattenList(nestedList);
    }

    private void flattenList(List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                integers.add(nestedInteger.getInteger());
            } else {
                flattenList(nestedInteger.getList());
            }
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return integers.get(position++);
    }

    @Override
    public boolean hasNext() {
        return position < integers.size();
    }
}

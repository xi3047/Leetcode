package round2.design;

import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/1/2021 4:06 PM
 * @topic round2.design
 * @link
 * @description
 */
public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();

}

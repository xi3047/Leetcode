package round4.otherDataStructure;

import round2.design.NestedInteger;

import java.util.*;

public class L341_FlattenNestedListIterator {

    /**
     * Flatten list solution
     */
    public class NestedIterator implements Iterator<Integer> {
        List<Integer> res;
        int position = 0;
        public NestedIterator(List<NestedInteger> nestedList) {
            flatten(nestedList);
        }

        // recursively flatten
        // base case: when isInteger is true
        private void flatten(List<NestedInteger> list) {
            for (NestedInteger nI : list) {
                if (nI.isInteger()) {
                    res.add(nI.getInteger());
                } else {
                    flatten(nI.getList());
                }
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return res.get(position++);
        }

        @Override
        public boolean hasNext() {
            return position < res.size();
        }
    }

    /**
     * Stack solution
     */
    public class NestedIterator2 implements Iterator<Integer> {
        private Deque<Iterator<NestedInteger>> stack;
        private NestedInteger nextElement;

        public NestedIterator2(List<NestedInteger> nestedList) {
            stack = new ArrayDeque<>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            hasNext();
            Integer res = nextElement.getInteger();
            nextElement = null;
            return res;
        }

        @Override
        public boolean hasNext() {
            while (nextElement == null && !stack.isEmpty()) {
                Iterator<NestedInteger> iterator = stack.peek();
                if (!iterator.hasNext()) {
                    stack.pop();
                } else {
                    NestedInteger nI = iterator.next();
                    if (nI.isInteger()) {
                        nextElement = nI;
                        return true;
                    } else {
                        stack.push(nI.getList().iterator());
                    }
                }
            }
            return nextElement != null;
        }
    }
















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
}



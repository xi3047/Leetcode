package round2.design;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Xi Zhang
 * @date 1/14/21 5:43 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/peeking-iterator
 * @description
 */
public class L284_PeekingIterator {
    /**
     * Saving peeked value
     */
    class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iter;
        private Integer peekValue = null;
        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            iter = iterator;

        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (peekValue == null) {
                if (!iter.hasNext()) {
                    throw new NoSuchElementException();
                }
                peekValue = iter.next();
            }
            return peekValue;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (peekValue != null) {
                Integer toReturn = peekValue;
                peekValue = null;
                return toReturn;
            }
            if (!iter.hasNext()) {
                throw new NoSuchElementException();
            }
            return iter.next();
        }

        @Override
        public boolean hasNext() {
            return peekValue != null || iter.hasNext();
        }
    }


    /**
     * Saving next value
     */
    class PeekingIterator2 implements Iterator<Integer> {
        private Iterator<Integer> iter;
        private Integer next;
        public PeekingIterator2(Iterator<Integer> iterator) {
            // initialize any member here.
            if (iterator.hasNext()) {
                next = iterator.next();
            }
            iter = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer toReturn = next;
            next = null;
            if (iter.hasNext()) next = iter.next();
            return toReturn;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}

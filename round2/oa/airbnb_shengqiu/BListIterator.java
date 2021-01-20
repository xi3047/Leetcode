package round2.oa.airbnb_shengqiu;

import java.util.*;

/*
Assumption:
1. input is a list of list, implement the iterator for the list of list
2. outer list may contain null, for example [[1,2], null, [3,4]]
3. inner list may contain null, for example [[1,2,null,3],[4,5]]
4. for the first, we need get 1,2,3,4, for the second, we have 1,2,null,3,4,5

Approach:
we can just use the list iterator to implement hasNext and next
The idea is, if inner list iterator is null or does not has next, and outer list
iterator has next, we move forward outer list iterator to get the list and generate
a new iterator to use.
 */

class Test {
    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(1,2,3)));
        list.add(new ArrayList<>());
        list.add(new ArrayList<>());
        list.add(new ArrayList<>(Arrays.asList(1,null,2)));
        BListIterator iter = new BListIterator(list);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        iter = new BListIterator(list);
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        System.out.println(list);
    }
}


public class BListIterator implements Iterator<Integer>  {
    private Iterator<List<Integer>> listIter;  // we use list iterator
    private Iterator<Integer> iter;

    public BListIterator(List<List<Integer>> list)  {
        listIter = list.iterator();
        iter = null;
    }

    @Override
    public boolean hasNext() {
        while ((iter == null || !iter.hasNext()) && listIter.hasNext()) {
            List<Integer> next = listIter.next();
            if (next != null) {
                iter = next.iterator();
            }
        }
        return iter != null && iter.hasNext();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return iter.next();
    }

    @Override
    public void remove() {
        if (iter == null) {
            return;
        }
        iter.remove();
    }
}

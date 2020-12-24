package round2.heap_stack_sort;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Xi Zhang
 * @date 12/20/2020 12:09 AM
 * @topic round2.heap_stack_sort
 * @link
 * @description
 *
 * a b b a c a
 *
 */
public class L1047_RemoveAllAdjacentDuplicate {
    public String removeDuplicates(String s) {
        if (s == null) return null;
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i ++) {
            char cur = s.charAt(i);
            if (!deque.isEmpty() && cur == deque.peekLast()) {
                deque.pollLast();
            } else {
                deque.offerLast(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty())
        {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }
}

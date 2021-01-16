package round2.oa.airbnb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/14/21 3:49 PM
 * @topic round2.oa.airbnb
 * @link
 * @description
 */
public class QueueWithFixedArray {
    private final int fixedSize;
    private int count;
    private int head;
    private int tail;
    private List<Object> headList;
    private List<Object> tailList;

    public QueueWithFixedArray(int fixedSize) {
        this.fixedSize = fixedSize;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
        this.headList = new ArrayList<>();
        this.tailList = this.headList;
    }

    public void offer(int num) {
        if (tail == fixedSize - 1) {
            List<Object> newList = new ArrayList<>();
            newList.add(num);
            tailList.add(newList);
            tailList = (List<Object>) tailList.get(tail);
            tail = 0;
        } else {
            tailList.add(num);
        }
        count++;
        tail++;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }

        int num = (int) headList.get(head);
        head++;
        count--;

        if (head == fixedSize - 1) {
            List<Object> newList = (List<Object>) headList.get(head);
            headList.clear();
            headList = newList;
            head = 0;
        }

        return num;
    }

    public int size() {
        return count;
    }

    public static class UnitTest {
        @Test
        public void test() {
            QueueWithFixedArray queue = new QueueWithFixedArray(3);
            queue.offer(7);
            queue.offer(8);
            queue.offer(9);
            queue.offer(10);
            int res = queue.poll();
            int res8 = queue.poll();
            int res9 = queue.poll();
            int res10 = queue.poll();

            System.out.println(res10);

        }
    }



}

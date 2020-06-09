package amazon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class minimumTime {

    public int minimumTime(int numOfSubFiles, List<Integer> files) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int file : files) {
            minHeap.offer(file);
        }
        int res = 0;
        int total = 0;
        while (minHeap.size() > 1) {
            int first = minHeap.poll();
            int second = minHeap.poll();

            res = first + second;
            total += res;

            minHeap.offer(res);
        }
        return total;
    }

    @Test
    public void test() {
        int[] nums = {8, 4, 6, 12};
        List<Integer> files = new ArrayList<>();
        for (int i : nums) {
            files.add(i);
        }
        System.out.println(minimumTime(4, files));
    }
    @Test
    public void test2() {
        int[] nums = {20 , 4 , 8 ,2};
        List<Integer> files = new ArrayList<>();
        for (int i : nums) {
            files.add(i);
        }
        System.out.println(minimumTime(4, files));
    }
    @Test
    public void test3() {
        int[] nums = {1, 2, 5, 10 ,35, 89};
        List<Integer> files = new ArrayList<>();
        for (int i : nums) {
            files.add(i);
        }
        System.out.println(minimumTime(6, files));
    }



}

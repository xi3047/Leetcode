package round4.string;

import java.util.PriorityQueue;

public class L1405_LongestHappyString {
    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x,y) -> y[1] - x[1]);
        if (a > 0) maxHeap.offer(new int[]{'a', a});
        if (b > 0) maxHeap.offer(new int[]{'b', b});
        if (c > 0) maxHeap.offer(new int[]{'c', c});

        StringBuilder res = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            int[] first = maxHeap.poll();
            int n = res.length();

            if (n >= 2 && res.charAt(n - 1) == first[0] && res.charAt(n - 2) == first[0]) {
                if (maxHeap.isEmpty()) break;
                int[] second = maxHeap.poll();
                res.append((char) second[0]);
                second[1]--;
                if (second[1] > 0) maxHeap.offer(second);
                maxHeap.offer(first);
            } else {
                res.append((char)first[0]);
                first[1]--;
                if (first[1] > 0) maxHeap.offer(first);
            }

        }
        return res.toString();
    }
}

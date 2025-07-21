package round4.array;

import java.util.PriorityQueue;

public class L378_KthSmallestMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        int n = matrix.length;
        //initialize minHeap
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{matrix[i][0], i, 0});
        }

        // go through matrix , pop smallest element till k
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            k--;
            if (k == 0) return cur[0];
            int row = cur[1], col = cur[2];
            // after pop , we push next element
            if (col + 1 < n) {
                minHeap.offer(new int[]{matrix[row][col + 1], row, col + 1});
            }
        }
        return -1;
    }


}

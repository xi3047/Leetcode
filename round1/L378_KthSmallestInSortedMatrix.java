package round1;
/*
    @author: Xi Zhang
    @date:   2019-02-19
    @time:   20:33
 */
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class L378_KthSmallestInSortedMatrix {
    class Cell {
        int val;
        int x;
        int y;

        public Cell(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return -1;
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(new Cell(matrix[0][0], 0, 0));
        visited.add(0);
        int kthSmall = 0;
        while (k-- > 0) {
            Cell min = minHeap.poll();
            int i = min.x;
            int j = min.y;
            kthSmall = min.val;
            if (i + 1 < m) {
                if (!visited.contains((i + 1) * n + j)) {
                    minHeap.offer(new Cell(matrix[i + 1][j], i + 1, j));
                    visited.add((i + 1) * n + j);
                }
            }
            if (j + 1 < n) {
                if (!visited.contains(i * n + j + 1)) {
                    minHeap.offer(new Cell(matrix[i][j + 1], i, j + 1));
                    visited.add(i * n + j + 1);
                }
            }
        }
        return kthSmall;
    }

    public static void main(String[] args) {
        L378_KthSmallestInSortedMatrix test = new L378_KthSmallestInSortedMatrix();
        //int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22} {, 13, 15}};
        //System.out.print(test.kthSmallest(matrix, 3));
    }
}

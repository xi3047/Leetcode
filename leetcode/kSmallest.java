package leetcode;

import java.util.*;

public class kSmallest {
    class Cell{
        int val;
        int x;
        int y;

        public Cell(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y= y;
        }
    }

    public int kthSmallest (int[][] matrix, int k) {
        //cc
        PriorityQueue<Cell> heap = new PriorityQueue<Cell>(k + 1, (Cell a, Cell b) -> a.val -b.val);
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        heap.offer(new Cell(matrix[0][0], 0,0));
        int min;
        int [][] directions = {{0,1},{1,0}};
        while (!heap.isEmpty()) {
            Cell cur = heap.poll();
            min = cur.val;

            for (int[] dir : directions) {
                int newX = cur.x + dir[0];
                int newY = cur.y + dir[1];
            }


        }
        return heap.poll().val;

    }

}

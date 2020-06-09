package leetcode;

import java.util.PriorityQueue;

/*
    @author: Xi Zhang
    @date:   2/5/19
    @time:   4:23 PM
 */
public class L407_TrappingRain2 {
    int[][] directions = {{0,-1},{0,1},{1,0},{-1,0}};
    public int trapRainWater(int[][] height) {
        if (height == null || height.length == 0 || height[0] == null || height[0].length == 0) return 0;
        int res = 0;
        int m = height.length;
        int n = height[0].length;
        boolean [][] visited = new boolean[m][n];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a , b) -> a[2] - b[2]);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j, height[i][j]});
                }
            }
        }
        
        int maxHeight = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : directions) {
                int i = cur[0] + dir[0];
                int j = cur[1] + dir[1];
                if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || visited[i][j]) continue;
                res += Math.max(0, cur[2] - height[i][j]);
                maxHeight = Math.max(cur[2], height[i][j]);
                queue.offer(new int[]{i, j, maxHeight});
                visited[i][j] = true;
            }
        }
        return res;
    }
    
}

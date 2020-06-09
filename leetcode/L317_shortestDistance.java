package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class L317_shortestDistance {
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) return 0;
        int m  = grid.length, n = grid[0].length;

        int [][] distance = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(grid[i][j]==1){
                    bfs(grid,i,j, distance);
                }
            }
        }
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 ) {
                    shortest = Math.min(shortest, distance[i][j]);
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? -1 : shortest;

    }
    private void bfs(int[][] grid,int x, int y,int [][] distance){
        int m  = grid.length, n = grid[0].length;
        int[][] dirs = new int [][]{{-1, 0},{1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{x,y});
        boolean[][] visited = new boolean[m][n];
        int level=1;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] curr=q.poll();
                for (int[] dir:dirs){
                    int x1=dir[0]+curr[0];
                    int y1=dir[1]+curr[1];
                    if(x1<0||x1>=m||y1<0||y1>=n) continue;
                    if(grid[x1][y1]!=0 ||visited[x1][y1]) continue;
                    //find next 0: level++;
                    distance[x1][y1] += level;
                    visited[x1][y1]=true;
                    q.offer(new int[]{x1,y1});
                }
            }
            level++;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    grid[i][j] = 2;
                }
            }
        }
    }

}
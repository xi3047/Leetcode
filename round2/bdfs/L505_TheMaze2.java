package round2.bdfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 11/22/2020 5:55 PM
 * @topic round2.bdfs
 * @link
 * @description
 */
public class L505_TheMaze2 {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dirs={{-1,0},{1,0},{0,-1},{0,1}};
        int m = maze.length;
        int n = maze[0].length;
        // record shotest distance
        int [][] distance = new int[m][n];
        // Set all cell as -1
        for (int[] d : distance) {
            Arrays.fill(d, -1);
        }
        Queue<int[]> q = new LinkedList<>();
        distance[start[0]][start[1]] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int[] c = q.poll();
            for (int [] dir : dirs) {
                int x = c[0];
                int y = c[1];
                int count = distance[x][y];
                while(x+dir[0]>=0&&x+dir[0]<maze.length&&y+dir[1]>=0&&y+dir[1]<maze[0].length&&maze[x+dir[0]][y+dir[1]]!=1){
                    x+=dir[0];
                    y+=dir[1];
                    count++;
                }
                // If this cell is first time to reach or the distance to this cell is shorter
                // add it to queue and update distance
                if(distance[x][y]==-1||distance[x][y]>count){
                    q.add(new int[]{x,y});
                    distance[x][y]=count;
                }
            }
        }
        return distance[destination[0]][destination[1]];

    }
}

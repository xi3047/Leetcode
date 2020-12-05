package round2.bdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 11/20/2020 1:22 AM
 * @topic round2.bdfs
 * @link
 * @description
 */
public class L286_WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        int row = rooms.length;
        int col = rooms[0].length;
        for (int i =0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) queue.offer(new int[]{i,j});
            }
        }
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        int minLen = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                for (int[] dir : directions) {
                    int i = cur[0] + dir[0];
                    int j = cur[1] + dir[1];
                    if (i >=0 && j>=0 && i < row && j < col && rooms[i][j] == Integer.MAX_VALUE) {
                        rooms[i][j] = minLen;
                        queue.offer(new int[]{i, j});
                    }
                }
            }
            minLen++;
        }
    }

    public void wallsAndGateDFS(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length ==0 || rooms[0].length == 0) return;
        int row = rooms.length;
        int col = rooms[0].length;
        for (int i =0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) dfs(rooms, i, j, 0);
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int len) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < len) {
            return;
        }
        rooms[i][j] = len;
        dfs(rooms, i + 1, j, len + 1);
        dfs(rooms, i - 1, j, len + 1);
        dfs(rooms, i, j + 1, len + 1);
        dfs(rooms, i, j - 1, len + 1);
    }
}

package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-05-12
    @time:   12:43


    Time complexity: O(5 mn) --> Amortized O (mn) 每个节点最多被摸了5次， main里的两层for loop是 O(mn) 然而dfs的时间复杂度
    度是O（1）
  */
import org.junit.Test;

public class L329_LongestIncreasingPath {
    final static int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length ==0)
            return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] mem = new int[rows][cols];
        int maxLen = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                maxLen = Math.max(maxLen, dfs(matrix, i, j, Integer.MIN_VALUE, mem));
            }
        }
        return maxLen;
    }
    private int dfs(int[][] matrix, int i, int j, int prev, int[][] mem){
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(i < 0 || i >= rows || j < 0 || j >= cols || matrix[i][j] <= prev)
            return 0;
        if(mem[i][j] != 0) return mem[i][j];

        int max = 0;
        for (int k = 0; k < 4; k++) {
            int nextI = i + directions[k][0];
            int nextJ = j + directions[k][1];
            max = Math.max(max, dfs(matrix, nextI, nextJ, matrix[i][j], mem) + 1);
        }
        mem[i][j] = max;
        return max;
    }





    @Test
    public void test() {
        int[][] matrix = {{3, 4}, {2, 5}};
        System.out.println(longestIncreasingPath(matrix));
    }

}

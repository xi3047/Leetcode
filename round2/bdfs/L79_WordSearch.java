package round2.bdfs;

/**
 * @author Xi Zhang
 * @date 11/24/2020 2:41 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/word-search/
 * @description
 */
public class L79_WordSearch {
    int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (dfs(board, word, i, j, 0, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) return false;

        if (board[i][j] == word.charAt(index) && ! visited[i][j]) {
            visited[i][j] = true;
            for (int[] dir: directions) {
                if (dfs(board, word, i + dir[0], j + dir[1], index + 1, visited)) {
                    return true;
                }
            }
            visited[i][j] = false;
        }
        return false;
    }
}

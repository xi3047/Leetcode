package round4.bdfs;

public class L79_WordSearch {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(dfs(board, visited, 0, i, j, word)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int index, int i, int j, String word) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            dfs(board, visited, index + 1, x, y, word);
        }
        visited[i][j] = false;
        return false;
    }
}

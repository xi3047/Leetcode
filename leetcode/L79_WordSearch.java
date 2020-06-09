package leetcode;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2/1/19
    @time:   6:15 PM
 */
public class L79_WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 ) return false;
        if (word == null || word.length() == 0) return true;
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, chars, 0, i, j )) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] word, int index, int x, int y) {
        if (index == word.length) return true;
        if (x < 0 || x == board.length || y < 0 || y == board[0].length || board[x][y] != word[index]) return false;
        board[x][y] ^= 256;


        boolean exist = dfs(board, word, index + 1, x + 1, y)
                || dfs(board, word, index + 1, x - 1, y)
                || dfs(board, word, index + 1, x, y + 1)
                || dfs(board, word, index + 1, x + 1, y - 1);
        board[x][y] ^= 256;
        return exist;
    }

    @Test
    public void test() {
        char a ='a';
        System.out.println(a ^ 256);
        System.out.println(a & 256);
    }
}

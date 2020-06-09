package leetcode;

import org.junit.Test;

import java.util.HashMap;

public class L294_FlipGameII {
    public boolean canWin(String s) {
        if (s == null || s.length() == 0) return false;
        HashMap<String, Boolean> mem = new HashMap<>();
        return dfs(s.toCharArray(), mem);
    }

    private boolean dfs(char[] board,  HashMap<String, Boolean> mem) {
        Boolean canWin = mem.get(new String(board));
        if (canWin != null) {
            return canWin;
        }
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] == '+' && board[i+ 1] == '+') { // 可以flip 分叉
                board[i] = '-';
                board[i+1] = '-';
                boolean ret = dfs(board, mem);
                board[i] = '+';
                board[i+1] = '+';

                if (!ret) {
                    mem.put(new String(board), true);
                    return true; // 如果玩家B 输的，A就赢了
                }
            }
        }
        mem.put(new String(board), false);
        return false; // 如果玩家B 一直都赢，那A肯定输了
    }

    @Test
    public void test() {
        String s = "abc";
        System.out.println(s.toCharArray());
        //System.out.println(canWin(s));
    }
}

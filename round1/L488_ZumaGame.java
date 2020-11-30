package round1;
/*
    @author: Xi Zhang
    @date:   2019-05-09
    @time:   20:45
 */
import org.junit.Test;

import java.util.HashMap;

public class L488_ZumaGame {
    public int findMinStep(String board, String hand) {
        // build map
        HashMap<Character, Integer> handMap = new HashMap<>();
        int[] globalMin = new int[]{Integer.MAX_VALUE};
        for (char ball : hand.toCharArray()) {
            handMap.put(ball, handMap.getOrDefault(ball, 0) + 1);
        }
        dfs(board, handMap, 0, globalMin);
        return globalMin[0] == Integer.MAX_VALUE ? -1 : globalMin[0];
    }
    private void dfs(String board, HashMap<Character, Integer> hand, int usedBall, int[] min) {
        if (board.isEmpty()) {
            min[0] = Math.min(usedBall, min[0]);
            return;
        } else if (hand.isEmpty()) {
            return;
        }

        for (int i = 0; i < board.length(); i++) {
            char ch = board.charAt(i);
            Integer count = hand.get(ch); // 看下手里的球有没有匹配的
            if (count == null) continue;
            // 有连续的两个球，用一个球消
            if (i < board.length() - 1 && board.charAt(i + 1) == ch) {
                String newBoard = generateBoard(board, i - 1, i + 2);
                int newCount =  count - 1;
                if (newCount == 0) hand.remove(ch);
                else hand.put(ch, newCount);
                dfs(newBoard, hand, usedBall + 1, min);
                hand.put(ch, count);
                i++; //跳过第二个同样的球
            }
            // 只有一个球，要用两个球消
            else if (count >= 2) {
                String newBoard = generateBoard(board, i - 1, i + 1);
                int newCount = count - 2;
                if (newCount == 0) hand.remove(ch);
                else hand.put(ch, newCount);
                dfs(newBoard, hand, usedBall + 2, min);
                hand.put(ch, count);
            }
        }
    }

    private String generateBoard(String board, int left, int right) {
        while (left >= 0 && right < board.length()) {
            char ch = board.charAt(left);
            int i = left;
            int count = 0;
            while (i >= 0 && board.charAt(i) == ch) {
                count++;
                i--;
            }
            int j = right;
            while (j < board.length() && board.charAt(j) == ch) {
                count++;
                j++;
            }
            if (count < 3) break;
            else {
                left = i;
                right = j;
            }
        }
        return board.substring(0, left + 1) + board.substring(right);
    }

    @Test
    public void test() {
        String board = "WWRRBBWW";
        String hand = "WRBRW";
        System.out.println(findMinStep(board, hand));
        System.out.println(board.substring(0, 0));

    }

}

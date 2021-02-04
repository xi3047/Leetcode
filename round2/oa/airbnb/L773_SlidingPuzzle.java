package round2.oa.airbnb;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 1/20/2021 10:43 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/sliding-puzzle/
 * @description
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 *
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 *
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 *
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is
 * impossible for the state of the board to be solved, return -1.
 *
 * Examples:
 *
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 */
public class L773_SlidingPuzzle {
    public int slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<>();
        int[][] directions = {{1, 3} , {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        String startBoard = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                startBoard += board[i][j];
            }
        }
        String targetBoard = "123450";
        queue.offer(startBoard);
        Set<String> visited = new HashSet<>();
        visited.add(startBoard);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curBoard = queue.poll();
                if (curBoard.equals(targetBoard)) return steps;
                int zeroPosition = curBoard.indexOf("0");
                int [] curDirections = directions[zeroPosition];
                for (int dir : curDirections) {
                    String newBoard = move(curBoard, zeroPosition, dir);

                    if (visited.add(newBoard)) {
                        queue.offer(newBoard);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private String move(String board, int i, int j) {
        StringBuilder newBoard = new StringBuilder(board);
        newBoard.setCharAt(i, board.charAt(j));
        newBoard.setCharAt(j, board.charAt(i));
        return newBoard.toString();
    }

    public static void main(String[] args) {
        L773_SlidingPuzzle sol = new L773_SlidingPuzzle();
        System.out.println(sol.slidingPuzzle(new int[][]{{1,2,3},{5,4,0}}));
    }
}

package round2.bdfs;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/22/2020 12:32 PM
 * @topic round2.bdfs
 * @link
 * @description
 */
public class L1197_MinKinghtMoves {
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x); y = Math.abs(y);   // If we can reach x,y in one quadrant then we can do it for all others in the same number of moves too.
        if (x == 1 && y == 1) return 2;     // Special case for (1,1) because there is a more efficient way to reach it if we allow negative positions.
        int[][] dirs = new int[][]{{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};
        Deque<int[]> queue = new ArrayDeque<>();
        Set<String> seen = new HashSet<>();
        queue.addLast(new int[]{0, 0});
        seen.add("0,0");
        int result = 0;
        while (queue.size() > 0) {
            int size = queue.size();    // Number of unique positions reachable with exactly result moves.
            for (int i = 0; i < size; ++i) {
                int[] currPos = queue.removeFirst();
                if (currPos[0] == x && currPos[1] == y) return result;
                for (int[] currDir : dirs) {
                    int newX = currPos[0] + currDir[0];
                    int newY = currPos[1] + currDir[1];
                    if (newX >= 0 && newY >= 0 && !seen.contains(newX + "," + newY)) {
                        seen.add(newX + "," + newY);
                        queue.addLast(new int[]{newX, newY});
                    }
                }
            }
            result++;
        }
        return -1;    // Can't be reached on an infinite chess board.
    }

    public static void main(String[] args) {
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1,1});
        int[] a = {1,1};
        System.out.println(set.contains(a));
    }
}

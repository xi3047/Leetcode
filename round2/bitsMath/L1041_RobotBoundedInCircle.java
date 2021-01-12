package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 1/8/21 12:50 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/robot-bounded-in-circle
 * @description
 */
public class L1041_RobotBoundedInCircle {
    public boolean isRobotBounded(String instructions) {
        // 0 north, 1 east, 2 south, 3 west
        int[][] directions = new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        int x = 0, y = 0;
        int idx = 0;

        for (char i : instructions.toCharArray()) {
            if (i == 'L') {
                idx = (idx + 3) % 4;
            } else if (i == 'R') {
                idx = (idx + 1) % 4;
            } else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }
        return (x == 0 && y == 0) || (idx != 0);
    }
}

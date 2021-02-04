package round2.matrix;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 2/3/21 10:23 上午
 * @topic round2.matrix
 * @link https://leetcode.com/problems/walking-robot-simulation/
 * @description
 * A robot on an infinite XY-plane starts at point (0, 0) and faces north. The robot can receive one of three possible
 * types of commands:
 *
 * -2: turn left 90 degrees,
 * -1: turn right 90 degrees, or
 * 1 <= k <= 9: move forward k units.
 * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi).
 *
 * If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues
 * following the rest of the route.)
 *
 * Return the maximum Euclidean distance that the robot will be from the origin squared (i.e. if the distance is 5,
 * return 25).
 *
 * Note:
 *
 * North means +Y direction.
 * East means +X direction.
 * South means -Y direction.
 * West means -X direction.
 *
 *
 * Example 1:
 *
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 4 units to (0, 4).
 * 2. Turn right.
 * 3. Move east 3 units to (3, 4).
 * The furthest point away from the origin is (3, 4), which is 32 + 42 = 25 units away.
 * Example 2:
 *
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 4 units to (0, 4).
 * 2. Turn right.
 * 3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
 * 4. Turn left.
 * 5. Move north 4 units to (1, 8).
 * The furthest point away from the origin is (1, 8), which is 12 + 82 = 65 units away.
 */
public class L874_WalkingRobotSimulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Set<String> obstaclesSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstaclesSet.add(obstacle[0] + " " + obstacle[1]);
        }
        int maxDistance = 0;
        int dir = 0, x = 0, y = 0;
        for (int command : commands) {
            if (command == -2) {
                dir = (dir + 3) % 4;
            } else if (command == -1) {
                dir = (dir + 1) % 4;
            } else {
                while (command-- > 0) {
                    if (obstaclesSet.contains((x + directions[dir][0]) +  " " + (y + directions[dir][1]))) break;
                    x += directions[dir][0];
                    y += directions[dir][1];
                }
            }
            maxDistance = Math.max(maxDistance, x * x + y * y);
        }
        return maxDistance;
    }

    @Test
    public void test() {
        int[][] obstacles = {{2, 4}};
        int[] commands = {4, -1, 4, -2, 4};
        System.out.println(robotSim(commands, obstacles));
    }
}

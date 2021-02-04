package round2.bitsMath;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Xi Zhang
 * @date 2/1/21 11:07 下午
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/valid-square/
 * @description
 * Pure Storage Interview Question
 * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
 *
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 *
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 *
 * Example 1:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * Output: true
 * Example 2:
 *
 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * Output: false
 * Example 3:
 *
 * Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * Output: true
 */
public class L593_ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int [] distances = new int[] {distance(p1, p2), distance(p1, p3), distance(p1, p4), distance(p2, p3),
                distance(p2, p4), distance(p3, p4)};
        Arrays.sort(distances);
        return distances[0] == distances[1] && distances[1] == distances[2] && distances[2] == distances[3]
                && distances[4] == distances[5] && distances[1] < distances[4];

    }
    public int distance(int[] p1, int[] p2) {
        return (p2[0] - p1[0]) * (p2[0] - p1[0]) + (p2[1] - p1[1]) * (p2[1] - p1[1]);
    }

}

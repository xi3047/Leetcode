package round2.array;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 1/20/2021 10:24 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/pour-water/
 * @description
 * We are given an elevation map, heights[i] representing the height of the terrain at that index. The width at each
 * index is 1. After V units of water fall at index K, how much water is at each index?
 *
 * Water first drops at index K and rests on top of the highest terrain or water at that index. Then, it flows according
 * to the following rules:
 *
 * If the droplet would eventually fall by moving left, then move left.
 * Otherwise, if the droplet would eventually fall by moving right, then move right.
 * Otherwise, rise at it's current position.
 * Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.
 * Also, "level" means the height of the terrain plus any water in that column.
 * We can assume there's infinitely high terrain on the two sides out of bounds of the array. Also, there could not be
 * partial water being spread out evenly on more than 1 grid block - each unit of water has to be in exactly one block.
 */
public class L755_PourWater {
    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            int l = K, r = K, n = heights.length, chosen = K;
            while (l > 0 && heights[l] >= heights[l - 1]) {
                l--;
                if (heights[l] < heights[chosen]) {
                    chosen = l;
                }
            }
            if (chosen != K) {
                heights[chosen]++;
                continue;
            }
            while (r < n - 1 && heights[r] >= heights[r + 1]) {
                r++;
                if (heights[r] < heights[chosen]) {
                    chosen = r;
                }

            }
            heights[chosen]++;
        }
        return heights;
    }

    @Test
    public void test() {
        int[] heights = {2,2,2,3};
        int[] res = pourWater(heights, 12, 3);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}

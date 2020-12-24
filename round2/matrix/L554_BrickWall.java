package round2.matrix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/7/2020 12:16 AM
 * @topic round2.array
 * @link
 * @description
 */
public class L554_BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                max = Math.max(max, map.get(sum));
            }
        }
        return wall.size() - max;
    }
}

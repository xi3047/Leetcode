package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-05-11
    @time:   12:25
 */
import java.util.HashMap;

public class L403_FrogJump {
    public boolean canCross(int[] stones) {
        HashMap<Integer, Boolean>[] mem = new HashMap[stones.length];

        return dfs(stones, 0, 0, mem);
    }

    // 每个石头上有对应着 k 能否跳到的状态
    private boolean dfs(int[] stones, int index, int k, HashMap<Integer, Boolean>[] mem) {
        if (index == stones.length - 1) {
            return true;
        }
        if (index >= stones.length) {
            return false;
        }
        HashMap<Integer, Boolean> map = mem[index];
        Boolean val = map.get(k);
        if (val != null) {
            return val;
        }
        // 如果下一个石头在跳跃范围之内
        for (int i = index + 1; i < stones.length; i++) {
            int distance = stones[i] - stones[index];
            if (distance < k - 1) continue;
            if (distance > k + 1) break;
            if (dfs(stones, i, distance, mem)) {
                map.put(k, true);
                return true;
            }
        }
        return false;
    }

}

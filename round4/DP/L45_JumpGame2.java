package round4.DP;

import java.util.LinkedList;
import java.util.Queue;

public class L45_JumpGame2 {
    public int jump(int[] nums) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(nums[0]);
        int minJump = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxCur = 0;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                int next = 0;
                while (next < nums.length && nums[next] < cur) {
                    next += nums[next];
                }
                maxCur = Math.max(maxCur, cur + nums[i]);

            }
            minJump++;
        }
        return minJump;
    }
}

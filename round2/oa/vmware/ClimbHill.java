package round2.oa.vmware;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Xi Zhang
 * @date 12/16/2020 1:09 PM
 * @topic round2.oa
 * @link
 * @description VMWare OA
 */
public class ClimbHill {
    private static int getMinCost(int[] nums) {
        return Math.min(NoneDecreasingArray(nums, nums.length), NoneIncreasingArray(nums, nums.length));
    }

    public static int NoneDecreasingArray(int[] nums, int n) {
        int sum = 0, dif = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            if (!maxHeap.isEmpty() && maxHeap.peek() >= nums[i]) {
                dif = maxHeap.peek() - nums[i];
                sum += dif;
                maxHeap.remove();
                maxHeap.add(nums[i]);
            }
            maxHeap.add(nums[i]);
        }
        return sum;
    }

    private static int NoneIncreasingArray(int[] nums, int n) {
        int sum = 0, dif = 0;
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (!minHeap.isEmpty() && minHeap.peek() <= nums[i]) {
                dif = nums[i] - minHeap.element();
                sum += dif;
                minHeap.remove();
                minHeap.add(nums[i]);
            }
            minHeap.add(nums[i]);
        }
        return sum;
    }

    @Test
    public void test() {
        int [] a = {9,8,7,2,3,3};
        System.out.println(NoneIncreasingArray(a, 6));
    }
}

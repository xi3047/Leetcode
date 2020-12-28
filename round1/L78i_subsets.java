package round1;
import org.junit.Test;

import java.util.*;

public class L78i_subsets {

    // Smart solution
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int num : nums) {  // ①从数组中取出每个元素
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i));  // ②逐一取出中间结果集
                temp.add(num);  // ③将 num 放入中间结果集
                res.add(temp);  // ④加入到结果集中
            }
        }
        return res;
    }

    // Solution 1: DFS one branch
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int index = 0;
        dfsHelper1(nums, res, new ArrayList<Integer>(), index);
        return res;
    }
    private void dfsHelper1(int[] nums, List<List<Integer>> res, List<Integer> temp, int index) {
        res.add(new ArrayList<>(temp));
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            dfsHelper1(nums, res, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    // DFS two branches
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int level = 0;
        dfsHelper2(nums, res, new ArrayList<Integer>(), level);
        return res;
    }
    private void dfsHelper2(int[] nums, List<List<Integer>> res, List<Integer> tempList, int level) {
        if (level == nums.length) {
            res.add(new ArrayList<Integer>(tempList));
            return;
        }
        tempList.add(nums[level]);
        dfsHelper2(nums, res, tempList, level + 1);
        tempList.remove(tempList.size() - 1);

        dfsHelper2(nums, res, tempList, level + 1);
    }

    // Solution 3: BFS with two branches
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null ) return res;
        Queue<List<Integer>> queue = new LinkedList<>();

        queue.offer(new ArrayList<Integer>());
        int index = 0;
        while (!queue.isEmpty() && index < nums.length) {
            int size = queue.size();
            while (size-- > 0) {
                List<Integer> cur = queue.poll();
                queue.offer(new ArrayList<>(cur));
                cur.add(nums[index]);
                queue.offer(new ArrayList<>(cur));
            }
            index++;
        }
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;

    }

    // Solution 4: BFS with one branch
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<Integer>());
        res.add(new ArrayList<Integer>());
        int index = 0;
        while (!queue.isEmpty() && index < nums.length) {
            int size = queue.size();
            int curIndex = index;
            while (size-- > 0) {
                List<Integer> cur = queue.poll();
                for (int i = curIndex; i < nums.length; i++) {
                    cur.add(nums[i]);
                    queue.offer(new ArrayList<Integer>(cur));
                    res.add(new ArrayList<Integer>(cur));
                    cur.remove(cur.size() - 1);
                }
                curIndex++;
            }
            index++;
        }
        return res;
    }

    @Test
    public void test () {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> subsets = subsets1 (nums);
        int counter = 0;
        for (List<Integer> s :subsets) {
            System.out.println(counter + ":" + s);
            counter++;
        }
        //breakpoint
    }
}



package round2.oa.pureStorage;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 1/22/2021 11:43 PM
 * @topic round2.oa
 * @link
 * @description Pure Storage OA question
 *
 * Given an unsorted list of N integers with duplicates, find all elements where there is a corresponding element that
 * is twice its value and that double-valued element only exists once, print the list out in sorted order
 *
 * Integer range from 0 to 1000,
 * E.g.
 * nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 8}
 * output = {0, 1, 2, 3}
 *
 * nums = {7, 17, 11, 1, 23}
 * output = {}
 *
 * nums = {1, 1, 2}
 * output = {1}
 */
public class FindDoubles {
    public List<Integer> findDoubles(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int doubleValue = num * 2;
            if (map.containsKey(doubleValue) && map.get(doubleValue) == 1) {
                res.add(num);
            }
        }
        Collections.sort(res);
        return res;
    }
    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 8};
        System.out.println(findDoubles(nums));
    }

}

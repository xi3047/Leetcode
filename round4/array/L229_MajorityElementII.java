package round4.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L229_MajorityElementII {
    /**
     * O(n) space , hashmap solution
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0 ) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : map.keySet()) {
            if (map.get(num) > nums.length / 3) {
                res.add(num);
            }
        }
        return res;
    }

    /**
     * O(1)
     * Boyer-Moore Algorithm
     */
    public List<Integer> majorityElement2(int[] nums) {
        Integer cand1 = null;
        Integer cand2 = null;
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (cand1 != null && cand1 == num) count1++;
            else if (cand2 != null && cand2 == num) count2++;
            else if (count1 == 0) {
                cand1 = num;
                count1 = 1;
            }
            else if (count2 == 0) {
                cand2 = num;
                count2 = 1;
            }
            else {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        for (int num : nums) {
            if (cand1 == num) count1++;
            if (cand2 == num) count2++;
        }
        List<Integer> res= new ArrayList<>();
        if (count1 > nums.length / 3) res.add(cand1);
        if (count2 > nums.length / 3) res.add(cand2);
        return res;
    }

}

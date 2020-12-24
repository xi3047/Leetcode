package round2.oa.vmware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/15/2020 11:55 PM
 * @topic round2.oa
 * @link
 * @description
 * VMWare OA
 */
public class PerfectTeam {
    public static int perfectTeam(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c: s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        for (Character key : map.keySet()){
            min = Math.min(min, map.get(key));
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(perfectTeam("pcmbzpcmbz"));
    }
}

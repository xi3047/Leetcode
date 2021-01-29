package round2.tree;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 1/21/2021 12:25 AM
 * @topic round2.tree
 * @link https://leetcode.com/problems/smallest-common-region/
 * @description
 * You are given some lists of regions where the first region of each list includes all other regions in that list.
 *
 * Naturally, if a region X contains another region Y then X is bigger than Y. Also by definition a region X contains itself.
 *
 * Given two regions region1, region2, find out the smallest region that contains both of them.
 *
 * If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.
 *
 * It's guaranteed the smallest region exists.

 *
 * Example 1:
 *
 * Input:
 * regions = [["Earth","North America","South America"],
 * ["North America","United States","Canada"],
 * ["United States","New York","Boston"],
 * ["Canada","Ontario","Quebec"],
 * ["South America","Brazil"]],
 * region1 = "Quebec",
 * region2 = "New York"
 * Output: "North America"
 */
public class L1257_SmallestCommonRegion {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> map = new HashMap<>();
        for (List<String> list : regions) {
            for (int i = 1; i < list.size(); i++) {
                map.put(list.get(i), list.get(0));
            }
        }
        Set<String> region1ParentPath = new HashSet<>();

        String parent = region1;

        while (parent != null) {
            region1ParentPath.add(parent);
            parent = map.get(parent);
        }

        parent = region2;
        while (parent != null) {
            if (region1ParentPath.contains(parent)) {
                return parent;
            }
            parent = map.get(parent);
        }
        return "";
    }

}

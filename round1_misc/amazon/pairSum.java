package round1_misc.amazon;
/*
    @author: Xi Zhang
    @date:   2019-02-22
    @time:   16:35

    Amazon 社招OA question 2
    Two Sum 变种， 使用TreeMap的floorKey来取比它小的最大值
 */

import org.junit.Test;

import java.util.*;

public class pairSum {
    public static List<List<Integer>> pairSum(List<List<Integer>> foreApps, List<List<Integer>> backApps, int capacity) {
        List<List<Integer>> finalList = new ArrayList<>();

        // key是 memory， value是对应的app ID，要用list存ID因为可能有多个app使用相同的memory
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> foreApps.get(a[0]).get(1) + foreApps.get(a[1]).get(1) - backApps.get(b[0]).get(1) - backApps.get(b[1]).get(1));


        // 遍历每个前端程序
        for (List<Integer> foreApp : foreApps) {
            int foreAppMemory = foreApp.get(1);
            // 如果map里没有，新建一个list, 然后无脑把当前app memory用量加到这个list里
            if (!map.containsKey(foreAppMemory)) {
                map.put(foreAppMemory, new ArrayList<>());
            }
            map.get(foreAppMemory).add(foreApp.get(0));
        }
        int maxMemory = Integer.MIN_VALUE;
        // 遍历每个后端程序
        for (List<Integer> backApp : backApps) {
            int backAppMemory = backApp.get(1);
            // 拿出可以利用最大剩余空间(总空间-后端空间)的前端程序
            Integer foreAppMemory = map.floorKey(capacity - backAppMemory);
            // 有可能没有(当剩余空间很小时)，直接跳过
            if (foreAppMemory == null) {
                continue;
            }

            if (foreAppMemory + backAppMemory >= maxMemory) {
                if (foreAppMemory + backAppMemory > maxMemory) { // 如果总使用空间大于 最大记录的空间，清空结果list
                    finalList.clear();
                }
                maxMemory = foreAppMemory + backAppMemory;
                // 把前端后端程序idx的pair 存到结果列表里
                for (int idx : map.get(foreAppMemory)) {
                    finalList.add(Arrays.asList(idx, backApp.get(0)));
                }
            }
        }

        return finalList;
    }


    @Test
    public void test() {
        List<List<Integer>> foreApps = new ArrayList<>();
        foreApps.add(Arrays.asList(0,7));
        foreApps.add(Arrays.asList(1,4));
        foreApps.add(Arrays.asList(2,6));
        List<List<Integer>> backApps = new ArrayList<>();

        backApps.add(Arrays.asList(0,10));
        backApps.add(Arrays.asList(1,6));
        backApps.add(Arrays.asList(2,8));

        System.out.println(pairSum(foreApps, backApps, 15));


    }

}

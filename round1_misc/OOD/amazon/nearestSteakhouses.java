package round1_misc.OOD.amazon;
/*
    @author: Xi Zhang
    @date:   2019-02-21
    @time:   20:14
    @Amazon oa1
 */
import java.util.*;

public class nearestSteakhouses {
    public static List<List<Integer>> nearestSteakhouses(int total, List<List<Integer>> locations, int number) {
        List<List<Integer>> res = new ArrayList<>();
        // cc
        if (number > total || total <= 0 || number <= 0 || locations == null) throw new IllegalArgumentException("Invalid input");

        PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.get(0)*b.get(0) + b.get(1)*b.get(1) - a.get(0)*a.get(0) - a.get(1)*a.get(1));

        for (List<Integer> location : locations) {
            maxHeap.offer(location);
            if (maxHeap.size() > number) {
                maxHeap.poll();
            }
        }
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.poll());
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(1);
        list3.add(-1);
        ArrayList<Integer> list4 = new ArrayList<>();
        list4.add(1);
        list4.add(0);
        List<List<Integer>> arr = new ArrayList<>();
        arr.add(list1);
        arr.add(list2);
        arr.add(list3);
        arr.add(list4);

        List<List<Integer>> res = nearestSteakhouses(4, arr, 2);
        for (List<Integer> location : res) {
            System.out.println(location.get(0) + ", " + location.get(1));
        }


    }
}

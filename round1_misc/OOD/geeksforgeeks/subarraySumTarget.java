package round1_misc.OOD.geeksforgeeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class subarraySumTarget {
    public static List<List<Integer>> subarraySumTarget(int[] arr, int target) {
        HashMap<Integer, List<Integer>> prefixSumMap = new HashMap<>();
        List<List<Integer>> resList = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(-1);
        prefixSumMap.put(0, a);
        int curSum = 0;

        for (int i = 0; i < arr.length; i++) {
            curSum += arr[i];

            if (prefixSumMap.containsKey(curSum - target)) {
                List<Integer> indexList = prefixSumMap.get(curSum - target);
                for (int index : indexList) {
                    int[] curResult = Arrays.copyOfRange(arr, index + 1, i + 1);
                    List<Integer> curResultList = convertIntArrayToList(curResult);
                    resList.add(curResultList);
                }
            }

            if (!prefixSumMap.containsKey(curSum)) {
                prefixSumMap.put(curSum, new ArrayList<>());
            }

            prefixSumMap.get(curSum).add(i);
        }
        return resList;
    }

    private static List<Integer> convertIntArrayToList(int[] input) {
        List<Integer> list = new ArrayList<>();
        for (int i : input) {
            list.add(i);
        }
        return list;
    }

    public static void main(String []args){

        int arr [] = { 0, 0};
        int sum = 0;
        System.out.println(subarraySumTarget(arr, sum));
    }
}

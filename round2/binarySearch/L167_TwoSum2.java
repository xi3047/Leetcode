package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/6/2020 5:06 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class L167_TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while(start < end){
            if(numbers[start] + numbers[end] == target) break;
            if(numbers[start] + numbers[end] < target) start++;
            else end--;
        }
        return new int[]{start + 1, end + 1};

    }
}

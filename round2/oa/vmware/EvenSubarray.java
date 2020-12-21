package round2.oa.vmware;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/15/2020 7:07 PM
 * @topic round2.oa
 * @link
 * @description VMWare OA Question 2
 *
 * Given an array of positive integers, and a positive number k,
 * find the count of total possible subarrays that contains at most k odd numbers
 */
public class EvenSubarray {
    public int evenSubarray(int[] a, int k) {
        int n = a.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int total = 0;
        int oddCount = 0;

        // traverse in the array
        for (int i = 0; i < n; i++)
        {
            // put an odd number and its last occurred index into the map
            if ((a[i] & 1) == 1) {
                oddCount++;
                map.put(oddCount, i);
            }
            // if current odd count is greater or equal to k,
            // it means we can we from current Index to the last odd-k index is the number of subarray we have
            if (oddCount >= k) {
                total += i - map.get(oddCount - k);
            } else { // if odd count is smaller than k, it means we are at the beginning where we haven't seen enough odd numbers
                    // thus every index will gives us i subArrays
                total += i;
            }
        }
        return total;
    }

    @Test
    public void test() {
        System.out.println(evenSubarray(new int[]{1,2,2,3,4}, 1));
    }
}

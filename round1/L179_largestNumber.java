package round1;
/*
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a round1_misc.string instead of an integer.

Time complexity: O(nlogn * k) + O(n)
Space complexity : O(n)
 */

import java.util.Arrays;
import java.util.Comparator;

public class L179_largestNumber {
    public String largestNumber(int[] nums) {
        // corner case
       if (nums == null || nums.length == 0) return "";

       // convert int array to String array
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }

        // sort the Integer as String using a new Comparator
       Arrays.sort(strs, new Comparator<String>() {
           @Override
           public int compare(String a, String b) {
               String s1 = a + b;
               String s2 = b + a;
               return s1.compareTo(s2);
           }
       });

        // extreme edge case where bunch of 0s is present is the array
        if (strs[strs.length-1].charAt(0) == '0') {
            return "0";
        }

        // append the sorted number in reverse order
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length -1  ; i >= 0; i--) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String strs[] = {"3", "30", "34", "5", "9"};
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String s1 = a + b;
                String s2 = b + a;
                return s1.compareTo(s2);
            }
        });
        for (String s : strs) {
            System.out.print(s+" ");
        }
    }
}


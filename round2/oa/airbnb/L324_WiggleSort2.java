package round2.oa.airbnb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/20/2021 6:26 PM
 * @topic round2.heap_stack_sort
 * @link https://leetcode.com/problems/wiggle-sort-ii/
 * @description
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 *
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 * Example 2:
 *
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class L324_WiggleSort2 {
    /**
     * Nlogn solution
     * Sort the array then
     */
    class Solution_1 {
        public void wiggleSort(int[] nums) {
            int[] copy = Arrays.copyOf(nums, nums.length);
            Arrays.sort(copy);

            int n = nums.length;
            int left = (n + 1) / 2 - 1; // median index
            int right = n - 1; // largest value index
            for (int i = 0; i < nums.length; i++)
            {
                // copy values decrementing from median on even indexes
                if(i % 2 == 0){
                    nums[i] = copy[left];
                    left--;
                } else{ // copy large values on odd indexes
                    nums[i] = copy[right];
                    right--;
                }
            }
        }
    }

    /**
     * QuickSelect (Three Way Partitioning)
     */
    class Solution_2 {
        public void wiggleSort(int[] nums) {
            int median = selectKth(nums, 0, nums.length-1, (nums.length + 1) / 2 - 1);
            List<Integer> leftArr = new ArrayList<>();
            for (int i = 0; i <= median; i++) {
                leftArr.add(nums[i]);
            }

            List<Integer> rightArr = new ArrayList<>();
            for(int i = median + 1; i < nums.length; i++) {
                rightArr.add(nums[i]);
            }
            for(int li = leftArr.size() - 1, ri = rightArr.size() - 1, i = 0; ri >= 0; li--, ri--,i += 2) {
                // right is same or shorter than left
                nums[i] = leftArr.get(li);
                nums[i + 1] = rightArr.get(ri);
            }
            if(nums.length %2 != 0)
                nums[nums.length - 1] = leftArr.get(0);
        }

        private int selectKth(int[] nums, int start, int end, int k) {
            int[] res = partition(nums, start, end);
            int low = res[0];
            int high = res[1];
            if(k < low)
                return selectKth(nums, start,low - 1,k);
            else if (k  > high)
                return selectKth(nums,high + 1, end, k);
            else // k is one of the median values
                return k;
        }


        private int[] partition(int[] nums, int low, int high) {
            int pVal = nums[low]; // use random generator is better in performance
            int mid = low;
            while(mid <= high) {
                if(nums[mid] == pVal)
                    mid++;
                else if(nums[mid] < pVal)
                    swap(nums, mid++, low++);
                else // nums[mid] > pVal
                    swap(nums, mid, high--);
            }
            int[] res = new int[2];
            res[0] = low; res[1] = high;
            // loop invariants:
            // [0, low) : numbers less than median
            // [low, high) medians
            // [high, n] numbers larger than median
            return res;
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    /**
     * Quick Select + Virtual Index Mapping
     * Time: O(n), Space O(1)
     */
    class Solution_3 {

        public void wiggleSort(int[] nums) {
            int median = findKthLargest(nums, (nums.length + 1) / 2);
            int n = nums.length;

            int left = 0, i = 0, right = n - 1;

            while (i <= right) {

                if (nums[newIndex(i,n)] > median) {
                    swap(nums, newIndex(left++,n), newIndex(i++,n));
                }
                else if (nums[newIndex(i,n)] < median) {
                    swap(nums, newIndex(right--,n), newIndex(i,n));
                }
                else {
                    i++;
                }
            }
        }

        private int newIndex(int index, int n) {
            return (1 + 2*index) % (n | 1);
        }

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, k - 1);
        }
        private int quickSelect(int[] nums, int start, int end, int k) {
            int pivot = nums[end];
            // all numbers on left of spit are bigger than pivot number
            int split = start;
            for (int i = start; i < end; i++) {
                if(nums[i] >= pivot) {
                    swap(nums, split, i);
                    split++;
                }
            }
            swap(nums, end, split);
            if (split == k) return nums[split];
            else if (split > k) return quickSelect(nums, 0, split - 1, k);
            else return quickSelect(nums, split + 1, end, k);
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }


    @Test
    public void test3(){
        int [] nums = {1,3,2,2,3,1};
        new Solution_2().wiggleSort(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

}

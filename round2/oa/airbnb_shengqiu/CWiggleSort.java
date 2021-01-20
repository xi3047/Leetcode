package round2.oa.airbnb_shengqiu;

/*
Assumption
1. the input is an array of integer
2. sort the array such that number at even position are greater than the number
at odd position
3. is there duplicate number? yes
4. what if have no solution, for example [2,2,2,2] ?

Approach:
1. The idea is first do a quick select to find the median of the array.
After the selction, all the number smaller or equal to median are on median's left
all the number greater than median are on median's right.
For example, [6,5,4,3,2,1] => [2,1,3,4,6,5]
then start from 1 and 4, from right to left, we put the element to a new array
then we can get [3,5,1,6,2,4]

Time: O(n) for quick select, O(n) for rearrange
Space: O(n) for temp array
*/
public class CWiggleSort {

    public static void main(String[] args) {
        CWiggleSort sol = new CWiggleSort();
        int[] nums = {100,1,100,1,100,1};
        sol.sort(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public void sort(int[] nums) {
        int len = nums.length;
        if (len % 2 == 0) {
            quickSelect(nums, 0, len - 1, len / 2);
        } else {
            quickSelect(nums, 0, len - 1, len / 2 + 1);
        }
        int[] temp = new int[len];
        int leftIndex = (len - 1) / 2 ;
        int rightIndex = len - 1;
        int index = 0;
        while (index < len) {
            if (index % 2 == 0) {
                temp[index++] = nums[leftIndex--];
            } else {
                temp[index++] = nums[rightIndex--];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }

    private void quickSelect(int[] nums, int left, int right, int k) {
        int pivot = left + (int)(Math.random() * (right - left + 1));
        swap(nums, pivot, right);
        int leftIndex = left;
        for (int i = left; i <= right - 1; i++) {
            if (nums[i] < nums[right]) {
                swap(nums, leftIndex++, i);
            }
        }
        swap(nums, leftIndex, right);
        if (leftIndex + 1 == k) {
            return;
        } else if (leftIndex + 1 < k) {
            quickSelect(nums, leftIndex + 1, right, k);
        } else {
            quickSelect(nums, left, leftIndex - 1, k);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

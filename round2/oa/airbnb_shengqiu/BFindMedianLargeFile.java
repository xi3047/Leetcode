package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. What is the size of the file => can not store into memory
2. What does the file store => integer
3. we want to find the median of integer in the file

Approach:
Let's say the file is 1 GB, we can not store it in the memory.
If we want to find median, we need to know the total number of integer in the file.
There are two ways, If we know the size of the file and we know it stores integer,
Then the size / 4 is the total number of integer in it. Or we can just sequentially scan
the file from start to end then we know the total number of integers.
After we get the number of integer in the file. And we know the integer ranges from Integer.MIN_VALUE
to Integer.MAX_VALUE. Then we pick one in between, scan the file entirely again, count how many number
smaller or equal to that. If count equal to half, then the largest number smaller or equal to the
guess is the result we want.

Time: O(32n) = O(n) where n is the size of array
we at most iterate the num 32 times
Space: O(1)
================================
*/
class BFindMedianLargeFile {
    public static void main(String[] args) {
        BFindMedianLargeFile sol = new BFindMedianLargeFile();
        int[] nums = new int[100];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        System.out.println(sol.findMedian(nums));
        Arrays.sort(nums);
        System.out.println(nums[49]);
        System.out.println(nums[50]);
    }

    public double findMedian(int[] nums) {
        int len = 0;
        for (int num : nums) {
            len++;
        }
        // 1, 2, 3, 4, 5 => k = 3
        // 1, 2, 3, 4, 5, 6 => k = 3, k = 4
        if (len % 2 == 1) {
            return findKthSmallest(nums, len / 2 + 1);
        } else {
            return (findKthSmallest(nums, len / 2) + findKthSmallest(nums, len / 2 + 1)) / 2.0;
        }
    }

    private double findKthSmallest(int[] nums, int k) {
        long left = Integer.MIN_VALUE;
        long right = Integer.MAX_VALUE;
        while (left <= right) {
            int count = 0;
            long res = left;
            long guess = left + (right - left) / 2;
            for (int num : nums) {
                if (num <= guess) {  // must use <= because guess might be the correct answer
                    count++;
                    res = Math.max(res, num);
                }
            }
            if (count == k) {
                return res;
            } else if (count < k) {
                left = guess + 1;
            } else {
                right = guess - 1;
            }
        }
        return left;
    }
}

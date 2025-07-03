package round4.array;

public class L4_MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            int[] nums1Copy = nums2;
            nums2 = nums1;
            nums1 = nums1Copy;
        }
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int half = total / 2;

        int left = 0, right = m;
        while (true) {
            // i is the partition index in nums1
            int i = left + (right - left) / 2;
            // j is the partition index in nums2
            int j = half - i;

            // Get values around the partition
            int aLeft = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int aRight = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int bLeft = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int bRight = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Correct partition found
            if (aLeft <= bRight && bLeft <= aRight) {
                if (total % 2 == 1) {
                    return Math.min(aRight, bRight); // odd length
                } else {
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2.0; // even length
                }
            } else if (aLeft > bRight) {
                right = i - 1; // move left in nums1
            } else {
                left = i + 1; // move right in nums1
            }
        }
    }
}

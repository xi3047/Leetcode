package round1;

public class KthSmallestInTwoSortedArray {
    public int kth(int[] arr1, int[] arr2, int k) {
        if (k > arr1.length + arr2.length || k < 1) return -1;
        return helper(arr1, arr2, arr1.length, arr2.length, k);
    }

    private int helper(int[] arr1, int[] arr2, int aleft, int bleft, int k) {
        if (k == 1) return Math.min(arr1[aleft], arr2[bleft]);

        if (arr1.length == 0) return arr2[k-1];
        if (arr2.length == 0) return arr1[k-1];

        aleft = Math.min(aleft, k / 2);
        bleft = Math.min(bleft, k / 2);

        if (arr1[aleft-1] < arr2[bleft-1]) {
            return helper(arr1, arr2, aleft, 0, aleft + k / 2);
        } else {
            return helper(arr1, arr2, 0, bleft, k / 2);
        }

    }


}

package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   22:24

 */
public class L162_findPeakElement {
    public static int findPeakElement(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int n = array.length;

        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == n - 1 || array[mid] >= array[mid + 1])) {
                return array[mid];
            } else if (mid > 0 && array[mid - 1] > array[mid]) { // left neighbor is greater, search left
                end = mid - 1;
            } else { // right neighbor is greater, search right
                start = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = { 50,30};
        Integer peak = findPeakElement(array);
        System.out.println(peak != -1 ? "Peak Element is " + peak : "No peak element!");
    }
}

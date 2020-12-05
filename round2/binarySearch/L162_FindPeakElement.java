package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/9/2020 7:06 PM
 * @topic round2.binarySearch
 * @link
 */
public class L162_FindPeakElement {
    public int findPeakElement(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int n = array.length;
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == n - 1 || array[mid] >= array[mid + 1])) return mid;
            else if (mid > 0 && array[mid - 1] > array[mid]) end = mid - 1;
            else start = mid + 1;
            }
        return -1;
    }

    public static void main(String[] args) {
        L162_FindPeakElement test = new L162_FindPeakElement();
        System.out.println(test.findPeakElement(new int[]{1, 2, 3, 1}));
    }
}

package round1;

/*
    @author: Xi Zhang
    @date:   1/26/19
    @time:   3:34 PM
 */
public class MergeSort {
    public int[] mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
        return array;
    }

    private void mergeSort(int[] array, int[] helper, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }

    private void merge(int[] array, int[] helper, int left, int mid, int right) {
        for (int i =left; i <= right; i++) {
            helper[i] = array[i];
        }
        int leftIndex = left;
        int rightIndex = mid + 1;
        int cur = left;
        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] < helper[rightIndex]) {
                array[cur++] = helper[leftIndex++];
            } else {
                array[cur++] = helper[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            array[cur++] = helper[leftIndex++];
        }
    }



}

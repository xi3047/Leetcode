package round2.sort;

/**
 * @author Xi Zhang
 * @date 12/6/2020 11:26 PM
 * @topic round2.sort
 * @link
 * @description Selection Sort
 */
public class SelectionSort {
    public void sort (int arr[]) {
        int n = arr.length;
        for (int i = 0; i < arr.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) min_idx = j;
            }
            swap(arr, min_idx, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int copyI = arr[i];
        arr[i] = arr[j];
        arr[j] = copyI;
    }
}

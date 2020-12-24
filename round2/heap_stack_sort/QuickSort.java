package round2.heap_stack_sort;

import java.util.Random;

/**
 * @author Xi Zhang
 * @date 12/6/2020 11:29 PM
 * @topic round2.sort
 * @link
 * @description Quick Sort with random pivot
 */
public class QuickSort {
    public void quickSort(int[] arr) {
        shuffle(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int split = partition(array, start, end);
            quickSort(array, start, split - 1);
            quickSort(array, split + 1, end);
        }
    }
    public int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int split = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                swap(array, pivot, i);
                split++;
            }
        }
        swap(array, end, split);
        return split;
    }

    private void swap (int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void shuffle(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int r = random.nextInt(i + 1);
            swap(array, r, i);
        }
    }
}

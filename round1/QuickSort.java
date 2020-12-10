package round1;


import java.util.Arrays;

public class QuickSort {
    public void quickSort(int[] array) {
        if (array == null) return;
        quickSort(array, 0, array.length-1);
    }
    public void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int split = partition(array, start, end);
            quickSort(array, start, split-1);
            quickSort(array, split + 1, end);
        }
    }
    public int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int split = start;
        for (int i = start ; i < end; i++) {
            if (array[i] <= pivot) {
                swap(array, split, i);
                split++;
            }
        }
        swap(array,split,end);
        return split;
    }

    private void swap (int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort solution = new QuickSort();

        int[] array = null;
        solution.quickSort(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {3, 2, 6, 8, 9, 1, 4, 7, 5};
        solution.quickSort(array);
        System.out.println(Arrays.toString(array));




    }
}

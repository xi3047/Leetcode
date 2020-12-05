package round2.binarySearch;

import java.util.Random;

public class L528_RandomPickWithWeight {

    Random rand;
    int [] weights;
    public L528_RandomPickWithWeight(int[] w) {
        this.rand = new Random();
        this.weights = w.clone();
        for (int i = 1; i < weights.length; i++) {
            weights[i] += weights[i - 1];
        }
    }

    public int pickIndex() {
        int guess = rand.nextInt(weights[weights.length - 1]) + 1;
        int pos = L528_RandomPickWithWeight.binarySearch(weights, guess);
        return pos;
    }

    private static int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) return mid;
            else if (array[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int [] a = new int[] {3, 5, 6, 10};
        int pos = L528_RandomPickWithWeight.binarySearch(a, 9);
        System.out.println(pos);
    }
}

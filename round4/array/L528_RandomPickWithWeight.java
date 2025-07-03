package round4.array;

import java.util.Random;

public class L528_RandomPickWithWeight {
    Random rand;
    int[] weights;

    public L528_RandomPickWithWeight(int[] w) {
        this.rand = new Random();
        this.weights = new int[w.length];
        weights[0] = w[0];
        for (int i = 1; i < weights.length; i++) {
            weights[i] = weights[i-1] + w[i];
        }
    }

    public int pickIndex() {
        int total = weights[weights.length - 1];
        int guess = rand.nextInt(total);
        int left = 0, right = weights.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess < weights[mid]) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 3  2  5
     * 3  5  10
     * 0-2 3-4 5-9
     * rand: 5, r = 0
     * rand >= midV : left = mid + 1
     * rand < midV : right = mid - 1
     *
     * @return
     */



}

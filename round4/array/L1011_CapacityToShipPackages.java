package round4.array;

import java.util.Arrays;

public class L1011_CapacityToShipPackages {
    public int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt(); // max weight
        int right = Arrays.stream(weights).sum();
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (!canShip(weights, days, mid)) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean canShip(int[] weights, int days, int capacity) {
        int count = 1;
        int cur = 0;
        for (int weight : weights) {
            if (cur + weight <= capacity) {
                cur += weight;
            } else {
                count++;
                cur = weight;
            }
        }
        return count <= days;
    }
}

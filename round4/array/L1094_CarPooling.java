package round4.array;

public class L1094_CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] changes = new int[1001];

        for (int[] trip : trips) {
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            changes[from] += numPassengers;  // pick up passengers
            changes[to] -= numPassengers;    // drop off passengers
        }

        int currentPassengers = 0;
        for (int i = 0; i < 1001; i++) {
            currentPassengers += changes[i];
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}

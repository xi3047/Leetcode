package round4.array;

import java.util.List;
import java.util.Random;

/**
 * Meta Interview Question 07/02/2025
 */

public class TreasureChest {
    List<Goodie> goodies;
    int[] ranges;
    Random rand;

    public TreasureChest(List<Goodie> input) {
        goodies = input;
        ranges[0] = goodies.get(0).freq;
        for (int i = 1; i < goodies.size(); i++) {
            ranges[i] = ranges[i - 1] + goodies.get(i).freq;
        }
        rand = new Random();
    }

    /**
     * 300     100     30        400
     * 300     400     430       830
     * 0-299  300-399  400-429  430-829
     */
    public String pick() {
        int left = 0, right = ranges.length;
        int guess = rand.nextInt(ranges[ranges.length - 1]);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess >= ranges[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return goodies.get(left).name;
    }














    class Goodie {
        String name;
        int freq;
        Goodie(String name, int freq) {
            this.name = name;
            this.freq = freq;
        }
    }
}



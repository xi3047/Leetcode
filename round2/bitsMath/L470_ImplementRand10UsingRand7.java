package round2.bitsMath;

import java.util.Random;

/**
 * @author Xi Zhang
 * @date 12/13/2020 7:29 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/implement-rand10-using-rand7/
 * @description
 */
public class L470_ImplementRand10UsingRand7 {
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = (col - 1) + (row - 1) * 7;
        } while (idx >= 40);
        return 1 + idx % 10;
    }

    private int rand7(){
        Random random = new Random();
        return random.nextInt(7) + 1;
    }
}

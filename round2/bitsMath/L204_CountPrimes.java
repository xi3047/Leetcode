package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 12/5/2020 8:17 PM
 * @topic round2.bitsMath
 * @link
 * @description
 */
public class L204_CountPrimes {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        return count;
    }
}

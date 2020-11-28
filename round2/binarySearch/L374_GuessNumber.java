package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/3/2020 10:51 PM
 * @topic round2.binarySearch
 * @link
 */
public class L374_GuessNumber {
    public int L374_GuessNumber(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == 0) return mid;
            else if (guess(mid) == -1) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    private static int guess(int n) {
        return 0;
    }



}

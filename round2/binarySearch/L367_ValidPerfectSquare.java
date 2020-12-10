package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 12/8/2020 10:47 PM
 * @topic round2.binarySearch
 * @link
 * @description
 */
public class L367_ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return  true;
        long low = 2;
        long high = num / 2;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long sqr = mid * mid;
            if (sqr == num) return true;
            else if (sqr < num) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(808201 * 808201);
    }
}

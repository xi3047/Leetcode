package round4.array;

public class L1891_CuttingRibbon {
    public int maxLength(int[] ribbons, int k) {
        int left = 1;
        int right = 0;
        for (int ribbon : ribbons) {
            right = Math.max(ribbon, right);
        }
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int ribbon : ribbons) {
                count += ribbon / mid;
            }
            if (count >= k) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}

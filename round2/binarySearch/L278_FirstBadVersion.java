package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/4/2020 3:14 AM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/first-bad-version/
 */
public class L278_FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (!isBadVersion(mid)) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }

    private boolean isBadVersion(int version) {
        return true;
    }
}

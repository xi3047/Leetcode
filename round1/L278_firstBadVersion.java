package round1;

/*
 * Leetcode 278 First Bad Version
 * https://leetcode.com/problems/first-bad-version/
 * Author: Xi Zhang
 * May 30, 2019
 */

// Solution 2:
// left < right, left = mid + 1

class L278_firstBadVersion {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) end = mid - 1;
            else start = mid + 1;
        }
        return start;

    }

    private boolean isBadVersion(int mid) {
        return false;
    }

}

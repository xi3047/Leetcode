package round4.array;

public class L875_KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int pile :piles) {
            left = Math.min(pile, left);
            right = Math.max(pile, right);
        }
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (overEat(piles, mid, h)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean overEat(int[] piles, int mid, int h) {
        long hoursNeeded = 0;
        for (int pile : piles) {
            if (pile % mid != 0) hoursNeeded++;
            hoursNeeded += pile / mid;
        }
        return hoursNeeded <= h;
    }
}

package round4.array;

public class L605_CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i <flowerbed.length; i++) {
            if (flowerbed[i] == 0 && canPlace(flowerbed, i)) {
                count++;
            }
        }
        return count >= n;
    }
    private boolean canPlace(int [] nums, int i) {
        if ((i == 0 || nums[i - 1] == 0) && (i == nums.length - 1 || nums[i + 1] == 0)) {
            nums[i] = 1;
            return true;
        }
        return false;
    }
}

package round4.DP;

public class L55_JumpGame {
    public boolean Jump(int[] nums) {
        Boolean[] memo = new Boolean[nums.length];
        return dfs(nums, memo, 0);
    }
    private boolean dfs(int[] nums, Boolean[] memo, int index) {
        if (memo[index] != null) {
            return memo[index];
        }
        if (index >= nums.length - 1) {
            memo[index] = true;
            return true;
        }

        int jumpCount = nums[index];
        while (jumpCount > 0) {
            if(dfs(nums, memo, index + jumpCount)) {
                return true;
            }
            jumpCount--;
        }
        memo[index] = false;
        return false;
    }

    public boolean canJump(int[] nums) {
        int furthestJump = 0;
        for (int i = 0; i < nums.length; i++) {
            if (furthestJump < i) return false;
            furthestJump = Math.max(furthestJump, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        L55_JumpGame jump = new L55_JumpGame();
        System.out.println(jump.Jump(new int[]{3, 1, 0, 3, 1, 0, 4}));
    }
}

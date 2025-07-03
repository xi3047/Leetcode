package round4.array;

public class L303_RangeSumQueryImmutable {
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
    class NumArray {
        int[] prefixSum ;

        public NumArray(int[] nums) {
            this.prefixSum = new int[nums.length + 1];
            prefixSum[0] = 0;
            for (int i = 1; i < prefixSum.length; i++) {
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left ];
        }
    }
}

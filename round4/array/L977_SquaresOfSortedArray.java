package round4.array;

public class L977_SquaresOfSortedArray {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] res = new int[nums.length];
        int n = nums.length - 1;
        while (n >= 0) {
            int leftV = Math.abs(nums[left]);
            int rightV = Math.abs(nums[right]);
            if (leftV > rightV) {
                res[n] = leftV * leftV;
                left++;
            } else {
                res[n] = rightV * rightV;
                right--;
            }
            n--;
        }
        return res;
    }
}

package round2.oa.airbnb_shengqiu;

public class DMaxNight {
    public static void main(String[] args) {
        DMaxNight sol = new DMaxNight();
        int[] test1 = {4,10,3,1,5};
        int[] test2 = {2};
        int[] test3 = {1,2};
        System.out.println(sol.maxNightCanAccommodate(test1));
        System.out.println(sol.maxNightCanAccommodate(test2));
        System.out.println(sol.maxNightCanAccommodate(test3));
    }

    public int maxNightCanAccommodate(int[] nums) {
        int one = 0;
        int two = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(one + nums[i], two);
            one = two;
            two = cur;
        }
        return two;
    }
}

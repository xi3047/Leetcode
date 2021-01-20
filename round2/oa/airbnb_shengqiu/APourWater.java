package round2.oa.airbnb_shengqiu;/*
Assumption:
1. input is array of integer, represents the height of ground
2. print the graph after water drop
3. water will go out from left side and right side
4. water first go left to drop, if can not find a place, go right
5. otherwise, drop at original place

Simulate the drop one by one
##wwwww###
###w##w###
##########
##########

Time: O(nk) + O(nh)
k is the number of drop, n is the length of the array.
drop water is O(nk), print graph takes O(nh) where h is the max number of the array
Space: O(n) to store the water
 */

class APourWater {
    public static void main(String[] args) {
        APourWater sol = new APourWater();
        int[] heights = {4,5,6,1,3,5,4};
        sol.pourWater(heights, 5, 14);
    }

    public void pourWater(int[] heights, int index, int k) {
        int[] water = new int[heights.length];
        print(heights, water);
        while (k > 0) {
            k--;
            // go left to drop
            int i = index;
            while (i - 1 >= 0 && heights[i - 1] + water[i - 1] <= heights[i] + water[i]) {
                i--;
            }
            int leftMost = i;
            if (heights[leftMost] + water[leftMost] < heights[index] + water[index] && leftMost != 0) {
                water[leftMost]++;
                continue;
            }
            i = index;
            while (i + 1 < heights.length && heights[i + 1] + water[i + 1] <= heights[i] + water[i]) {
                i++;
            }
            int rightMost = i;
            if (heights[rightMost] + water[rightMost] < heights[index] + water[index] && rightMost != heights.length - 1) {
                water[rightMost]++;
                continue;
            }
            if (leftMost == 0 || rightMost == heights.length - 1) {
                break;
            }
            heights[index]++;
        }
        print(heights, water);
    }

    public void print(int[] heights, int[] water) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] + water[i]);
        }
        for (int i = max; i > 0; i--) {
            for (int j = 0; j < heights.length; j++) {
                if (heights[j] + water[j] < i) {
                    System.out.print(" ");
                } else if (heights[j] + water[j] >= i && heights[j] < i){
                    System.out.print("w");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
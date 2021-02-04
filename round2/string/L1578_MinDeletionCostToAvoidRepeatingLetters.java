package round2.string;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 2/2/21 12:39 上午
 * @topic round2.string
 * @link https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
 * @description
 * Given a string s and an array of integers cost where cost[i] is the cost of deleting the ith character in s.
 *
 * Return the minimum cost of deletions such that there are no two identical letters next to each other.
 *
 * Notice that you will delete the chosen characters at the same time, in other words, after deleting a character, the
 * costs of deleting other characters will not change.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abaac", cost = [1,2,3,4,5]
 * Output: 3
 * Explanation: Delete the letter "a" with cost 3 to get "abac" (String without two identical letters next to each other).
 * Example 2:
 *
 * Input: s = "abc", cost = [1,2,3]
 * Output: 0
 * Explanation: You don't need to delete any character because there are no identical letters next to each other.
 * Example 3:
 *
 * Input: s = "aabaa", cost = [1,2,3,4,1]
 * Output: 2
 * Explanation: Delete the first and the last character, getting the string ("aba").
 */
public class L1578_MinDeletionCostToAvoidRepeatingLetters {
    /**
     * Intuition
     * For a group of continuous same characters,
     * we need to delete all the group but leave only one character.
     *
     *
     * Explanation
     * For each group of continuous same characters,
     * we need cost = sum_cost(group) - max_cost(group)
     */
    public int minCost(String s, int[] cost) {
        char arr[] = s.toCharArray();
        int ans = 0;

        int costsum = cost[0];
        int maxcost = cost[0];
        for( int i = 1; i < arr.length; i++){
            if(arr[i] == arr[i-1]){
                costsum += cost[i];
                maxcost = Math.max(maxcost, cost[i]);
            }else{
                ans += costsum-maxcost;
                costsum = cost[i];
                maxcost = cost[i];
            }
        }
        ans += costsum-maxcost;
        return ans;
    }

    @Test
    public void test(){
        System.out.println(minCost("abaac", new int[] {1, 2, 3,4,5}));
    }

}

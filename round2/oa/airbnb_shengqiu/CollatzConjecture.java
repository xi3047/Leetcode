package round2.oa.airbnb_shengqiu;

import java.util.HashMap;
import java.util.Map;
/*
Assumption:
1. The input is a integer n
2. return the max steps we can find for value from 1 to n
3. Assume The number will become 1 in the end

Approach:
The idea is straightforward, for each number, in a while loop,
we keep apply change to the number until it become 1. If the
step is greater than a global max, we update global max.
Also, to reduce the number of unnecessay computation, we can use
a hash table to memorize the steps for number we have found.

Time: O(n*k) k is the average steps to become 1
Space: O(n)
*/
public class CollatzConjecture {

    public static void main(String[] args) {
        CollatzConjecture sol = new CollatzConjecture();
        System.out.println(sol.findMax(1000000));
    }

    public int findMax(int n) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int curr = i;
            int step = 0;
            while (curr != 1) {
                if (map.containsKey(curr)) {
                    step += map.get(curr);
                    break;
                }
                step++;
                if (curr % 2 == 0) {
                    curr = curr / 2;
                } else {
                    curr = curr * 3 + 1;
                }
            }
            map.put(i, step);
            max = Math.max(max, step);
        }
        return max;
    }
}
// Recursion
//     public int findMax(int n) {
//         int max = 0;
//         Map<Integer, Integer> map = new HashMap<>();
//         for (int i = 1; i <= n; i++) {
//             int step = findStep(i, map);
//             map.put(i, step);
//             max = Math.max(max, step);
//         }
//         return max;
//     }

//     private int findStep(int num, Map<Integer, Integer> map) {
//         if (num == 1) return 0;
//         if (map.containsKey(num)) return map.get(num);
//         if (num % 2 == 1) {
//             return 1 + findStep(3 * num + 1, map);
//         } else {
//             return 1 + findStep(num / 2, map);
//         }
//     }
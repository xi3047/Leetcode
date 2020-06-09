package geeksforgeeks;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StringConcatenation {
        //can you use subsequence of b to make a
        int min;
        public boolean valid(String a, String b) {
            if (a == null || b == null || a.length() == 0 || b.length() == 0) return true;

            Set<Character> seen = new HashSet<>();

            for (char c : b.toCharArray()) {
                seen.add(c);
            }

            for (char c : a.toCharArray()) {
                if (!seen.contains(c)) return false;
            }

            return true;
        }
        //what's the minimum copy of b required to concatinate into a
 /*   //m * n
    public int minCopy(String a, String b) {
        //c.c

        min = a.length();
        dfs(a, b, 0, 0, 0);

        return min;

    }
 */
        public int numOfCombination(String a, String b) {
            min = a.length();
            int[] mins = new int[min + 1];
            dfs(a, b , mins, 0, 0, 0);
            System.out.println(min);
            return mins[min];
        }

        private void dfs(String a, String b, int[] mins, int i, int j, int count) {
            if (count > min) return;
            if (i == a.length()) {
                if (j != b.length()) count++;
                if (count <= min) {
                    min = count;
                    mins[min]++;
                }
                //min = Math.min(min, count);
                //mins[min]++;
                return;
            }
            int idx = j;
            if (j == b.length()) {
                count++;
                idx = 0;
            }

            if (a.charAt(i) != b.charAt(idx)) {
                dfs(a, b, mins, i, idx + 1, count);
            } else {
                dfs(a, b, mins, i + 1, idx + 1, count);
                dfs(a, b, mins, i, idx + 1, count);
            }

        }


        @Test
        public void test() {
            System.out.println(numOfCombination("ZAZA", "XAZB"));
        }


}

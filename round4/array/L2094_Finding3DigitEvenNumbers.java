package round4.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L2094_Finding3DigitEvenNumbers {
    public int[] findEvenNumbers(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int[] freq = new int[10];
        for (int digit : digits) {
            freq[digit]++;
        }

        for (int i = 100; i < 999; i+=2) {
            if(helper(i, freq)) {
                res.add(i);
            }
        }
        int[] ans = new int[res.size()];
        for (int i=0; i<ans.length;i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private boolean helper(int num, int[] map) {
        int[] copy = map.clone();
        while (num > 0) {
            int cur = num % 10;
            copy[cur]--;
            if (copy[cur] < 0) return false;
            num /= 10;
        }
        return true;
    }
}

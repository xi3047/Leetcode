package round4.array;

import java.util.HashSet;
import java.util.Set;

public class L202_HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);

        while (slow != fast && fast != 1) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }
    private int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int digit = n % 10;
            total += digit * digit;
            n /= 10;
        }
        return total;
    }

    public boolean isHappy2(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

}

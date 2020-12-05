package round1;

public class L277_FindCelebrity {
    public int findCelebrity(int n) {
        /**
         *    first pass to find the potential candidate
         *          we can discard one person by calling knows(i, j)
         *          if knows(i, j) is true, then i is not a celebrity
         *          if knows(i, j) is false, then j is not a celebrity
         */
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        // second pass to check all others to this candidate to make sure he is a celebrity
        for (int i = 0; i < n; i++) {
            if (i == candidate) {
                continue;
            }
            if (!knows(i, candidate) || knows(candidate, i)) {
                return -1;
            }
        }
        return candidate;

    }

    private boolean knows(int candidate, int i) {
        return false;
    }
}

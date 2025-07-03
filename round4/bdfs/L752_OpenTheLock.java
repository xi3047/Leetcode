package round4.bdfs;

import java.util.*;

public class L752_OpenTheLock {
    public int openLock(String[] deadends, String target) {
        int step = 0;
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        set.add("0000");

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String s = q.poll();
                if (s.equals(target)) {
                    return step;
                }
                List<String> nexts = convert(s);
                for (String next : nexts) {
                    if (set.add(next)) {
                        q.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> convert(String code) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = code.toCharArray();

        for (int i = 0; i < 4; i++) {
            char original = chars[i];

            // Move up
            chars[i] = original == '9' ? '0' : (char)(original + 1);
            neighbors.add(new String(chars));

            // Move down
            chars[i] = original == '0' ? '9' : (char)(original - 1);
            neighbors.add(new String(chars));

            // Restore original for next digit
            chars[i] = original;
        }

        return neighbors;
    }

}

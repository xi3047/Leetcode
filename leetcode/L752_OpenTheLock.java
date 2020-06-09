package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-05-02
    @time:   14:12
 */
import org.junit.Test;

import java.util.*;

public class L752_OpenTheLock {
    public int openLock(String[] deadends, String target) {
        String start = "0000";
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> deadEnds = new HashSet<>();
        deadEnds.addAll(Arrays.asList(deadends));
        queue.offer(start);

        int len = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (deadEnds.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) return len;
                StringBuilder sb = new StringBuilder(cur);
                for (int i = 0; i < 4; i++) {
                    char c = sb.charAt(i);
                    String up = sb.substring(0, i) + (c =='9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String down = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                    if (!visited.contains(up) && !deadEnds.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    if (!visited.contains(down) && !deadEnds.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            len++;
        }
        return -1;
    }



    @Test
    public void test () {

    }

}

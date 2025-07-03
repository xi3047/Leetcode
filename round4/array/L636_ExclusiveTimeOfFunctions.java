package round4.array;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class L636_ExclusiveTimeOfFunctions {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        int prevTime = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            boolean isStart = parts[1].equals("start");
            int time = Integer.parseInt(parts[2]);

            if(isStart) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += time - prevTime;
                }
                stack.push(id);
                prevTime = time;
            } else {
                res[stack.pop()] += time - prevTime + 1;
                prevTime = time + 1;
            }
        }
        return res;
    }
}

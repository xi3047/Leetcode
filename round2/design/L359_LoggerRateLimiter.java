package round2.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 1/8/21 10:23 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/logger-rate-limiter/
 * @description
 *
 */
public class L359_LoggerRateLimiter {
    /** Initialize your data structure here. */
    Map<String, Integer> map;
    public L359_LoggerRateLimiter() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        } else {
            if (timestamp < map.get(message) + 10) {
                return false;
            } else {
                map.put(message, timestamp);
                return true;
            }
        }
    }
}

package round1_misc.OOD.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IDsOfPackages {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    ArrayList<Integer> IDsOfPackages(int rideDuration,
                                     ArrayList<Integer> songDurations)
    {
        // WRITE YOUR CODE HERE
        ArrayList<Integer> res = new ArrayList<>();

        if (songDurations == null || songDurations.size() < 2) return res;

        int target = rideDuration - 30;

        Map<Integer, Integer> map = new HashMap<>();

        int max = 0;
        for (int i = 0; i < songDurations.size(); i ++) {
            int space = songDurations.get(i);

            if (map.containsKey(target - space)) {

                if (space >= max || target - space >= max) {
                    res = new ArrayList<>();
                    res.add(map.get(target - space));
                    res.add(i);
                    max = Math.max(target - space, space);
                }
            }
            map.put(space, i);
        }
        return res;

    }

}

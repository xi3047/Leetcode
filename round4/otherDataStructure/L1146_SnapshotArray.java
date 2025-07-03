package round4.otherDataStructure;

import java.util.*;

public class L1146_SnapshotArray {
    /**
     * Using treeMap
     */
    class SnapshotArray {
        List<TreeMap<Integer, Integer>> history;
        int snapID;
        public SnapshotArray(int length) {
            history = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                TreeMap<Integer, Integer> map = new TreeMap<>();
                map.put(0, 0);
                history.add(map);
            }
            snapID = 0;
        }

        public void set(int index, int val) {
            history.get(index).put(snapID, val);
        }

        public int snap() {
            return snapID++;
        }

        public int get(int index, int snap_id) {
            int lastSnapID = history.get(index).floorKey(snap_id);
            return history.get(index).get(lastSnapID);
        }
    }

    /**
     * Using binary search
     */
    class SnapshotArray2 {
        List<List<int[]>> history;
        int snapID;

        public SnapshotArray2(int length) {
            history = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{0, 0}); // default value at snap 0
                history.add(list);
            }
            snapID = 0;
        }

        public void set(int index, int val) {
            List<int[]> records = history.get(index);
            if (records.get(records.size() - 1)[0] == snapID) {
                records.get(records.size() - 1)[1] = val; // overwrite same snapID
            } else {
                records.add(new int[]{snapID, val}); // new record
            }
        }

        public int snap() {
            return snapID++;
        }

        public int get(int index, int snap_id) {
            List<int[]> records = history.get(index);
            int left = 0, right = records.size() - 1;

            // Binary search to find largest snap_id <= given snap_id
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (records.get(mid)[0] <= snap_id) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return records.get(right)[1];
        }
    }

}

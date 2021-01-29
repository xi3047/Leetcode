package round2.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 1/21/2021 12:13 AM
 * @topic round2.design
 * @link https://leetcode.com/problems/design-file-system/
 * @description
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English
 * letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 * bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true.
 * Returns false if the path already exists or its parent path doesn't exist.
 * int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 *
 * Example 1:
 *
 * Input:
 * ["FileSystem","createPath","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 * Example 2:
 *
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 */
public class L1166_FilesSystem {
    class FileSystem {
    Map<String, Integer> map;
        public FileSystem() {
            map = new HashMap<>();
            map.put("", 0);
        }
        public boolean createPath(String path, int value) {
            if (map.containsKey(path)) return false;
            int lastSlash = path.lastIndexOf("/");
            String parentPath = path.substring(0, lastSlash);
            if (!map.containsKey(parentPath)) return false;
            map.put(path, value);
            return true;
        }

        public int get(String path) {
            if (map.containsKey(path)) {
                return map.get(path);
            }
            return -1;
        }
    }

}

package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. Implement a FileSystem, support create and get
2. We can only create on a path already existed
3. If create on a path not existed, return false ? throw an exception
4. Assume the root path is "/" and we create under it.

Approach:
Use a hash map to store path and value pair
*/

class Solution {
    public static void main(String[] args) {
        AFileSystem fileSystem = new AFileSystem();
        System.out.println(fileSystem.create("a", 1));
        System.out.println(fileSystem.create("/a", 1));
        System.out.println(fileSystem.create("/a/b", 2));
        System.out.println(fileSystem.create("/c/b", 3));
        fileSystem.watch("/a/b", "on path /a/b");
        fileSystem.watch("/a", "on path /a");
        fileSystem.watch("/", "on path /");
        System.out.println(fileSystem.create("/a/b/c", 2));
        System.out.println(fileSystem.get("/a/b/c"));
        System.out.println(fileSystem.get("/a/b/c/d"));
    }
}

public class AFileSystem {
    Map<String, Integer> map;
    Map<String, Runnable> watchMap;

    public AFileSystem() {
        this.map = new HashMap<>();
        this.watchMap = new HashMap<>();
        map.put("/", 0);
    }

    public boolean create(String path, int val)  {
        if (map.containsKey(path)) {
            return false;
        }
        int index = path.lastIndexOf("/");
        if (index == -1 || !map.containsKey(path.substring(0, index)) && index != 0) {
            return false;
        }
        map.put(path, val);
        while (path.length() > 0) {
            if (watchMap.containsKey(path)) {
                watchMap.get(path).run();
            }
            path = path.substring(0, path.lastIndexOf("/"));
        }
        return true;
    }

    public Integer get(String path) {
        return map.get(path);
    }

    public boolean watch(String path, String message) {
        if (!map.containsKey(path)) {
            return false;
        }
        watchMap.put(path, () -> {
            System.out.println(message);
        });
        return true;
    }
}

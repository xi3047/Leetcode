package round2.oa.airbnb;

/**
 * @author Xi Zhang
 * @date 2/1/21 11:33 上午
 * @topic round2.oa.airbnb
 * @link
 * @description
 */
import java.util.*;

public class minUsersFollow {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>());
        map.get(0).add(1);
        map.put(1, new ArrayList<>());
        map.get(1).add(2);
        map.put(2, new ArrayList<>());
        map.get(2).add(0);
        map.put(3, new ArrayList<>());
        map.get(3).add(4);
        map.put(4, new ArrayList<>());
        map.get(4).add(3);
        System.out.println(minimumUsers(map, 5));
    }

    private static int find(int[] parents, int u) {
        while (u != parents[u]) {
            parents[u] = parents[parents[u]];
            u = parents[u];
        }
        return u;
    }

    // with cycle
    // union find
    public static List<Integer> minimumUsers(Map<Integer, List<Integer>> map, int n) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        Set<Integer> set = new HashSet<>();
        for (Integer user : map.keySet()) {
            for (Integer follower : map.get(user)) {
                int u = find(parents, user);
                int v = find(parents, follower);
                if (u != v) {
                    parents[u] = v;
                    n--;
                }
            }
        }
        for (int i : parents) {
            set.add(i);
        }

        return new ArrayList<>(set);

        // build inDegree
//        Map<Integer, Integer> inDegree = new HashMap<>();
//        for (Integer user : map.keySet()) {
//            inDegree.putIfAbsent(user, 0);
//            for (Integer follower : map.get(user)) {
//                inDegree.put(follower, inDegree.getOrDefault(follower, 0) + 1);
//            }
//        }
//        List<Integer> res = new ArrayList<>();
//        for (Integer user: inDegree.keySet()) {
//            if (inDegree.get(user) == 0) res.add(user);
//        }


//        List<Integer> usersSet = new ArrayList<>();
//        for (int user : map.keySet()) {
//            usersSet.add(user);
//            for (int follower: map.get(user)) {
//                usersSet.add(follower);
//            }
//        }
//
//        // elimination
//
//        List<Integer> oneUserFromCycle = new ArrayList<>();
//        while (!usersSet.isEmpty()) {
//            int cur = usersSet.get(0);
//            if (containsCycle(cur, usersSet, new HashSet<>(), map)) {
//                oneUserFromCycle.add(cur);
//            }
//        }
//        return oneUserFromCycle;

    }
    // 1 -> 0 -> 1,  4 -> 5 -> 4
    private static boolean containsCycle(int user, List<Integer> usersSet, HashSet<Integer> visited, Map<Integer, List<Integer>> map ) {
        if (visited.contains(user)) {
            return true;
        }
        visited.add(user);
        int position = usersSet.indexOf(user);
        usersSet.remove(position);
        for (Integer follower: map.get(user)) {
            if (containsCycle(follower, usersSet, visited, map)) return true;
        }
        visited.remove(user);
        return false;
    }

}


//1. User i may follow user j, but user j may not follow user i.
//2. We want to send referral bonus to a subset of users to ask them to broadcast a new feature such that all N users will know this feature in the end.
//3. We assume that if user i sends a tweet, all the users following i will be notified and all of these followers will broadcast to its' followers and so on.
//4. We want to find the minimum number of users we want to send referral bonus such that all N users will be notified about this new feature.


//if  entry (i, j) = 1, user *j* follows user *i*.

// 1 follows 0
// 0 => 1,  1 => 0, 2 => 1, 3 => 2

// 3 -> 2 ,  1 -> 0 -> 1,  4 -> 5 -> 4
//  countOfSets     Set(1, 0) , Set(4,5)
// [[ 0, 1, 0, 0],
// [1, 0, 0 , 0],
// [0, 1, 0, 0],
// [0, 0, 1, 0]]

// Answer: [3]

// cong.zhao@airbnb.com

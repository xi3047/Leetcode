package round2.oa.airbnb_shengqiu;/*
Assumption:
1. The given input is a list of list, each list contains one flight
information as ["A", "B", "100"]
   Also we know the start position and the end position given as String
   Given a integer represents the number of stop we can have
2. return the minimum cost from start to end, if can not reach, return -1
3. The cost >= 0, that is no negative cost
4. k < 1000

Approach: DFS
So, based on our assumption and we know k should not be too big in real
life. The structure of the graph will looks more wide than tall.
So, rather than BFS, we can choose DFS to solve this problem.
Let me go through an example here, let's say we have a graph like this:
Graph:
      A
     / \
    B ->C -> D

Recursion Tree:
0        A
       / |
1     B  C
     /  |
2   C   D
    |
3   D
Also, to reduce the unnecessary search, we can do some memorization during
DFS. We can use a map to store the min cost and min stop for a node so far.
If later path comes, if both cost and stop greater than the value in maps,
we should not go further.

Time: O(V^(k+1))
In the worst case, each level we have n flight, we can go k levels, therefore, time complexity is
Space: O(V + E + k + 1)
O(k) for the call stack, O(V + E) to store the path information
=======================
follow up:
1. DFS vs. BFS, for time complexity, BFS is similar with DFS with proper
pruning. But the space complexity, BFS is much larger, the size of queue
can grow very fast
 */

import java.util.*;

public class ACheapestFlight {

    public static void main(String[] args) {
        DFS sol = new DFS();
        List<List<String>> flights = new ArrayList<>();
        flights.add(Arrays.asList("A", "C", "300"));
        flights.add(Arrays.asList("A", "B", "100"));
        flights.add(Arrays.asList("B", "C", "100"));
        flights.add(Arrays.asList("A", "D", "700"));
        flights.add(Arrays.asList("C", "D", "200"));
        flights.add(Arrays.asList("D", "B", "2"));
        System.out.print(sol.findMinCost(flights, "A", "C", 1));
        System.out.println(sol.getPath());
    }
}

class DFS {

    private int minCost = Integer.MAX_VALUE;
    private List<String> minPath;
    private Map<String, Integer> costMap;  // this two memorization hash map must work together
    private Map<String, Integer> stopMap;
    private Map<String, Map<String, Integer>> map;

    public int findMinCost(List<List<String>> flights, String from, String to, int k) {
        this.map = new HashMap<>();
        this.costMap = new HashMap<>();
        this.stopMap = new HashMap<>();
        for (List<String> flight : flights) {
            map.putIfAbsent(flight.get(0), new HashMap<>());
            map.putIfAbsent(flight.get(1), new HashMap<>());
            map.get(flight.get(0)).put(flight.get(1), Integer.parseInt(flight.get(2)));
        }
        for (String city : map.keySet()) {
            this.costMap.put(city, Integer.MAX_VALUE);
            this.stopMap.put(city, Integer.MAX_VALUE);
        }
        dfs(new HashSet<>(), new ArrayList<>(), from, to, k,-1, 0);
        return minCost == Integer.MAX_VALUE ? -1 : this.minCost;
    }

    private void dfs(Set<String> visited, List<String> curPath, String cur, String to, int k, int stop,  int cost) {
        if (stop > k || visited.contains(cur)) {
            return;
        }
        visited.add(cur);
        curPath.add(cur);
        if (cur.equals(to)) {
            if (cost < minCost) {
                this.minPath = new ArrayList<>(curPath);
                this.minCost = cost;
            }
            curPath.remove(curPath.size() - 1);
            visited.remove(cur);
            return;
        }
        for (Map.Entry<String, Integer> entry : map.get(cur).entrySet()) {
            int newCost = cost + entry.getValue();
            int newStop = stop + 1;
            String next = entry.getKey();
            if (newCost >= costMap.get(next) && newStop >= stopMap.get(next)) continue;
            if (newCost < costMap.get(next) && newStop < stopMap.get(next)) {
                costMap.put(next, newCost);
                stopMap.put(next, newStop);
            }
            dfs(visited, curPath, next, to, k, newStop, newCost);
        }
        curPath.remove(curPath.size() - 1);
        visited.remove(cur);
    }

    public List<String> getPath() {
        return this.minPath;
    }
}


class Dijk {

    private Map<String, Integer> costMap;  // this two memorization hash map must work together
    private Map<String, Integer> stopMap;
    private List<String> minPath;
    private Map<String, Map<String, Integer>> map;

    public static void main(String[] args) {
        Dijk sol = new Dijk();
        List<List<String>> flights = new ArrayList<>();
        flights.add(Arrays.asList("A", "B", "100"));
        flights.add(Arrays.asList("B", "C", "100"));
        flights.add(Arrays.asList("A", "C", "250"));
        flights.add(Arrays.asList("C", "D", "200"));
        flights.add(Arrays.asList("A", "D", "500"));
        System.out.print(sol.findMinCostDij(flights, "A", "D", 1));
        //System.out.println(sol.getPath());
    }

    public int findMinCostDij(List<List<String>> flights, String from, String to, int k) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (List<String> flight : flights) {
            map.putIfAbsent(flight.get(0), new HashMap<>());
            map.get(flight.get(0)).put(flight.get(1), Integer.parseInt(flight.get(2)));
        }
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.cost, o2.cost);
        });
        pq.offer(new Node(0, from, k + 1));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.city.equals(to)) return cur.cost;
            if (cur.stop > 0) {
                if (!map.containsKey(cur.city)) continue;
                Map<String, Integer> neighbors = map.get(cur.city);
                for (String nextCity : neighbors.keySet()) {
                    pq.offer(new Node(cur.cost + neighbors.get(nextCity), nextCity, cur.stop - 1));
                }
            }
        }
        return -1;
    }

     class Node {
        int cost;
        String city;
        int stop;

        public Node(int cost, String city, int stop) {
            this.cost = cost;
            this.city = city;
            this.stop = stop;
        }

    }

    public int findMinCost(List<List<String>> flights, String from, String to, int k) {
        this.map = new HashMap<>();
        this.costMap = new HashMap<>();
        this.stopMap = new HashMap<>();
        // Initialize Adjacency matrix
        for (List<String> flight : flights) {
            map.putIfAbsent(flight.get(0), new HashMap<>());
            map.putIfAbsent(flight.get(1), new HashMap<>());
            map.get(flight.get(0)).put(flight.get(1), Integer.parseInt(flight.get(2)));
        }
        // Initialize Cost and Stop Map for every city and  set default value to MAX infinity
        for (String city : map.keySet()) {
            costMap.put(city, Integer.MAX_VALUE);
            stopMap.put(city, Integer.MAX_VALUE);
        }
        Queue<Point> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.cost, o2.cost);
        });
        pq.offer(new Point(from, 0, 0, null));

        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            // notice we do not use a set to close node
            if (curr.id.equals(to)) {
                setPath(curr);
                return curr.cost;
            }
            if (curr.stop == k + 1) continue;

            for (Map.Entry<String, Integer> entry : map.get(curr.id).entrySet()) {
                String next = entry.getKey();
                int newCost = curr.cost + entry.getValue();
                int newStop = curr.stop + 1;

                if (newCost < costMap.get(next)) {
                    costMap.put(next, newCost);
                    pq.offer(new Point(next, newCost, newStop, curr));
                } else if (newStop < stopMap.get(next)) {
                    stopMap.put(next, newStop);
                    pq.offer(new Point(next,newCost, newStop, curr));
                }

//                if (newCost >= costMap.get(next) && newStop >= stopMap.get(next)) continue;
//                //
//                if (newCost < costMap.get(next) && newStop < stopMap.get(next)) {
//                    costMap.put(next, newCost);
//                    stopMap.put(next, newStop);
//                }
//                pq.offer(new Point(next, newCost, newStop, curr));
            }

        }
        return -1;
    }

    public List<String> getPath() {
        return this.minPath;
    }

    private void setPath(Point end) {
        List<String> list = new ArrayList<>();
        while (end != null) {
            list.add(end.id);
            end = end.from;
        }
        Collections.reverse(list);
        this.minPath = list;
    }

    class Point {
        String id;
        int cost;
        int stop;
        Point from;

        public Point(String id, int cost, int stop, Point from) {
            this.id = id;
            this.cost = cost;
            this.stop = stop;
            this.from = from;
        }
    }
}


// BFS
//    public int findMinCost(List<List<String>> flights, String start, String end, int k) {
//        // BFS + Queue
//        Map<String, Map<String, Integer>> map = new HashMap<>();
//        int res = Integer.MAX_VALUE;
//        for (List<String> flight : flights) {
//            map.putIfAbsent(flight.get(0), new HashMap<>());
//            map.putIfAbsent(flight.get(1), new HashMap<>());
//            map.get(flight.get(0)).put(flight.get(1), Integer.parseInt(flight.get(2)));
//        }
//        Queue<City> queue = new LinkedList<>();
//        queue.offer(new City(start, 0));
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                City cur = queue.poll();
//                if (cur.id.equals(end)) {
//                    res = Math.min(res, cur.cost);
//                }
//                for (Map.Entry<String, Integer> nei : map.get(cur.id).entrySet()) {
//                    int cost = cur.cost + nei.getValue();
//                    if (cost > res) continue; // prunning
//                    queue.offer(new City(nei.getKey(), cost));
//                }
//            }
//            if (k-- < 0) break;
//        }
//        System.out.println(getPath(des));
//        return res == Integer.MAX_VALUE ? -1 : res;
//    }

//class BellmanFord {
//    Map<Integer, Map<String, String>> prev;
//
//        flights.add(Arrays.asList("S", "A", "8"));
//        flights.add(Arrays.asList("A", "B", "20"));
//        flights.add(Arrays.asList("S", "C", "5"));
//        flights.add(Arrays.asList("C", "D", "10"));
//        flights.add(Arrays.asList("D", "B", "3"));
//        flights.add(Arrays.asList("B", "E", "100"));
//        flights.add(Arrays.asList("E", "F", "20"));
//
//    public int findCheapestPrice(List<List<String>> flights, String src, String dst, int k) {
//        //follow up
//        prev = new HashMap<>();
//
//        //dist from src to each vertex
//        Map<String, Integer> costs = new HashMap<>();
//        for (List<String> flight : flights) {
//            costs.putIfAbsent(flight.get(0), Integer.MAX_VALUE);
//            costs.putIfAbsent(flight.get(1), Integer.MAX_VALUE);
//        }
//        if (!costs.containsKey(src) || !costs.containsKey(dst)) {
//            return -1;
//        }
//
//        costs.put(src, 0);
//
//        //do k + 1 iterations
//        for (int i = 0; i < k + 1; i++) {
//            //follow up
//            prev.put(i, new HashMap<>());
//
//            Map<String, Integer> curr = new HashMap<>();
//            for (String key : costs.keySet()) {
//                curr.put(key, costs.get(key));
//            }
//
//            for (List<String> flight : flights) {
//                String from = flight.get(0);
//                String to = flight.get(1);
//                int cost = Integer.parseInt(flight.get(2));
//                if (costs.get(from) == Integer.MAX_VALUE) {
//                    continue;
//                }
//                if (costs.get(from) + cost < curr.get(to)) {
//                    curr.put(to, costs.get(from) + cost);
//
//                    //follow up
//                    prev.get(i).put(to, from);
//                }
//            }
//            costs = curr;
//        }
//
//        if (costs.get(dst) != Integer.MAX_VALUE) {
//            //follow up
//            printPath(src, dst, k);
//
//            return costs.get(dst);
//        }
//        return -1;
//    }
//
//    public void printPath(String src, String dst, int k) {
//        List<String> path = new ArrayList<>();
//        while (k >= 0) {
//            if (prev.get(k).containsKey(dst)) {
//                path.add(dst);
//                dst = prev.get(k).get(dst);
//            }
//            k--;
//        }
//        path.add(src);
//        Collections.reverse(path);
//        System.out.print("Path is : ");
//        for (String s : path) {
//            System.out.print(s + " -> ");
//        }
//        System.out.println("done");
//
//    }
//}
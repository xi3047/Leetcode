package round2.oa.airbnb_shengqiu;/*
Assumption
1. The input is a list of list, each list store ["A", "B", "10"] represents A to B and path weight 10
and the second input is a list of list ["A", "15"] represents the point of A is 15
given a list of end, and a start, find the max score
2. Is the end point given or not
3. Does the graph contains cycle
(negative cycle is ok, but positive cycle there is no answer)
to detect positive cycle, in the dfs, we use a set for current path, if visited && current score
greater than the score in the map=> positive cycle. We should just stop.
4. What if we can not reach any of the end, return -1

Approach:
DFS: The idea is DFS to traverse the map starting from start point, maintain the score along the path,
if we can reach one of the end, we update the global max. Also, to speed up the process, we can do some
memorization, we can keep track of the max score so far for each node, if we reach a node, but with smaller
score, we do not keep going.

Time: O(E*V)
If there is no cycle, In the worst case, every time we pass a edge, we update a score, we might need re-visited
all the node, that is O(E*V)
Space: O(h) + O(V+E) = O(V + E)
where h is the length of longest path from start to end
O(V+E) to store the path information, the rewards, and a map to keep track of max score
=================================
Follow up:
Topo + BFS: Since the graph is a DAG, we can just traverse the map with topological ordering. Compared with BFS or DFS
to traverse the map and find the max, we do not have to do duplicated computation. Once a node in-degree
become 0, that is the final score. Therefore, we can get the max score for each point, then, find the
max one in the end point
 */


import java.util.*;

public class ASki {

    private int maxScore = Integer.MIN_VALUE;
    private Map<String, Map<String, Integer>> pathMap; // graph info
    private Map<String, Integer> rewardMap;  //   graph info
    private Map<String, Integer> scoreMap;   //   dfs pruning
    private List<String> maxPath;

    public static void main(String[] args) {
        ASki sol = new ASki();
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("A", "B", "2"));
        paths.add(Arrays.asList("A", "C", "3"));
        paths.add(Arrays.asList("B", "D", "5"));
        paths.add(Arrays.asList("B", "E", "6"));
        paths.add(Arrays.asList("C", "E", "4"));
        paths.add(Arrays.asList("C", "F", "4"));
        paths.add(Arrays.asList("D", "H", "7"));
        paths.add(Arrays.asList("E", "H", "6"));
        paths.add(Arrays.asList("H", "I", "1"));
        paths.add(Arrays.asList("H", "J", "2"));
        paths.add(Arrays.asList("F", "J", "3"));
        List<List<String>> points = new ArrayList<>();
        points.add(Arrays.asList("A", "5"));
        points.add(Arrays.asList("B", "7"));
        points.add(Arrays.asList("C", "6"));
        points.add(Arrays.asList("D", "2"));
        points.add(Arrays.asList("E", "4"));
        points.add(Arrays.asList("F", "7"));
        points.add(Arrays.asList("H", "7"));
        points.add(Arrays.asList("I", "3"));
        points.add(Arrays.asList("J", "2"));
        List<String> ends = Arrays.asList("I", "J");
        System.out.println(sol.findMaxScore(paths, points, "A"));
        System.out.println(sol.maxPath);

    }

    public int findMaxScore(List<List<String>> paths, List<List<String>> rewards, String start) {
        this.pathMap = new HashMap<>();
        this.rewardMap = new HashMap<>();
        this.scoreMap = new HashMap<>();
        for (List<String> path : paths) {
            pathMap.putIfAbsent(path.get(0), new HashMap<>());
            pathMap.putIfAbsent(path.get(1), new HashMap<>());
            pathMap.get(path.get(0)).put(path.get(1), Integer.parseInt(path.get(2)));
        }
        for (List<String> point : rewards) {
            rewardMap.put(point.get(0), Integer.parseInt(point.get(1)));
        }
        Set<String> ends = new HashSet<>();
        for (String key : pathMap.keySet()) {
            scoreMap.put(key, 0);
            if (pathMap.get(key).isEmpty()) {
                ends.add(key);
            }
        }
        List<String> curPath = new ArrayList<>();
        dfs(curPath, ends, start, 2 * rewardMap.get(start));
        return maxScore == Integer.MIN_VALUE ? -1 : maxScore;
    }

    private void dfs(List<String> curPath, Set<String> ends, String curr, int score) {
        curPath.add(curr);
        if (ends.contains(curr)) {
            if (score > this.maxScore) {
                this.maxPath = new ArrayList<>(curPath);
                this.maxScore = score;
            }
            curPath.remove(curPath.size() - 1);
            return;
        }
        for (Map.Entry<String, Integer> entry : pathMap.get(curr).entrySet()) {
            String next = entry.getKey();
            int newScore = score + 2 * rewardMap.get(next) + entry.getValue();;
            if (newScore <= scoreMap.get(next)) continue;
            scoreMap.put(next, newScore);
            dfs(curPath, ends, next, newScore);
        }
        curPath.remove(curPath.size() - 1);
    }
}
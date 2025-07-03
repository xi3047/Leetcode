package round4.otherDataStructure;

import java.util.*;

public class L133_CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    // DFS
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node clone = new Node(node.val);
        map.put(node, clone);
        for (Node next : node.neighbors) {
            clone.neighbors.add(cloneGraph(next));
        }
        return clone;
    }

    // BFS
    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;
        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node next : cur.neighbors) {
                if (!visited.containsKey(next)) {
                    visited.put(next, new Node(next.val));
                    queue.offer(next);
                }
                Node curClone = visited.get(cur);
                curClone.neighbors.add(visited.get(next));
            }
        }
        return visited.get(node);
    }

    /**
     * Variant, disconnected undirected graph
     */
    class Graph {
        List<Node> roots;
        Graph(){}

    }
    public Graph cloneGraph2(Graph input) {
        Graph output = new Graph();
        for (Node node: input.roots) {
            if (node == null) continue;
            Map<Node, Node> map = new HashMap<>();
            output.roots.add(dfs(node, map));
        }
        return output;
    }
}

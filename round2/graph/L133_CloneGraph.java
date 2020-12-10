package round2.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/5/2020 10:23 PM
 * @topic round2.graph
 * @link
 * @description
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 */
public class L133_CloneGraph {
    /*
    DFS
     */
    public Node cloneGraph(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Integer, Node> map) {
        if (node == null) return null;
        if (map.containsKey(node.val)) return map.get(node.val);
        Node newNode = new Node(node.val);
        map.put(node.val, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(dfs(neighbor, map));
        }
        return newNode;
    }

    /*
    BFS
     */
    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;
        Node newNode = new Node(node.val); //new node for return
        HashMap<Integer, Node> map = new HashMap(); //store visited nodes

        map.put(newNode.val, newNode); //add first node to HashMap

        LinkedList<Node> queue = new LinkedList(); //to store **original** nodes need to be visited
        queue.add(node); //add first **original** node to queue

        while (!queue.isEmpty()) { //if more nodes need to be visited
            Node cur = queue.pop(); //search first node in the queue
            for (Node neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor.val)) { //add to map and queue if this node hasn't been searched before
                    map.put(neighbor.val, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                map.get(cur.val).neighbors.add(map.get(neighbor.val)); //add neighbor to new created nodes
            }
        }
        return newNode;
    }

}

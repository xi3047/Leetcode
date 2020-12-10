package round2.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/5/2020 10:24 PM
 * @topic round2.graph
 * @link
 * @description
 */
public class Node {
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

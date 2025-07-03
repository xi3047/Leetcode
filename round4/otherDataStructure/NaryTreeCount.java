package round4.otherDataStructure;

import java.util.ArrayList;
import java.util.List;

public class NaryTreeCount {
    class Node {
        List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }
    }
    public int countNode(Node root) {
        if (root == null) return 0;
        int count = 1;
        for (Node child: root.children) {
            count += countNode(child);
        }
        return count;
    }

}

package round1;

public class UnionFind {

    //Kruskal(O(ElogE)) + Union Find
    public class Node {
        int val;
        Node parent;
        int size;
        Node(int val) {
            this.val = val;
            this.parent = this;
            this.size = 1;
        }
    }

    public int num;
    public int setCount; // number of sets
    public int size;
    public Node[] nodes;

    public UnionFind(int num) {
        this.num = num;
        setCount = num;
        nodes = new Node[num];
        for(int i = 0; i < num; i++) {
            nodes[i] = new Node(i + 1);
        }
    }

    public void union(Node n1, Node n2) {
        Node root1 = getRoot(n1);
        Node root2 = getRoot(n2);
        if(root1.size > root2.size) {
            root2.parent = root1;
            root1.size += root2.size;
        } else {
            root1.parent = root2;
            root2.size += root1.size;
        }
        setCount--;
    }

    public boolean find(Node n1, Node n2) {
        return getRoot(n1) == getRoot(n2);
    }

    public Node getRoot(Node n) {
        Node cur = n;
        while(cur.parent != cur) {
            cur.parent = cur.parent.parent;
            cur = cur.parent;
        }
        n.parent = cur;
        return cur;
    }
}

package round2.tree;

/**
 * @author Xi Zhang
 * @date 11/19/2020 3:54 PM
 * @topic round2.tree
 * @link
 * @description
 */
public class L117_PopulatingNextRight2 {
    /**
     * Use a dummy head to store the node of
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Node dummyHead = new Node(0);
        Node prev = dummyHead;
        Node cur = root;
        while (cur != null) {
            if (cur.left != null) {
                prev.next = cur.left;
                prev = prev.next;
            }
            if (cur.right != null) {
                prev.next = cur.right;
                prev = prev.next;
            }
            if (cur.next != null) {
                cur = cur.next;
            } else {
                cur = dummyHead.next;
                dummyHead.next = null;
                prev = dummyHead;
            }
        }
        return root;
    }
}



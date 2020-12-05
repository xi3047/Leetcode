package round1;

/*
    @author: Xi Zhang
    @date:   2/2/19
    @time:   8:29 PM
 */
public class L116_PopulateNextRight {
    public void connect(TreeLinkNode root) {
        while (root != null && root.left != null) {
            TreeLinkNode cur = root;
            while (cur != null) {
                cur.left.next = cur.right;
                cur.right.next = cur.next == null? null : cur.next.left;
                cur = cur.next;
            }
            root = root.left;
        }
    }

}

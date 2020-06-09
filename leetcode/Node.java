package leetcode;

/**
 * created by Xi at 1/31/19
 **/
abstract class Node<T extends Comparable<?>> {
//    Node<T> left, right;
//    T data;

    public Node(T data) {
      //  this.data = data;
    }

    abstract T getData();

    abstract Node<T> left();
    abstract Node<T> right();
}
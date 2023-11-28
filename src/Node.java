public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
    public T data() {
        return data;
    }
    public Node<T> next() {
        return next;
    }
    void setNext(Node<T> next) {
        this.next = next;
    }
}

package reverse_linked_list;

class ReverseALinkedList {

    public static <T> void printList(Node<T> head) {
        Node<T> node = head;
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
        
    }

    public static <T> Node<T> reverseList(Node<T> head) {

        Node<T> prev = null;
        Node<T> node = head;
        // 1 -> 2 -> 3 -> null
        // node.next = 1 -> null
        // next = 2
        // prev = 1
        while(node != null) {
            Node<T> next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }

        return prev;

    }

    public static void main(String[] args) {
        Node<Integer> head1 = new Node<Integer>(1);
        head1.next = new Node<Integer>(2);
        head1.next.next = new Node<Integer>(3);
        head1.next.next.next = new Node<Integer>(4);
        head1.next.next.next.next = new Node<Integer>(5);
        head1.next.next.next.next.next = new Node<Integer>(6);

        printList(reverseList(head1));
    } 

}

class Node<T> {
    public T val;
    public Node<T> next = null;

    public Node(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "val: " + this.val;
    }
}

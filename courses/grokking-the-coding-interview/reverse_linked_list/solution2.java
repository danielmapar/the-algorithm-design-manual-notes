package reverse_linked_list;

class ReverseASubList {

    public static <T> void printList(Node<T> node) {
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    public static <T> void reverseList(Node<T> head, Node<T> p, Node<T> q) {
        printList(head);

        Node<T> beforeP = head;
        while (beforeP.next != p) {
            beforeP = beforeP.next;
        }

        // 1, 2, [3], 4, 5, 6, [7], 8, 9
        // prev = 8 
        // node = 3
        // next = 4
        Node<T> prev = q.next;
        Node<T> node = p;
        while (node != q) {
            Node<T> next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        node.next = prev;
        beforeP.next = node;

        System.out.println("--------");

        printList(head);

    }

    public static void main(String[] args){
        Node<Integer> node = new Node<Integer>(1);
        node.next = new Node<Integer>(2);
        Node<Integer> p = new Node<Integer>(3);
        node.next.next = p;
        node.next.next.next = new Node<Integer>(4);
        node.next.next.next.next = new Node<Integer>(5);
        node.next.next.next.next.next = new Node<Integer>(6);
        Node<Integer> q = new Node<Integer>(7);
        node.next.next.next.next.next.next = q;
        node.next.next.next.next.next.next.next = new Node<Integer>(8);
        node.next.next.next.next.next.next.next.next = new Node<Integer>(9);
        
        reverseList(node, p, q);
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
        return "Val: " + this.val;
    }
}
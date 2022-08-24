package reverse_linked_list;

class ReverseEveryKElementSubList {

    public static <T> void printList(Node<T> node) {
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    public static <T> void reverseKSubList(Node<T> head, int k) {

        
        Node<T> start = null;
        Node<T> node = head;
        Node<T> prev = null;
        int counter = 0;
        
        
        // 1,2,3,   4,5,6,7,8,9
        // 3 -> 2 -> 1 -> null
        // 1
        while (node != null) {
            Node<T> next = node.next;
            node.next = prev;
            prev = node;
            node = next;

            counter++;

            if (counter == k) {

                //System.out.print(node);

                counter = -1;
                if (start == null) {
                    start = head;
                    head = prev;
                    
                } else {
                    start.next = prev;
                    start = prev;
                }

                prev = null;
            }
        }

        printList(head);
    }
    public static void main(String[] args) {
        Node<Integer> node = new Node<Integer>(1);
        node.next = new Node<Integer>(2);
        node.next.next = new Node<Integer>(3);
        node.next.next.next = new Node<Integer>(4);
        node.next.next.next.next = new Node<Integer>(5);
        node.next.next.next.next.next = new Node<Integer>(6);
        node.next.next.next.next.next.next = new Node<Integer>(7);
        node.next.next.next.next.next.next.next = new Node<Integer>(8);
        node.next.next.next.next.next.next.next.next = new Node<Integer>(9);

        reverseKSubList(node, 3);
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
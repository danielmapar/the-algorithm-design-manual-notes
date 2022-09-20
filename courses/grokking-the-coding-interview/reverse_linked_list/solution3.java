package reverse_linked_list;

class ReverseEveryKElementSubList {

    public static <T> void printList(Node<T> node) {
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    public static <T> void reverseLinkedList(Node<T> head, Node<T> tail) {
        Node<T> node = head;
        Node<T> prev = null;
        while (prev != tail) {
            Node<T> next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
    }

    public static <T> Node<T> reverseKSubList(Node<T> head, int k) {
        int counter = 0;
        Node<T> node = head;
        Node<T> listHead = null;
        Node<T> currentHead = null;
        Node<T> currentTail = null;
        Node<T> newTail = null;

        // O(N*2)
        while (node.next != null) {
            if (counter == 0) currentHead = node;
            else if (counter == k-1) {
                currentTail = node;
                if (newTail != null) newTail.next = currentTail;
                
                node = currentTail.next;
                reverseLinkedList(currentHead, currentTail);   
                newTail = currentHead;
                if (listHead == null) listHead = currentTail;
                counter = 0;
                continue;
            }
            counter++;
            node = node.next;
        }

        if (counter > 0) {
            currentTail = node;
            if (newTail != null) newTail.next = currentTail;
            reverseLinkedList(currentHead, currentTail);   
        }
        return listHead;
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
        node.next.next.next.next.next.next.next.next.next = new Node<Integer>(10);
        node.next.next.next.next.next.next.next.next.next.next = new Node<Integer>(11);

        printList(reverseKSubList(node, 3));
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
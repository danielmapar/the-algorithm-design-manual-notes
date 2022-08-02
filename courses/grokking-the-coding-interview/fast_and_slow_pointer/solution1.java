package fast_and_slow_pointer;

class LinkedListCycle {

    public static boolean hasCycle(ListNode<Integer> node) {

        ListNode<Integer> slowNode = node;
        ListNode<Integer> fastNode = node.next;

        while(slowNode != null && fastNode != null) {
            if (slowNode == fastNode) return true;

            slowNode = slowNode.next;

            if (fastNode.next != null) fastNode = fastNode.next.next;
            else break;
        }

        return false;
        
    }

    public static void main(String[] args) {
        ListNode<Integer> node = new ListNode<Integer>(1);
        node.next = new ListNode<Integer>(2);
        node.next.next = new ListNode<Integer>(3);
        node.next.next.next = new ListNode<Integer>(4);
        node.next.next.next.next = node;
        System.out.println(hasCycle(node));

        ListNode<Integer> node2 = new ListNode<Integer>(1);
        node2.next = new ListNode<Integer>(2);
        node2.next.next = new ListNode<Integer>(3);
        node2.next.next.next = new ListNode<Integer>(4);
        node2.next.next.next.next = null;
        System.out.println(hasCycle(node2));
    }
}

class ListNode<T> {
    public T value;
    public ListNode<T> next = null;

    public ListNode(T val) {
        this.value = val;
    }
}
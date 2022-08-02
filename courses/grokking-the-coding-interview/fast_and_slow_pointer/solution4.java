package fast_and_slow_pointer;

class MiddleOfTheLinkedList {

    public static ListNode<Integer> getMiddleNodeFromList(ListNode<Integer> head) {
        ListNode<Integer> mid = null;

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        mid = slow;
        return mid;
    }

    public static void main(String[] args) {
        ListNode<Integer> node = new ListNode<Integer>(1);
        node.next = new ListNode<Integer>(2);
        node.next.next = new ListNode<Integer>(3);
        node.next.next.next = new ListNode<Integer>(4);
        node.next.next.next.next = new ListNode<Integer>(5);
        System.out.println(getMiddleNodeFromList(node).value);

        ListNode<Integer> node2 = new ListNode<Integer>(1);
        node2.next = new ListNode<Integer>(2);
        node2.next.next = new ListNode<Integer>(3);
        node2.next.next.next = new ListNode<Integer>(4);
        node2.next.next.next.next = new ListNode<Integer>(5);
        node2.next.next.next.next.next = new ListNode<Integer>(6);
        System.out.println(getMiddleNodeFromList(node2).value);

        ListNode<Integer> node3 = new ListNode<Integer>(1);
        node3.next = new ListNode<Integer>(2);
        node3.next.next = new ListNode<Integer>(3);
        node3.next.next.next = new ListNode<Integer>(4);
        node3.next.next.next.next = new ListNode<Integer>(5);
        node3.next.next.next.next.next = new ListNode<Integer>(6);
        node3.next.next.next.next.next.next = new ListNode<Integer>(7);
        System.out.println(getMiddleNodeFromList(node3).value);
    }
}

class ListNode<T> {
    public T value;
    public ListNode<T> next = null;
    public ListNode(T val) {
        this.value = val;
    }
}
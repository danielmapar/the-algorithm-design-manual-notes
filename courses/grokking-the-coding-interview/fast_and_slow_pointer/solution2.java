package fast_and_slow_pointer;

class StartOfLinkedListCycle {

    public static ListNode<Integer> getCycleStart(ListNode<Integer> node, int size) {
        ListNode<Integer> cycleStartNode = null;

        ListNode<Integer> slow = node;
        ListNode<Integer> fast = node.next;
        do {
            slow = slow.next;
            if (slow == null) break;

            if (fast != null && fast.next != null) fast = fast.next.next;
            else break;

            if (slow == fast) {
                ListNode<Integer> curr = slow;
                int count = 0;
                do {
                    count++;
                    curr = curr.next;
                } while(curr != slow);
                int startCycleCount = size - count;
                cycleStartNode = node;
                while (startCycleCount > 0) {
                    cycleStartNode = cycleStartNode.next;
                    startCycleCount--;
                }
                break;
            }
        } while(slow != fast);

        return cycleStartNode;
    } 

    public static void main(String[] args){
        ListNode<Integer> node = new ListNode<Integer>(1);
        node.next = new ListNode<Integer>(2);
        node.next.next = new ListNode<Integer>(3);
        node.next.next.next = new ListNode<Integer>(4);
        node.next.next.next.next = node;
        System.out.println(getCycleStart(node, 4).value);

        ListNode<Integer> node2 = new ListNode<Integer>(1);
        node2.next = new ListNode<Integer>(2);
        node2.next.next = new ListNode<Integer>(3);
        node2.next.next.next = new ListNode<Integer>(4);
        node2.next.next.next.next = new ListNode<Integer>(5);
        node2.next.next.next.next.next = null;
        System.out.println(getCycleStart(node2, 4));

        ListNode<Integer> node3 = new ListNode<Integer>(1);
        node3.next = new ListNode<Integer>(2);
        node3.next.next = new ListNode<Integer>(3);
        node3.next.next.next = new ListNode<Integer>(4);
        node3.next.next.next.next = node3.next.next.next;
        System.out.println(getCycleStart(node3, 4).value);
    }
}

class ListNode<T> {
    public T value;
    public ListNode<T> next = null;

    public ListNode(T val) {
        this.value = val;
    }
}
package fast_and_slow_pointer;

class RearrangeLinkedList {

    public static ListNode<Integer> reverseList(ListNode<Integer> head) {
        // 1 -> 2 -> 3 -> 4
        ListNode<Integer> prev = null;
        while (head != null) {
            ListNode<Integer> next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static ListNode<Integer> reArrangeList(ListNode<Integer> head) {
        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;
        int countToMid = 0;
        do {
            fast = fast.next.next;
            slow = slow.next;
            countToMid++;
        } while(fast != null && fast.next != null);

        ListNode<Integer> mid = slow;
        ListNode<Integer> firstHalf = head;
        ListNode<Integer> secondHalf = reverseList(mid);

        /*printList(firstHalf);
        System.out.println("---------");
        printList(secondHalf);
        System.out.println("---------");
        System.out.println(countToMid);
        System.out.println("---------");*/

        // 2 -> 4 -> 6 -> 8 -> 10 -> 12
        while(countToMid > 0) {
            // 4 -> 6 -> 8 -> null
            ListNode<Integer> nextFirst = firstHalf.next;
            // 2 -> null
            firstHalf.next = null;
            // 10 -> 8 -> null
            ListNode<Integer> nextSecond = secondHalf.next;
            // 12 -> null
            secondHalf.next = null;
            if (nextFirst == secondHalf) {
                firstHalf.next = nextFirst;
                break;
            }
            // 2 -> 12 -> null
            firstHalf.next = secondHalf;
            // 2 -> 12 -> 4 -> 6 -> 8 -> null
            secondHalf.next = nextFirst;
            // 4 -> 6 -> 8 -> null
            firstHalf = nextFirst;
            // 10 -> 8 -> null
            secondHalf = nextSecond;
            
            countToMid--;
        }
        return head;

    }

    public static void printList(ListNode<Integer> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> node = new ListNode<Integer>(1);
        node.next = new ListNode<Integer>(2);
        node.next.next = new ListNode<Integer>(3);
        node.next.next.next = new ListNode<Integer>(4);
        node.next.next.next.next = new ListNode<Integer>(5);
        printList(reArrangeList(node));

        System.out.println("-------");

        ListNode<Integer> node2 = new ListNode<Integer>(2);
        node2.next = new ListNode<Integer>(4);
        node2.next.next = new ListNode<Integer>(6);
        node2.next.next.next = new ListNode<Integer>(8);
        node2.next.next.next.next = new ListNode<Integer>(10);
        node2.next.next.next.next.next = new ListNode<Integer>(12);
        printList(reArrangeList(node2));

        System.out.println("-------");

        ListNode<Integer> node3 = new ListNode<Integer>(2);
        node3.next = new ListNode<Integer>(4);
        node3.next.next = new ListNode<Integer>(6);
        node3.next.next.next = new ListNode<Integer>(8);
        node3.next.next.next.next = new ListNode<Integer>(10);
        printList(reArrangeList(node3));
    }

}

class ListNode<T> {
    public T value; 
    public ListNode<T> next = null;
    public ListNode(T val) {
        this.value = val;
    }
}
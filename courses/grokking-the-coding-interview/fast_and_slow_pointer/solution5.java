package fast_and_slow_pointer;

class PalindromeLinkedList {

    public static void printLinkedList(ListNode<Integer> head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    // 3 -> 2 -> 1 -> 0 -> null
    // 3 -> null
    public static ListNode<Integer> reverseList(ListNode<Integer> head) {
        ListNode<Integer> prev = null;
        while(head != null) {
            ListNode<Integer> next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static boolean isPalindrome(ListNode<Integer> head) {

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        // 1 -> 2 -> 3 -> 2 -> 1 -> null
        // 1 -> 2 -> 3 -> 3 -> 2 -> 1 -> null
        int countToMid = 0;
        do {
            countToMid++;
            fast = fast.next.next;
            slow = slow.next;
        }
        while (fast != null && fast.next != null);

        ListNode<Integer> mid = slow;
        ListNode<Integer> reverseStart = reverseList(mid);
        
        ListNode<Integer> firstHalf = head;
        ListNode<Integer> secondHalf = reverseStart;

        while (countToMid > 0) {
            if (!firstHalf.value.equals(secondHalf.value)) break;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
            countToMid--;
        }

        reverseList(reverseStart);

        // Check if list is intact
        printLinkedList(head);

        return countToMid == 0;
    }

    public static void main(String[] args) {
        ListNode<Integer> node = new ListNode<Integer>(1);
        node.next =  new ListNode<Integer>(2);
        node.next.next =  new ListNode<Integer>(3);
        node.next.next.next =  new ListNode<Integer>(2);
        node.next.next.next.next =  new ListNode<Integer>(1);
        System.out.println(isPalindrome(node));

        ListNode<Integer> node2 = new ListNode<Integer>(1);
        node2.next =  new ListNode<Integer>(2);
        node2.next.next =  new ListNode<Integer>(3);
        node2.next.next.next =  new ListNode<Integer>(3);
        node2.next.next.next.next =  new ListNode<Integer>(2);
        node2.next.next.next.next.next =  new ListNode<Integer>(1);
        System.out.println(isPalindrome(node2));

        ListNode<Integer> node3 = new ListNode<Integer>(1);
        node3.next =  new ListNode<Integer>(2);
        node3.next.next =  new ListNode<Integer>(3);
        node3.next.next.next =  new ListNode<Integer>(3);
        node3.next.next.next.next =  new ListNode<Integer>(4);
        node3.next.next.next.next.next =  new ListNode<Integer>(2);
        node3.next.next.next.next.next.next =  new ListNode<Integer>(1);
        System.out.println(isPalindrome(node3));
    } 
}

class ListNode<T> {
    public T value;
    public ListNode<T> next = null;
    public ListNode(T val) {
        this.value = val;
    }
}

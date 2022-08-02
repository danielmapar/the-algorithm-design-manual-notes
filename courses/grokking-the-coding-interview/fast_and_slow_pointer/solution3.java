package fast_and_slow_pointer;

import java.util.*;

class HappyNumber {

    public static int squaredDigits(int num) {
        int squared = 0;
        while(num > 0) {
            int remainder = num % 10;
            num = (num - remainder) / 10;
            squared += remainder * remainder;
        }
        return squared;
    }

    public static boolean isHappyNumber(int num) {
        Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
        while (!visited.containsKey(num)) {
            visited.put(num, true);
            num = squaredDigits(num);
            if (num == 1) return true;
        }
        return false; 
    }

    public static boolean isHappyNumberV2(int num) {
        int slow = num;
        int fast = num;
        do {
            slow = squaredDigits(slow);
            fast = squaredDigits(squaredDigits(fast));
            if (slow == fast) return false;
            if (slow == 1 || fast == 1) break;
        } while(fast != slow);
        return true;
    }

    public static void main(String[] args){
        System.out.println(isHappyNumber(23));
        System.out.println(isHappyNumber(12));
        System.out.println("-------");
        System.out.println(isHappyNumberV2(23));
        System.out.println(isHappyNumberV2(12));

    }
}
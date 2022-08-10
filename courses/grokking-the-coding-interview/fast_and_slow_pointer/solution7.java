package fast_and_slow_pointer;

class CycleInACircularArray {

    public static boolean isCircular(int[] arr) {

        if (arr.length <= 1) return false;

        for (int i = 0; i < arr.length; i++) {
            int index = i;
            int num = arr[index];

            boolean isPositive = num > 0;
            boolean hasCycle = true;

            // We could use a HashMap for non-cyclic indexes.
            while (index >= 0 && index < arr.length) {
                num = arr[index];
                if (num == 0 || (num < 0 && isPositive) || (num > 0 && !isPositive)) {
                    hasCycle = false;
                    break;
                }
                index += num;
            }

            if (hasCycle) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isCircular(new int[]{1, 2, -1, 2, 2}));
        System.out.println(isCircular(new int[]{2, 2, -1, 2}));
        System.out.println(isCircular(new int[]{2, 1, -1, -2}));
    }
}

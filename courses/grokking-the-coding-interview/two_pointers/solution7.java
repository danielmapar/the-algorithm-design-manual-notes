import java.util.Arrays;

class TripletsWithSmallestNumber {

    public static int getTotalTripletsLowerThanTarget(int[] arr, int target) {
        // O(n logn)
        Arrays.sort(arr);

        // -1, 1, 2, 3, 4 -- 5
        // -1, 0, 2, 3 -- 3
        // 1, 1, 1, 1, 1, 1, 1, 1 --- 4
        // Runtime O(n^2)
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int leftPointer = i+1;
            int rightPointer = arr.length-1;
            while (leftPointer < rightPointer) {
                int sum = arr[i] + arr[leftPointer] + arr[rightPointer];
                if (sum >= target) {
                    rightPointer--;
                } else {
                    count += rightPointer - leftPointer;
                    leftPointer++;
                }
            }
        }

        return count;
    }
    public static void main(String[] args) {
        System.out.println(getTotalTripletsLowerThanTarget(new int[] {-1, 0, 2, 3}, 3));
        System.out.println(getTotalTripletsLowerThanTarget(new int[] {-1, 4, 2, 1, 3}, 5));
    }
}

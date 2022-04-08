import java.util.Arrays;

class RemoveDuplicates {
    public static int countUniqueNums(int[] arr) {
        int uniqueNums = 0;
        int previousNum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int currentNum = arr[i];
            if (currentNum != previousNum) {
                uniqueNums++;
            }
            previousNum = currentNum;
        }
        return uniqueNums;
    }

    public static void swap(int[] arr, int a, int b) {
        int swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
    }


    // 2, 3, 3, 3, 6, 9, 9
    public static int countUniqueNumsV2(int[] arr) {
        int uniqueNums = 0;
        int previousNum = 0;

        int uniqueIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            int currentNum = arr[i];
            if (currentNum != previousNum) {
                swap(arr, uniqueIndex, i);
                uniqueNums++;
                previousNum = currentNum;
                uniqueIndex++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return uniqueNums;
    }
    public static void main(String[] args) {
        System.out.println(countUniqueNums(new int[]{2, 3, 3, 3, 6, 9, 9}));
        System.out.println(countUniqueNums(new int[]{2, 2, 2, 11}));
        System.out.println("--------");
        System.out.println(countUniqueNumsV2(new int[]{2, 3, 3, 3, 6, 9, 9}));
        System.out.println(countUniqueNumsV2(new int[]{2, 2, 2, 11}));
    }
}

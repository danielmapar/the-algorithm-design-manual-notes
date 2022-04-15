class DutchNationalFlagProblem {

    public static void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    // Runtime Complexity O(n)
    // Space Complexity O(1)
    public static int[] sortArray(int[] arr) {
        int leftPointer = 0;
        int rightPointer = arr.length-1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 && i > leftPointer) {
                swap(arr, i, leftPointer);
                leftPointer++;
                i--;
            } else if (arr[i] == 2 && i < rightPointer) {
                swap(arr, i, rightPointer);
                rightPointer--;
                i--;
            }
        }
        return arr;
    }
    public static void main(String[] args){
        int[] arr = new int[]{1, 0, 2, 1, 0};
        sortArray(arr);
        for (int i : arr) {
            System.out.println(i);
        }
        System.out.println("---------------");
        int[] arr2 = new int[]{2, 2, 0, 1, 2, 0};
        sortArray(arr2);
        for (int i : arr2) {
            System.out.println(i);
        }
        System.out.println("---------------");
        int[] arr3 = new int[]{1, 2, 1, 0, 1, 2, 0, 1, 0, 2, 2, 0, 1, 2, 0};
        sortArray(arr3);
        for (int i : arr3) {
            System.out.println(i);
        }
    }

}
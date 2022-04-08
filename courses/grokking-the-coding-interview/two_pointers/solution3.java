import java.util.Arrays;

class RemoveKey {

    public static void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    // 3, 3, 2, 3, 6, 3, 10, 9, 3
    // 2, 3, 3, 3, 6, 3, 10, 9, 3
    // 2, 3, 3, 3, 6, 3, 10, 9, 3
    public static int uniqueNumbers(int[] arr, int key) {

        int uniqueIndex = 0;
        for(int i = 0; i < arr.length; i++) {
            int currentNum = arr[i];
            if (currentNum != key) {
                swap(arr, i, uniqueIndex);
                uniqueIndex++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return uniqueIndex;
    }

    public static void main(String[] args) {
        System.out.println(uniqueNumbers(new int[]{3, 2, 3, 6, 3, 10, 9, 3}, 3));
        System.out.println(uniqueNumbers(new int[]{2, 11, 2, 2, 1}, 2));
    }

}

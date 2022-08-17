package cyclic_sort;

class FindMissingNumber {

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void sortArray(int[] arr) {
        int i = 0;
        while(i < arr.length) {
            if (arr[i] != arr.length && arr[i] != i) {
                swap(arr[i], i, arr);
                continue;
            }
            i++;
        }
    }

    public static int findMissingNumber(int[] arr) {
        sortArray(arr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == arr.length) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findMissingNumber(new int[] {
            4, 0, 3, 1
        }));
        System.out.println(findMissingNumber(new int[] {
            8, 3, 5, 2, 4, 6, 0, 1
        }));
    }   
}
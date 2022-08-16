package cyclic_sort;

class CyclicSort {

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length-1) System.out.print(",");
        }
        System.out.println();
    }

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static int[] sortArray(int[] arr) {
        if (arr.length <= 1) return arr;
        
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 1;
            if (index != i) {
                swap(index, i, arr);
                i--;
            }
        }
        
        return arr;
    }    

    public static void main(String[] args) {
        printArr(sortArray(new int[]{
            3, 1, 5, 4, 2
        }));
        printArr(sortArray(new int[]{
            2, 6, 4, 3, 1, 5
        }));
        printArr(sortArray(new int[]{
            1, 5, 6, 4, 3, 2
        }));
    }

}
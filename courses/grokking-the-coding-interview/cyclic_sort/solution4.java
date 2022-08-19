package cyclic_sort;

class FindTheDuplicateNumber {

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static int findDuplicate(int[] arr) {
        if (arr.length <= 1) return -1;

        // [1, 4, 4, 3, 2]
        int i = 0;
        while (i < arr.length) {
            int num = arr[i]-1;
            if (num != i) {
                if (arr[i] == arr[num]) return arr[i];
                swap(i, num, arr);
                continue;
            }
            i++;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[] {
            1, 4, 4, 3, 2
        }));
        System.out.println(findDuplicate(new int[] {
            2, 1, 3, 3, 5, 4
        }));
        System.out.println(findDuplicate(new int[] {
            2, 4, 1, 4, 4
        }));
    }
    
}

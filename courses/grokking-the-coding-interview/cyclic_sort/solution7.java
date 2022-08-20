package cyclic_sort;

class FindTheSmallestMissingPositiveNumber {

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static int findMissing(int[] arr) {
        if (arr.length == 0) return -1;

        // -3, 1, 5, 4, 2
        int i = 0;
        while (i < arr.length) {
            int num = arr[i];
            if (arr[i] < 0 || arr[i] >= arr.length) {
                i++;
                continue;
            }
            if (i != arr[num]) {
                swap(i, num, arr);
                continue;
            }
            i++;
        }

        i = 1;
        while(i < arr.length) {
            if (arr[i] != i) return i;
            i++;
        }
        return i;
    } 

    public static void main(String[] args) {
        System.out.println(findMissing(new int[] {
            -3, 1, 5, 4, 2
        }));
        System.out.println(findMissing(new int[] {
            3, -2, 0, 1, 2
        }));
        System.out.println(findMissing(new int[] {
            3, 2, 5, 1
        }));
        System.out.println(findMissing(new int[] {
            33, 37, 5
        }));
    }
    
}

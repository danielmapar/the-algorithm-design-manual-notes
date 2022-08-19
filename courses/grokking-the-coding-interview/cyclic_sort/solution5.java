package cyclic_sort;

import java.util.*;

class FindAllDuplicateNumbers {

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static List<Integer> findDuplicates(int[] arr) {
        List<Integer> duplicates = new ArrayList<Integer>();
        if (arr.length <= 1) return duplicates;

        int i = 0;
        // [3, 5, 4, 4, 5]
        // 3 -> 2
        while(i < arr.length) {
            int num = arr[i]-1;
            if (num != i) {
                if (arr[num] != arr[i]) {
                    swap(i, num, arr);
                    continue;
                }
                else duplicates.add(arr[num]);
            }
            i++;
        }
        for (i = 0; i < arr.length; i++) 
            System.out.print(arr[i] + ",");

        return duplicates;
    }
    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[] {
            3, 5, 4, 4, 5
        }));
        System.out.println(findDuplicates(new int[] {
            5, 4, 7, 2, 3, 5, 3
        }));
    }
}

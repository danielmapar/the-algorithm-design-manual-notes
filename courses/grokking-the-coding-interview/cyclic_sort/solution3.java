package cyclic_sort;

import java.util.*;

class FindAllMissingNumbers {

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

    public static List<Integer> findMissing(int[] arr) {
        int i = 0;
        List<Integer> missing = new ArrayList<Integer>();

        while(i < arr.length) {
            int index = arr[i]-1;
            if (index != i && arr[index] != arr[i]) {
                swap(i, index, arr);
                continue;
            }
            i++;
        }
        
        for (i = 0; i < arr.length; i++) {
            if (i != arr[i]-1) missing.add(i+1);
        }
        return missing;
    }

    public static void main(String args[]) {
        System.out.println(findMissing(new int[] {
            2, 3, 1, 8, 2, 3, 5, 1
        }));
        System.out.println(findMissing(new int[] {
            2, 4, 1, 2
        }));
        System.out.println(findMissing(new int[] {
            2, 3, 2, 1
        }));
    }   
}
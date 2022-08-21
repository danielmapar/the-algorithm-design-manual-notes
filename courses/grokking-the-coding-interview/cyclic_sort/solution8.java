package cyclic_sort;

import java.util.*;

class FindTheFirstKMissingPositiveNumbersF {

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static List<Integer> findKMissingNumbers(int[] arr, int k) {
        List<Integer> missingNumbers = new ArrayList<Integer>();
        if (arr.length == 0) return missingNumbers;

        // move all negatives and greater than length to the left - O(N) 
        int i = 0;
        int negativeIndex = 0;
        Map<Integer, Boolean> greaterNumbers = new HashMap<Integer,Boolean>();
        while (i < arr.length) {
            if (arr[i] <= 0) {
                swap(i, negativeIndex, arr);
                negativeIndex++;
            }
            i++;
        }

        // [-1, 1, 2, 3, 5]
        // 5 - 1 - 1
        // 3
        // move number greater than array size to the left
        int greaterThanArrayIndex = negativeIndex; 
        int upperBoundary = arr.length-1-greaterThanArrayIndex;
        System.out.println(upperBoundary);
        System.out.println("----------");
        i = 0;
        while(i < arr.length) {
            if (arr[i] >= upperBoundary) {
                swap(i, greaterThanArrayIndex, arr);
                greaterThanArrayIndex++;
                upperBoundary--;
            }
            i++;
        }

        i = 0;
        while (i < arr.length) {
            System.out.println(arr[i]);
            i++;
        }
        System.out.println("-----------");
        System.out.println(greaterThanArrayIndex);

        // sort numbers starting from "greaterThanArrayIndex"
        
        // 2, 3, 1, 5
        // i = 5
        // greaterThanArrayIndex = 5;
        i = greaterThanArrayIndex;
        int j = 1;
        while(i < arr.length) {
            if (arr[i] != j) {
                System.out.print(arr[i]);
                swap(i, arr[i]+greaterThanArrayIndex, arr);
                continue;
            }
            j++;
            i++;
        }

        





        //i = 0;
        //System.out.println("-----------");
        //while (i < arr.length) {
        //    System.out.println(i);
        //    i++;
        //}


        /* 
        i = 0;
        int greaterNumber = arr[arr.length-1];
        System.out.println(greaterNumber);
        System.out.println("-----");
        while (i < left) {
            if (arr[i] >= arr.length && arr[i] <= (greaterNumber+k)) greaterNumbers.put(arr[i], true);
            i++;
        }
        */

        System.out.println(greaterNumbers);
        
        
        // sort from index = 3
        // 

        // hashmap with greater numbers


        return missingNumbers;
    }


    public static void main(String[] args) {
        System.out.println(findKMissingNumbers(new int[] {
            -1, 1, 2, 3, 5, 10, 20, -3, 40
        }, 8));
        //System.out.println(findKMissingNumbers(new int[] {
        //    3, -1, 4, 5, 5
        //}, 3));
    }
}
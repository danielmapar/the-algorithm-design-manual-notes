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

        // Sort non negative numbers and collect greater numbers
        // O(n) time complexity
        // O(n) space complexity (lets say all numbers are greater than array.length)
        Map<Integer, Boolean> greaterNumbers = new HashMap<Integer, Boolean>();
        int i = 0;
        int j = 1;
        while (i < arr.length) {
            if ((arr[i]) > arr.length) greaterNumbers.put(arr[i], true);
            else if (arr[i] > 0 && arr[i] != j && (arr[i]-1) < arr.length && arr[arr[i]-1] != arr[i]) {
                swap(i, arr[i]-1, arr);
                continue;
            }
            j++;
            i++;
        }

        /*i = 0;
        while (i < arr.length) {
            System.out.print(arr[i] + ", ");
            i++;
        }*/

        // Find k missing numbers in sorted array
        i = 0;
        j = 1;
        while (i < arr.length && k > 0) {
            if (arr[i] != j) {
                missingNumbers.add(j);
                k--;
            }
            j++;
            i++;
        }

        // Find k missing numbers in hashmap
        while (k > 0) {
            if (!greaterNumbers.containsKey(j)) {
                missingNumbers.add(j);
                k--;
            }
            j++;
        }

        return missingNumbers;
    }


    public static void main(String[] args) {
        System.out.println(findKMissingNumbers(new int[] {
            3, -1, 4, 5, 5
        }, 3));
        System.out.println(findKMissingNumbers(new int[] {
            2, 3, 4
        }, 3));
        System.out.println(findKMissingNumbers(new int[] {
            -2, -3, 4
        }, 2));
        System.out.println(findKMissingNumbers(new int[] {
            -1, 1, 2, 3, 5, 10, 20, -3, 12, 2
        }, 8));
        
    }
}
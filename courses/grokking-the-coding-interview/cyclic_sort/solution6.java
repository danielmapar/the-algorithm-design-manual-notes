package cyclic_sort;

import java.util.*;

class FindTheCorruptPair {

    public static void swap(int i, int j, int[] arr) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static List<Integer> findPair(int[] arr) {
        List<Integer> pair = new ArrayList<Integer>();

        if (arr.length <= 1) return pair;

        int i = 0;
        while (i < arr.length) {
            int num = arr[i]-1;
            if (i != num && arr[num] != arr[i]) {
                swap(i, num, arr);
                continue;
            }
            i++;
        }

        i = 0;
        while(i < arr.length) {
            int num = arr[i]-1;
            if (num != i) {
                pair.add(num+1);
                pair.add(i+1);
            }
            i++;
        }

        return pair;
    }

    public static void main(String[] args) {
        System.out.println(findPair(new int[] {
            3, 1, 2, 5, 2
        }));
        System.out.println(findPair(new int[] {
            3, 1, 2, 3, 6, 4
        }));
    }
}

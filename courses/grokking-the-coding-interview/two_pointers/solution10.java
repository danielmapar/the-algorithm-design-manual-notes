package two_pointers;

import java.util.*;

class QuadrupleSumToTarget {

    public static Set<List<Integer>> findQuadrupleSumToTarget(Integer[] arr, int target) {
        Set<List<Integer>> groups = new HashSet<>();

        // [2, 2, 1, 0, -1, -2]
        // 2
        Arrays.sort(arr, Collections.reverseOrder());

        for (int numIdx = 0; numIdx < arr.length - 3; numIdx++) {
            int leftIdx = numIdx + 1;
            int rightIdx = arr.length-1;
            while (leftIdx < rightIdx) {
                 
                int sum = arr[numIdx] + arr[leftIdx] + arr[rightIdx];

                boolean add = false;
                int centralIdx = rightIdx-1;
                if (sum < target) {
                    add = true;
                    centralIdx = leftIdx+1;
                }

                while(centralIdx > leftIdx && centralIdx < rightIdx) {
                    if ((sum + arr[centralIdx]) == target) {
                        groups.add(Arrays.asList(arr[numIdx], arr[leftIdx], arr[centralIdx], arr[rightIdx]));
                        break;
                    }
                    if (add) centralIdx++;
                    else centralIdx--;
                }

                if (sum >= target) leftIdx++;
                else rightIdx--;
            }
        }
        return groups;
    }

    public static void main(String[] args) {
        System.out.println(findQuadrupleSumToTarget(new Integer[]{4, 1, 2, -1, 1, -3}, 1));
        System.out.println(findQuadrupleSumToTarget(new Integer[]{2, 0, -1, 1, -2, 2}, 2));
    }
    
}

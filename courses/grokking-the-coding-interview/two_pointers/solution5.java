import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class TripletSumToZero {
    public static Map<String, int[]> findTriplet(int[] arr) {

        // Order the array (Runtime O(nlogn))
        Arrays.sort(arr);

        Map<String, int[]> result = new HashMap<>();

        // -5, 2, -1, -2, 3
        // -5, -2, -1, 2, 3

        // -3, 0, 1, 2, -1, 1, -2
        // -3, -2, -1, 0, 1, 1, 2
        // Search pairs (Runtime O(n^2))
        for(int i = 0; i < arr.length-2; i++) {
            int val = arr[i];

            int leftPointer = i+1;
            int rightPointer = arr.length-1;

            while(leftPointer < rightPointer) {
                int leftVal = arr[leftPointer];
                int rightVal = arr[rightPointer];
                int sum = val + leftVal + rightVal;

                if (sum == 0) {
                    result.put(
                        Integer.toString(val) +','+ 
                        Integer.toString(leftVal) +','+ 
                        Integer.toString(rightVal),
                        new int[]{leftVal, rightVal, rightVal}
                    );
                    leftPointer++;
                    while(leftVal == arr[leftPointer]) leftPointer++; //skip equal neighbours
                } else if (sum > 0) {
                    rightPointer--;
                    while(rightVal == arr[rightPointer]) rightPointer--; //skip equal neighbours
                } else {
                    leftPointer++;
                    while(leftVal == arr[leftPointer]) leftPointer--; //skip equal neighbours
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(findTriplet(new int[]{-3, 0, 1, 2, -1, 1, -2}));
        System.out.println(findTriplet(new int[]{-5, 2, -1, -2, 3}));
    }
}
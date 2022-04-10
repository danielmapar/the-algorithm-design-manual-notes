import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class SquaringASortedArray {

    // O(n) runtime complexity
    // O(n) space complexity
    public static int[] sortedSquareArray(int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length-1;
        int index = arr.length-1;
        int[] result = new int[arr.length];
        while(leftIdx < rightIdx) {
            int leftVal = arr[leftIdx];
            int rightVal = arr[rightIdx];
            if (leftVal < 0) {
                leftVal = Math.abs(leftVal);
            }
             
            if (rightVal > leftVal) {
                result[index] = rightVal * rightVal;
                rightIdx--;
            } else {
                result[index] = leftVal * leftVal;
                leftIdx++;
            }
            
            index--;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquareArray(new int[]{-2, -1, 0, 2, 3}))); // [0, 1, 4, 4, 9]
        System.out.println(Arrays.toString(sortedSquareArray(new int[]{-3, -1, 0, 1, 2}))); // [0, 1, 1, 4, 9]
    }

}
import java.util.Arrays;

class TripletSumCloseToTarget {

    public static int getSumClosestTriplet(int[] arr, int target) {
        // O(n logn)
        Arrays.sort(arr);

        int closestDistance = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < arr.length-2; i++) {
            int val = arr[i];
            int leftPointer = i + 1;
            int rightPointer = arr.length-1;
            
            // -2, 0, 1, 2
            // 2
            while (leftPointer < rightPointer) {
                int leftVal = arr[leftPointer];
                int rightVal = arr[rightPointer];

                int distance = (val + leftVal + rightVal) - target; // 2
                if (distance > 0) {
                    rightPointer--;
                } else {
                    leftPointer++;
                }
                if (Math.abs(distance) < closestDistance) {
                    closestDistance = Math.abs(distance);
                    sum = val + leftVal + rightVal;
                    if (closestDistance == 0) {
                        return sum;
                    }
                    //System.out.println(closestDistance);
                    //System.out.println(sum);
                    //System.out.println("--------");
                }
            }
        }

        return sum;
    }

    public static void main(String[] args){
        System.out.println(getSumClosestTriplet(new int[] {-2, 0, 1, 2}, 2));
        System.out.println(getSumClosestTriplet(new int[] {-3, -1, 1, 2}, 1));
        System.out.println(getSumClosestTriplet(new int[] {1, 0, 1, 1}, 100));
    }
}

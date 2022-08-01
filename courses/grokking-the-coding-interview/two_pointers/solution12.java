package two_pointers;

class MinimumWindowSort {

    public static int sort(int[] arr) {
        int low = 0, high = arr.length - 1;
        // find the first number out of sorting order from the beginning
        while (low < arr.length - 1 && arr[low] <= arr[low + 1])
          low++;
    
        if (low == arr.length - 1) // if the array is sorted
          return 0;
    
        // find the first number out of sorting order from the end
        while (high > 0 && arr[high] >= arr[high - 1])
          high--;
    
        // find the maximum and minimum of the subarray
        int subarrayMax = Integer.MIN_VALUE, subarrayMin = Integer.MAX_VALUE;
        for (int k = low; k <= high; k++) {
          subarrayMax = Math.max(subarrayMax, arr[k]);
          subarrayMin = Math.min(subarrayMin, arr[k]);
        }
    
        // extend the subarray to include any number which is bigger than the minimum of 
        // the subarray 
        while (low > 0 && arr[low - 1] > subarrayMin)
          low--;
        // extend the subarray to include any number which is smaller than the maximum of 
        // the subarray
        while (high < arr.length - 1 && arr[high + 1] < subarrayMax)
          high++;
    
        return high - low + 1;
      }

    // O(N)
    public static int getUnsortedSubArray(Integer[] arr) {

        int left = 0;
        int right = arr.length-1;
        int prevLeft = arr[0];
        int prevRight = arr[arr.length-1];
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        boolean breakLeft = false;
        boolean breakRight = false;

        while (left < right) {
            if (!breakLeft){
                if (arr[left] >= prevLeft && arr[left] <= arr[right]) {
                    prevLeft = arr[left];
                    left++;
                } else {
                    breakLeft = true;
                    if (left > 0) left--;
                }
            }
            
            if (!breakRight) {
                if (arr[right] <= prevRight && arr[right] >= arr[left]) {
                    prevRight = arr[right];
                    right--;
                } else {
                    breakRight = true;
                    if (right < arr.length-1) right++;
                }
            }
            

            if(breakLeft && breakRight) {
                int midLeft = left;
                int midRight = right;
                while (midLeft < midRight) {
                    minNum = Math.min(minNum, arr[midLeft]);
                    maxNum = Math.max(maxNum, arr[midLeft]);
                    midLeft++;
                }
                break;
            }
        }

        if (left < right) {
            int add = 0;
            while (left >= 0) {
                if (arr[left] > minNum) left--;
                else break;
            }
            if (left < 0) left = 0;
            else add--;
            while (right < arr.length) {
                if (arr[right] < maxNum) right++;
                else break;
            }
            if (right == arr.length) right = arr.length-1;
            else add--;
            return right - left + add + 1;
        }

        return 0;
        
    }

    public static void main(String[] args) {        
        System.out.println(getUnsortedSubArray(new Integer[]{
            1, 2, 5, 3, 7, 10, 9, 12
        }));
        System.out.println(getUnsortedSubArray(new Integer[]{
            1, 3, 2, 0, -1, 7, 10
        }));
        System.out.println(getUnsortedSubArray(new Integer[]{
            1, 2, 3
        }));
        System.out.println(getUnsortedSubArray(new Integer[]{
            3, 2, 1
        }));
        System.out.println(getUnsortedSubArray(new Integer[]{
            6, 7, 9, /**/2, 3, 4, 2, 3, -1, 0, 12, 5, /**/4, 10, 11, 11, 11, 11, 12
        }));
    }
}
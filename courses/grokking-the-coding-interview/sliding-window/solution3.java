class SmallestSubArrayWithAGreaterSum {
    public static int findMinSubArray(int[] arr, int S) {
        int windowSum = 0, minLength = Integer.MAX_VALUE;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
          windowSum += arr[windowEnd]; // add the next element
          // shrink the window as small as possible until the 'windowSum' is smaller than 'S'
          while (windowSum >= S) {
            minLength = Math.min(minLength, windowEnd - windowStart + 1);
            windowSum -= arr[windowStart]; // subtract the element going out
            windowStart++; // slide the window ahead
          }
        }
    
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
      }
    public static int getSmallestSubArraySize(int[] arr, int S) {
        int sum = 0;
        int max = Integer.MAX_VALUE;
        int firstIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= S) return 1;
            else if ((sum + arr[i]) < S) sum += arr[i];
            else {
                sum += arr[i];
                max = Math.min(i - firstIndex + 1, max);
                sum -= arr[firstIndex];
                firstIndex++;
                if (sum >= S) 
                    max = Math.min(i - firstIndex + 1, max);
            }
        }

        return max;
    }
    public static void main(String[] args) {
        System.out.println(getSmallestSubArraySize(new int[] {2, 1, 5, 2, 3, 2}, 7));
        System.out.println(getSmallestSubArraySize(new int[] {2, 1, 5, 2, 8}, 7));
        System.out.println(getSmallestSubArraySize(new int[] {3, 4, 1, 1, 6}, 8));

        System.out.println(getSmallestSubArraySize(new int[] {3, 4, 11, 1, 36, 12, 3, 4, 5, 1, 123, 23, 1}, 8) == findMinSubArray(new int[] {3, 4, 11, 1, 36, 12, 3, 4, 5, 1, 123, 23, 1}, 8));
    }
}
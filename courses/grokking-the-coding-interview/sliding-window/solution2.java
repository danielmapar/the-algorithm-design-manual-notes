class MaxSumSubArrayOfSizeK  {

    public static int findMaxSumSubArray(int k, int[] arr) {
        int maxSum = 0, windowSum;
        for (int i = 0; i <= arr.length - k; i++) {
          windowSum = 0;
          for (int j = i; j < i + k; j++) {
            windowSum += arr[j];
          }
          maxSum = Math.max(maxSum, windowSum);
        }
    
        return maxSum;
    }

    public static double findMaxSumSubArrayV2(int K, int[] arr) {
        int sum = 0;
        // O(K)
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }
        int maxSum = sum;
        
        // O(N-K)
        for (int firstIndex = 0, lastIndex = K; lastIndex < arr.length; firstIndex++, lastIndex++) {
            sum -= arr[firstIndex];
            sum += arr[lastIndex];
            maxSum = Math.max(maxSum, sum);
        }
        
        return maxSum;
    }


    public static void main(String[] args) {
        double res = findMaxSumSubArray(5, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 90, 123, 23 });
        double res2 = findMaxSumSubArrayV2(5, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 90, 123, 23 });
        System.out.println(res);
        System.out.println("--------");
        System.out.println(res2);
    }

}
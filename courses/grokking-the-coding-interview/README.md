# Grokking the Coding Interview: Patterns for Coding Questions

* This course categorizes coding interview problems into a set of 16 patterns. Each pattern will be a complete tool - consisting of data structures, algorithms, and analysis techniques - to solve a specific category of problems. The goal is to develop an understanding of the underlying pattern, so that, we can apply that pattern to solve other problems.

## Pattern: Sliding Window

### Introduction

* Given an array, find the average of all  subarrays of ‘K’ contiguous elements in it.

* `Input: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5`

* `Output: [2.2, 2.8, 2.4, 3.6, 2.8]`

* ```java
    import java.util.Arrays;

    class AverageOfSubarrayOfSizeK {
        public static double[] findAverages(int K, int[] arr) {
            double[] result = new double[arr.length - K + 1];
            for (int i = 0; i <= arr.length - K; i++) {
                // find sum of next 'K' elements
                double sum = 0;
                for (int j = i; j < i + K; j++)
                    sum += arr[j];
                result[i] = sum / K; // calculate average
            }

            return result;
        }

        public static void main(String[] args) {
            double[] result = AverageOfSubarrayOfSizeK.findAverages(5, 
                                new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
            System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
        }
    }
    ```

* Time complexity: Since for every element of the input array, we are calculating the sum of its next `K` elements, the time complexity of the above algorithm will be `O(N*K)` where `N` is the number of elements in the input array.

* The inefficiency is that for any two consecutive subarrays of size ‘5’, the overlapping part (which will contain four elements) will be evaluated twice. For example, take the above-mentioned input:

* ![sliding_window](./images/sliding_window.png)

* As you can see, there are four overlapping elements between the subarray (indexed from `0-4`) and the subarray (indexed from `1-5`). Can we somehow reuse the sum we have calculated for the overlapping elements?

* The efficient way to solve this problem would be to visualize each subarray as a sliding window of `5` elements. This means that we will slide the window by one element when we move on to the next subarray. To reuse the sum from the previous subarray, we will subtract the element going out of the window and add the element now being included in the sliding window. This will save us from going through the whole subarray to find the sum and, as a result, the algorithm complexity will reduce to `O(N)`.

* ![sliding_window2](./images/sliding_window2.png)

* ```java
    public static double[] findAveragesV2(int K, int[] arr) {
        double[] results = new double[arr.length - K + 1];

        int sum = 0;
        // O(K)
        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }
        int i = 0;
        results[i] = sum/K;

        int firstIndex = 0;
        int lastIndex = K-1;

        // O(N-K)
        while (true) {
            sum -= arr[firstIndex];
            firstIndex++;
            lastIndex++;
            if (lastIndex == arr.length) break;
            sum += arr[lastIndex];
            results[++i] = sum/K;
        }
        
        return results;
    }
    ```

* In the following chapters, we will apply the Sliding Window approach to solve a few problems.

* In some problems, the size of the sliding window is not fixed. We have to expand or shrink the window based on the problem constraints. We will see a few examples of such problems in the next chapters.

### Maximum Sum Subarray of Size K (easy)

* Given an array of positive numbers and a positive number `k`, find the maximum `sum` of any contiguous subarray of size `k`.

* ```
    Input: [2, 1, 5, 1, 3, 2], k=3 
    Output: 9
    Explanation: Subarray with maximum sum is [5, 1, 3].
    ```

* ```
    Input: [2, 3, 4, 1, 5], k=2 
    Output: 7
    Explanation: Subarray with maximum sum is [3, 4].
    ```

* A basic brute force solution will be to calculate the sum of all `k` sized subarrays of the given array to find the subarray with the highest `sum`. We can start from every index of the given array and add the next `k` elements to find the subarray’s sum. Following is the visual representation of this algorithm for Example-1:

* ![sliding_window3](./images/sliding_window3.png)

* ```java
    class MaxSumSubArrayOfSizeK {
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
        
        public static void main(String[] args) {
            System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray(3, new int[] { 2, 1, 5, 1, 3, 2 }));
            System.out.println("Maximum sum of a subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray(2, new int[] { 2, 3, 4, 1, 5 }));
        }
    }
    ```

    * The above algorithm’s time complexity will be `O(N∗K)`, where `N` is the total number of elements in the given array. Is it possible to find a better algorithm than this?

* A better approach

* If you observe closely, you will realize that to calculate the sum of a contiguous subarray, we can utilize the sum of the previous subarray. For this, consider each subarray as a **Sliding Window** of size `k`. To calculate the sum of the next subarray, we need to slide the window ahead by one element. So to slide the window forward and calculate the sum of the new position of the sliding window, we need to do two things:

    * Subtract the element going out of the sliding window, i.e., subtract the first element of the window.

    * Add the new element getting included in the sliding window, i.e., the element coming right after the end of the window.

* This approach will save us from re-calculating the sum of the overlapping part of the sliding window. Here is what our algorithm will look like:

* ```java
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
    ```

    * The time complexity of the above algorithm will be `O(N)`.

    * The algorithm runs in constant space `O(1)`.
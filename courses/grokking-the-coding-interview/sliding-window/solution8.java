class LongestSubArrayWithOnesAfterReplacement {
    public static int getLongestSubArraySize(int[] arr, int K) {
        int maxSubArraySize = 0;
        int numOfOnesFound = 0;
        for (int windowStart = 0, windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 1) numOfOnesFound++;
            if ((windowEnd - windowStart + 1 - numOfOnesFound) > K) {
                if (arr[windowStart] == 1) numOfOnesFound--;
                windowStart++;
            }
            maxSubArraySize = Math.max(windowEnd - windowStart + 1, maxSubArraySize);
        }
        return maxSubArraySize;
    }

    public static void main(String[] args) {
        System.out.println(getLongestSubArraySize(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2)); 
        System.out.println(getLongestSubArraySize(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3)); 
    }
}
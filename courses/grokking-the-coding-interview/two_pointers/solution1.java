import java.util.HashMap;

class PairWithTargetSum {
    public static int[] findPair(int[] nums, int target) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex < endIndex) {
            int sum = nums[startIndex] + nums[endIndex];
            if (sum == target) {
                return new int[]{startIndex, endIndex};
            }
            if (sum > target) {
                endIndex--;
            } else {
                startIndex++;
            }
        }
        return new int[]{};
    }
    public static int[] search(int[] arr, int targetSum) {
        HashMap<Integer, Integer> nums = new HashMap<>(); // to store numbers and indices
        for (int i = 0; i < arr.length; i++) {
            if (nums.containsKey(targetSum - arr[i]))
                return new int[] { nums.get(targetSum - arr[i]), i };
            else
                nums.put(arr[i], i); // put the number and its index in the map
        }
        return new int[] { -1, -1 }; // pair not found
    }
    
    public static void main(String[] args) {
        int[] result = findPair(new int[]{1, 2, 3, 4, 6}, 6);
        int[] result2 = findPair(new int[]{2, 5, 9, 11}, 11);
        if (result.length > 0)
            System.out.println(result[0] + " " + result[1]);
        if (result2.length > 0)
            System.out.println(result2[0] + " " + result2[1]);
    }
}
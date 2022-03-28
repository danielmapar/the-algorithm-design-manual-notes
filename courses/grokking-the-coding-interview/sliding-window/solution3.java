import java.util.ArrayList;
import java.util.List;

class getSmallestSubArrayWithAGreaterSum {

    public static Integer[] getSmallestSubArray(int[] arr, int S) {
        List<Integer> currentSum = new ArrayList<>();
        List<Integer> smallestSum = new ArrayList<>();
        Integer sum = 0;

        for (int val : arr) {
            if (val > S && currentSum.size() > 0) {
                sum -= currentSum.remove(0);
                if (sum == S && (smallestSum.size() == 0 || smallestSum.size() > currentSum.size()))
                    smallestSum = new ArrayList<>(currentSum);
                sum = 0;
                currentSum = new ArrayList<>();
            }
            else if (val == S) return new Integer[] { val };
            else if (val < S) {
                if ((val + sum) > S)
                    sum -= currentSum.remove(0);

                if (sum == S) {
                    if (smallestSum.size() == 0 || smallestSum.size() > currentSum.size()) 
                        smallestSum = new ArrayList<>(currentSum);
                    currentSum = new ArrayList<>();
                    sum = 0;
                } else if ((val + sum) <= S) {
                    currentSum.add(val);
                    sum += val;
                    if (sum == S) {
                        if (smallestSum.size() == 0 || smallestSum.size() > currentSum.size()) 
                            smallestSum = new ArrayList<>(currentSum);
                        currentSum = new ArrayList<>();
                        sum = 0;
                    } 
                } else {
                    currentSum = new ArrayList<>();
                    currentSum.add(val);
                    sum = val;
                }
            }
        }
        return smallestSum.toArray(new Integer[smallestSum.size()]);
    }

    public static void main(String[] args) {
        Integer[] result = getSmallestSubArray(new int[] { 5, -1, 2, 4, 5, 5 }, 3);
        for (Integer val : result) {
            System.out.println(val);
        }
    }
}
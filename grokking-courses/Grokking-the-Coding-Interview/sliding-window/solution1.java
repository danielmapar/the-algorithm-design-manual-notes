
class AverageOfSubArrayOfSizeK {

    public static double[] findAverages(int K, int[] arr) {
        double[] results = new double[arr.length - K + 1];

        for (int i = 0; i < (arr.length - K + 1); i++) {
            int sum = 0;
            for (int j = i; j < (i + K); j++) {
                sum += arr[j];
            }
            results[i] = sum / K;
        }

        return results;
    }


    public static void main(String[] args) {
        double[] res = findAverages(5, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        for (double val : res) {
            System.out.println(val);
        }
    }

}


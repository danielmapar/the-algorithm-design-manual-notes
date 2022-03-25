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
            if (lastIndex >= arr.length) break;
            sum += arr[lastIndex];
            results[++i] = sum/K;
        }
        
        return results;
    }


    public static void main(String[] args) {
        double[] res = findAverages(5, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        double[] res2 = findAveragesV2(5, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        for (double val : res) {
            System.out.println(val);
        }
        System.out.println("-----------");
        for (double val : res2) {
            System.out.println(val);
        }
    }

}
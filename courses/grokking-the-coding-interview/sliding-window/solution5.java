import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class FruitsIntoBaskets {
    public static int findLength(char[] arr) {
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> fruitFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            fruitFrequencyMap.put(arr[windowEnd],
                                    fruitFrequencyMap.getOrDefault(arr[windowEnd], 0) + 1);
            // shrink the sliding window, until we're left with '2' fruits in the frequency map
            while (fruitFrequencyMap.size() > 2) {
                fruitFrequencyMap.put(arr[windowStart],
                                    fruitFrequencyMap.get(arr[windowStart]) - 1);
                if (fruitFrequencyMap.get(arr[windowStart]) == 0) {
                    fruitFrequencyMap.remove(arr[windowStart]);
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static int maxNumOfFruitsInBothBaskets(char[] arr) {
        // Space Complexity: O(3)
        Map<Character, Integer> collectedFruits = new HashMap<>();

        int maxNumberOfFruits = 0;
        Character lastSeenTree = null;

        // Run time complexity: O(N)
        for (int windowStart = 0, windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            char currentFruitTree = arr[windowEnd];
            if (lastSeenTree == null || lastSeenTree != currentFruitTree) collectedFruits.put(currentFruitTree, windowEnd);
            if (collectedFruits.size() > 2) {
                // Find greatest tree
                // O(3)
                char lastFruitTree = collectedFruits.entrySet().stream()
                .filter((entry) -> entry.getKey() != currentFruitTree) // Ignore current tree
                .max(
                    (entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1
                ).get().getKey();

                // Remove invalid fruit tree
                // O(3)
                collectedFruits = collectedFruits.entrySet().stream().filter((entry) -> {
                    return (entry.getKey() == currentFruitTree || entry.getKey() == lastFruitTree);
                }).collect(Collectors.toMap(
                    e -> e.getKey(),
                    e -> e.getValue()
                ));

                windowStart = collectedFruits.get(lastFruitTree);
            }
            maxNumberOfFruits = Math.max(maxNumberOfFruits, windowEnd - windowStart + 1);
            lastSeenTree = currentFruitTree;
        }

        return maxNumberOfFruits;
    }

    public static void main(String[] args) {
        System.out.println(maxNumOfFruitsInBothBaskets(new char[] {'A', 'B', 'C', 'A', 'C'}));
        System.out.println(maxNumOfFruitsInBothBaskets(new char[] {'A', 'B', 'C', 'B', 'B', 'C'}));
        System.out.println(maxNumOfFruitsInBothBaskets(new char[] {'A', 'B', 'A', 'B', 'C', 'C', 'C', 'C', 'C'}));
        System.out.println(maxNumOfFruitsInBothBaskets(new char[] {'A', 'B', 'B', 'B', 'C', 'C', 'C', 'C', 'C'}));
        System.out.println(maxNumOfFruitsInBothBaskets(new char[] {'B', 'C', 'B', 'A', 'B', 'A', 'A', 'B', 'D'}));
        System.out.println("------------");
        System.out.println(findLength(new char[] {'A', 'B', 'C', 'A', 'C'}));
        System.out.println(findLength(new char[] {'A', 'B', 'C', 'B', 'B', 'C'}));
        System.out.println(findLength(new char[] {'A', 'B', 'A', 'B', 'C', 'C', 'C', 'C', 'C'}));
        System.out.println(findLength(new char[] {'A', 'B', 'B', 'B', 'C', 'C', 'C', 'C', 'C'}));
        System.out.println(findLength(new char[] {'B', 'C', 'B', 'A', 'B', 'A', 'A', 'B', 'D'}));

    }
}

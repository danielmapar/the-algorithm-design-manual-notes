import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithSameLettersAfterReplacement {

    // aabccbb

    // a = 2
    // (1 - 0 + 1 - 2) > 2
    public static int findLength(String str, int k) {
        int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0;
        Map<Character, Integer> letterFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
          char rightChar = str.charAt(windowEnd);
          letterFrequencyMap.put(rightChar, letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
          maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap.get(rightChar));

          // current window size is from windowStart to windowEnd, overall we have a letter
          // which is repeating 'maxRepeatLetterCount' times, this means we can have a window
          //  which has one letter repeating 'maxRepeatLetterCount' times and the remaining
          // letters we should replace. If the remaining letters are more than 'k', it is the
          // time to shrink the window as we are not allowed to replace more than 'k' letters
          if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
            char leftChar = str.charAt(windowStart);
            letterFrequencyMap.put(leftChar, letterFrequencyMap.get(leftChar) - 1);
            windowStart++;
          }

          maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static int getLongestSubstringAfterReplacement(String str, int K) {

        // aabccbb
        // O(N^2)
        int maxSubstringSize = 0;
        Character previousChar = null;
        for (int windowStart = 0, windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char character = str.charAt(windowEnd);
            if (previousChar == null || character == previousChar) {
                maxSubstringSize = Math.max(maxSubstringSize, windowEnd - windowStart + 1);
            } else {
                int count = 0;
                int index = 0;
                while((windowEnd+index) < str.length()) {
                    char nextKChar = str.charAt(windowEnd+index);
                    if (nextKChar != previousChar) {
                        count++;
                        if (count > K) {
                            break;
                        }
                    }
                    index++;
                }
                maxSubstringSize = Math.max(maxSubstringSize, (windowEnd+index) - windowStart);
                windowStart = windowEnd;
            }
            previousChar = character;
        }

        return maxSubstringSize;
    }
    public static void main(String[] args) {
        System.out.println(getLongestSubstringAfterReplacement("aabccbb", 2)); // 5
        System.out.println(getLongestSubstringAfterReplacement("abbcb", 1)); // 4
        System.out.println(getLongestSubstringAfterReplacement("abccde", 1)); // 3
        System.out.println("-------");
        System.out.println(findLength("aabccbb", 2)); // 5
        System.out.println(findLength("abbcb", 1)); // 4
        System.out.println(findLength("abccde", 1)); // 3
    }
}

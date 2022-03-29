import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithKDistinctCharacters {
    public static int getSizeOfLongestSubstring(String str, int K) {
        Map<Character, Integer> uniqueChars = new HashMap<>();

        int windowStart = 0;
        int maxSubstringSize = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            Character c = str.charAt(windowEnd);

            uniqueChars.put(c, uniqueChars.getOrDefault(c, 0) + 1);

            Character windowStartChar = str.charAt(windowStart);
            while (uniqueChars.size() > K && uniqueChars.get(windowStartChar) > 0) {
                uniqueChars.put(windowStartChar, uniqueChars.get(windowStartChar) - 1);
                if (uniqueChars.get(windowStartChar) == 0) uniqueChars.remove(windowStartChar);
                windowStartChar = str.charAt(++windowStart);
            }
            maxSubstringSize = Math.max(maxSubstringSize, windowEnd - windowStart + 1);
        }
        return maxSubstringSize;
    }

    public static int findLength(String str, int k) {
        if (str == null || str.length() == 0 || str.length() < k)
          throw new IllegalArgumentException();

        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // in the following loop we'll try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
          char rightChar = str.charAt(windowEnd);
          charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
          // shrink the sliding window, until we are left with 'k' distinct characters in
          // the frequency map
          while (charFrequencyMap.size() > k) {
            char leftChar = str.charAt(windowStart);
            charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
            if (charFrequencyMap.get(leftChar) == 0) {
              charFrequencyMap.remove(leftChar);
            }
            windowStart++; // shrink the window
          }
          // remember the maximum length so far
          maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
      }
    public static void main(String[] args) {
        System.out.println(getSizeOfLongestSubstring("araaci", 2));
        System.out.println(getSizeOfLongestSubstring("araaci", 1));
        System.out.println(getSizeOfLongestSubstring("cbbebi", 3));
        System.out.println(getSizeOfLongestSubstring("cbbebicccccccccccc", 3));
        System.out.println("-----------");
        System.out.println(findLength("araaci", 2));
        System.out.println(findLength("araaci", 1));
        System.out.println(findLength("cbbebi", 3));
        System.out.println(findLength("cbbebicccccccccccc", 3));
    }
}

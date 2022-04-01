import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class LongestSubstringWithDistinctCharacters {

    public static int findLength(String str) {
        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            // if the map already contains the 'rightChar', shrink the window from the
            // beginning so that we have only one occurrence of 'rightChar'
            if (charIndexMap.containsKey(rightChar)) {
                // this is tricky; in the current window, we will not have any 'rightChar' after
                // its previous index and if 'windowStart' is already ahead of the last index of
                // 'rightChar', we'll keep 'windowStart'
                windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
            }
            charIndexMap.put(rightChar, windowEnd); // insert the 'rightChar' into the map
            // remember the maximum length so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static int findLengthLongestSubstring(String str) {
        // Space complexity: O(n)
        Map<Character, Integer> uniqueChars = new HashMap<>();
        int maxSubstringSize = 0;

        // Total runtime complexity O(n+n) -> simplified to O(n)
        // O(n)
        for (int windowStart = 0, windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char character = str.charAt(windowEnd);
            if (uniqueChars.containsKey(character)) {
                int repeatedCharIndex = uniqueChars.get(character);
                windowStart = repeatedCharIndex + 1;
                // Check Grooking solution for an interesting trick to ignore previous invalid map values
                // Remove previous characters starting from the repeated character
                // O(n-1)
                uniqueChars = uniqueChars.entrySet().stream()
                        .filter((entry) -> entry.getValue() > repeatedCharIndex)
                        .collect(Collectors.toMap(
                            e -> e.getKey(),
                            e -> e.getValue()
                        ));
            }
            else maxSubstringSize = Math.max(maxSubstringSize, windowEnd - windowStart + 1);
            uniqueChars.put(character, windowEnd);
        }

        return maxSubstringSize;
    }
    /*
    a: 0
    */
    public static void main(String[] args) {
        System.out.println(findLengthLongestSubstring("aabccbb")); // 3
        System.out.println(findLengthLongestSubstring("abbbb")); // 2
        System.out.println(findLengthLongestSubstring("abccde")); // 3
        System.out.println(findLengthLongestSubstring("aaaaaaabbbbabcdefghccccccc")); // 8
        System.out.println(findLengthLongestSubstring("aaaaaaabbbbcccccccabcdefgh")); // 8
        System.out.println(findLengthLongestSubstring("abcdefghaaaaaaabbbbccccccc")); // 8
        System.out.println(findLengthLongestSubstring("aaaaabcdeaaaaabbjjabcdefgaaa")); // 8
        System.out.println("--------");
        System.out.println(findLength("aabccbb")); // 3
        System.out.println(findLength("abbbb")); // 2
        System.out.println(findLength("abccde")); // 3
        System.out.println(findLength("aaaaaaabbbbabcdefghccccccc")); // 8
        System.out.println(findLength("aaaaaaabbbbcccccccabcdefgh")); // 8
        System.out.println(findLength("abcdefghaaaaaaabbbbccccccc")); // 8
        System.out.println(findLength("aaaaabcdeaabaaaajjabcbcdefgaaa")); // 8
    }
}

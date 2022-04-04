import java.util.*;

class StringAnagrams {
    // Runtime is O(N+M) and space complexity is O(M)
    // M = pattern length
    // N = str length
    public static List<Integer> getAnagrams(String str, String pattern) {

        List<Integer> anagrams = new ArrayList<>();
        Map<Character, Integer> patternChars = new HashMap<>();

        for (char c : pattern.toCharArray()) {
            patternChars.put(c, patternChars.getOrDefault(c, 0) + 1);
        }

        int matchingChars = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char c = str.charAt(windowEnd);

            if (patternChars.containsKey(c)) {
                patternChars.put(c, patternChars.get(c) - 1);
                if (patternChars.get(c) == 0) {
                    matchingChars++;
                }
            }

            if (matchingChars == patternChars.size()) {
                anagrams.add(windowEnd+1-pattern.length());
            }

            if (pattern.length() == (windowEnd-windowStart+1)) {
                char windowStartChar = str.charAt(windowStart);
                if (patternChars.containsKey(windowStartChar)) {
                    if (patternChars.get(windowStartChar) == 0) {
                        matchingChars--;
                    }
                    patternChars.put(windowStartChar, patternChars.get(windowStartChar) + 1);
                }
                windowStart++;
            }

        }

        return anagrams;
    }

    public static List<Integer> findStringAnagrams(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
          charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        List<Integer> resultIndices = new ArrayList<Integer>();
        // our goal is to match all the characters from the map with the current window
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
          char rightChar = str.charAt(windowEnd);
          // decrement the frequency of the matched character
          if (charFrequencyMap.containsKey(rightChar)) {
            charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
            if (charFrequencyMap.get(rightChar) == 0)
              matched++;
          }

          if (matched == charFrequencyMap.size()) // have we found an anagram?
            resultIndices.add(windowStart);

          if (windowEnd >= pattern.length() - 1) { // shrink the window
            char leftChar = str.charAt(windowStart++);
            if (charFrequencyMap.containsKey(leftChar)) {
              if (charFrequencyMap.get(leftChar) == 0)
                matched--; // before putting the character back, decrement the matched count
              // put the character back
              charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
            }
          }
        }

        return resultIndices;
      }

    public static void main(String[] args) {
        System.out.println(getAnagrams("ppqp", "pq"));
        System.out.println(getAnagrams("abbcabc", "abc"));
        System.out.println(getAnagrams("abca", "abc"));
        System.out.println(getAnagrams("abc", "abc"));
        System.out.println("--------------");
        System.out.println(findStringAnagrams("ppqp", "pq"));
        System.out.println(findStringAnagrams("abbcabc", "abc"));
        System.out.println(findStringAnagrams("abca", "abc"));
        System.out.println(findStringAnagrams("abc", "abc"));
    }
}

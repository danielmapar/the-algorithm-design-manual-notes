import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PermutationInString {

    // Runtime: O(n), Space complexity: O(n)
    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
          charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
    
        // our goal is to match all the characters from the 'charFrequencyMap' with the 
        // current window try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
          char rightChar = str.charAt(windowEnd);
          if (charFrequencyMap.containsKey(rightChar)) {
            // decrement the frequency of the matched character
            charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
            if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
              matched++;
          }
    
          if (matched == charFrequencyMap.size())
            return true;
    
          if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
            char leftChar = str.charAt(windowStart++);
            if (charFrequencyMap.containsKey(leftChar)) {
              if (charFrequencyMap.get(leftChar) == 0)
                matched--; // before putting the character back, decrement the matched count
              // put the character back for matching
              charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
            }
          }
        }
        return false;
    }

    // Runtime: O(n), Space complexity: O(n)
    public static Boolean isPatternInStringV2(String str, String pattern) {
        Map<Character, Integer> patternChars = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            patternChars.put(c, patternChars.getOrDefault(c, 0) + 1);
        }

        // aaaaabaaaaaaaaaaaa
        // aababb
        int windowStart = 0;
        int matchCharsCount = 0;
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char c = str.charAt(windowEnd);

            if (patternChars.containsKey(c)) {
                patternChars.put(c, patternChars.get(c) - 1);
                matchCharsCount++;
            }

            if (matchCharsCount == pattern.length()) return true;

            if (pattern.length() == (windowEnd-windowStart+1)) {
                char startChar = str.charAt(windowStart);
                if (patternChars.containsKey(startChar)) {
                    patternChars.put(startChar, patternChars.get(startChar) + 1);
                    matchCharsCount--;
                }
                windowStart++;
            }

        }
        return false;
    }

    // Runtime: O(n*2), Space complexity: O(n*2)
    public static Boolean isPatternInString(String str, String pattern) {
        Map<Character , Integer> patternChars = new HashMap<>();
        List<Character> foundPatternLetters = new ArrayList<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            patternChars.put(pattern.charAt(i), patternChars.getOrDefault(pattern.charAt(i), 0) + 1);
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (patternChars.containsKey(c)) {
                foundPatternLetters.add(c);
                patternChars.put(c, patternChars.get(c)-1);
                if (patternChars.get(c) == 0) patternChars.remove(c);
                if (patternChars.size() == 0) return true;
            } else if (foundPatternLetters.size() > 0) {
                for (Character foundChar : foundPatternLetters) {
                    patternChars.put(foundChar, patternChars.getOrDefault(foundChar, 0) + 1);
                }
                foundPatternLetters = new ArrayList<>();
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(isPatternInString("oidbcaf", "abc"));
        System.out.println(isPatternInString("odicf", "dc"));
        System.out.println(isPatternInString("bcdxabcdy", "bcdyabcdx"));
        System.out.println(isPatternInString("aaacb", "abc"));
        System.out.println("-------------");
        System.out.println(isPatternInStringV2("oidbcaf", "abc"));
        System.out.println(isPatternInStringV2("odicf", "dc"));
        System.out.println(isPatternInStringV2("bcdxabcdy", "bcdyabcdx"));
        System.out.println(isPatternInStringV2("aaacb", "abc"));
        System.out.println("-------------");
        System.out.println(findPermutation("oidbcaf", "abc"));
        System.out.println(findPermutation("odicf", "dc"));
        System.out.println(findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println(findPermutation("aaacb", "abc"));
        System.out.println("-------------");
        System.out.println(isPatternInStringV2("aaaaabaaaaaaaaaaaacccaaaaaaaaavaaaaaaasdas", "aaaaaaaaaaaaaaaaa"));
    }
}
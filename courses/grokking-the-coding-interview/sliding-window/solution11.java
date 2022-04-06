import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class WordsConcatenation {

    public static String convertStringArrayToString(String[] strArr) {
		StringBuilder sb = new StringBuilder();
		for (String str : strArr)
			sb.append(str);
		return sb.toString();
	}

    public static String[] swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    // O(n!)
    public static void permutate(Map<String, Boolean> wordMap, String[] arr, int k) {
        // "cat", "fox", "dog"
        for(int i = k; i < arr.length; i++) {
            swap(arr, i, k);
            permutate(wordMap, arr, k+1);
            swap(arr, k, i);
        }
        if (k == arr.length-1){
            wordMap.put(convertStringArrayToString(arr), true);
        }
    }

    public static List<Integer> getListOfOverlappingWords(String str, String[] words) {

        Map<String, Boolean> wordMap = new HashMap<>();
        permutate(wordMap, words, 0);

        List<Integer> indices = new ArrayList<>();

        int windowStart = 0;
        for (int windowEnd = convertStringArrayToString(words).length()-1; windowEnd < str.length(); windowEnd++) {
            String subString = str.substring(windowStart, windowEnd+1);
            if (wordMap.containsKey(subString)) {
                indices.add(windowStart);
            }
            windowStart++;
        }
        
        return indices;
    }

    /**
     * Given a string and a list of words, find all the starting indices of 
     * substrings in the given string that are a concatenation of all the given 
     * words exactly once without any overlapping of words. It is given that all words 
     * are of the same length.
     */
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words)
          wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
    
        List<Integer> resultIndices = new ArrayList<Integer>();
        int wordsCount = words.length, wordLength = words[0].length();
        
        // It is given that all words are of the same length.
        for (int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
          Map<String, Integer> wordsSeen = new HashMap<>();
          for (int j = 0; j < wordsCount; j++) {
            int nextWordIndex = i + j * wordLength;
            // get the next word from the string
            String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
            if (!wordFrequencyMap.containsKey(word)) // break if we don't need this word
              break;
    
            // add the word to the 'wordsSeen' map
            wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1); 
    
            // no need to process further if the word has higher frequency than required 
            if (wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word, 0))
              break;
    
            if (j + 1 == wordsCount) // store index if we have found all the words
              resultIndices.add(i);
          }
        }
    
        return resultIndices;
    }

    public static void main(String[] args) {
        System.out.println(getListOfOverlappingWords("catfoxcat", new String[]{"cat", "fox"}));
        System.out.println(getListOfOverlappingWords("catcatfoxfox", new String[]{"cat", "fox"}));
        System.out.println("-----------------");
        System.out.println(findWordConcatenation("catfoxcat", new String[]{"cat", "fox"}));
        System.out.println(findWordConcatenation("catcatfoxfox", new String[]{"cat", "fox"}));
    }

}
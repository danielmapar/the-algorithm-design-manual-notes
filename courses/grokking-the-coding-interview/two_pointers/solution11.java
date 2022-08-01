package two_pointers;

class ComparingStringsContainingBackspaces {


    public static String removeBackspace(String str) {

        // x#wrr##mu#p
        // wmp
        int removeLetter = 0;
        StringBuilder sb = new StringBuilder();
        for(int letterIdx = str.length()-1; letterIdx > 0; letterIdx--) {
            char c = str.charAt(letterIdx);
            if (c == '#') removeLetter++;
            else if (removeLetter == 0) sb.append(c);
            else removeLetter--;
        }

        return sb.toString();

    }

    public static boolean isBackspaceStringEqual(String str1, String str2) {

        // xy#z
        // xzz#
        int letter1Idx = str1.length()-1;
        int letter2Idx = str2.length()-1;
        int removeLetterStr1 = 0;
        int removeLetterStr2 = 0;
        Character lastSeenCharStr1 = null, lastSeenCharStr2 = null;
        while(letter1Idx >= 0 && letter2Idx >= 0) {
            
            char c1 = str1.charAt(letter1Idx);
            if (c1 == '#') removeLetterStr1++;
            else if (removeLetterStr1 == 0) lastSeenCharStr1 = c1;
            else removeLetterStr1--;
            
            char c2 = str2.charAt(letter2Idx);
            if (c2 == '#') removeLetterStr2++;
            else if (removeLetterStr2 == 0) lastSeenCharStr2 = c2;
            else removeLetterStr2--;

            if (lastSeenCharStr1 != null && lastSeenCharStr2 != null) {
                if (lastSeenCharStr1 != lastSeenCharStr2) return false;
                lastSeenCharStr1 = null;
                lastSeenCharStr2 = null;
            } 

            if (lastSeenCharStr1 == null) letter1Idx--;
            if (lastSeenCharStr2 == null) letter2Idx--;
        }

        return true;

    }
    public static boolean isStringEnqual(String str1, String str2){
        // O(N) time complexity
        // return removeBackspace(str1).equals(removeBackspace(str2));
        return isBackspaceStringEqual(str1, str2);
    }


    public static void main(String[] args) {
        System.out.println(isStringEnqual("xy#z", "xzz#"));
        System.out.println(isStringEnqual("xy#z", "xyz#"));
        System.out.println(isStringEnqual("xp#", "xyz##"));
        System.out.println(isStringEnqual("xywrrmp", "xywrrmu#p"));
    }
}
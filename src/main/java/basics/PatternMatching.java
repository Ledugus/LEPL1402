package basics;

public class PatternMatching {

    /**
     * Look for one sequence of characters (the pattern) in an input
     * string, and return the position of the pattern in the string
     * (if present). If the pattern is present multiple times in the
     * string, the function must return the leftmost occurrence of the
     * pattern (i.e. the occurrence whose index is the lowest). The
     * function must be case-sensitive (i.e. <code>Hello</code> is not
     * the same as <code>hello</code>).
     * @param pattern The pattern to look for.
     * @param value The string to look in.
     * @result The index of the leftmost occurrence of the pattern in
     * the string. Must be <code>-1</code> if the pattern is absent
     * from the string.
     **/

    // DUPLICATION DE L'EXO indexOf de StringUtils.java
    public static int find(String sub, String str){
        for (int i =0; i < str.length()-sub.length() + 1; i++) {
            boolean correct = true;
            for (int j = 0; j < sub.length(); j++) {
                if (sub.charAt(j) != str.charAt(i + j)) {
                    correct = false;
                    break;
                }
            }
            if (correct) {return i;}
        }
        return -1;
    }

}

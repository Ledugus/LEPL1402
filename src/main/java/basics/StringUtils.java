package basics;

public class StringUtils {


    /**
     * Split a string according to a delimiter
     *
     * @param str The string to split
     * @param delimiter The delimiter
     * @return An array containing the substring which fall
     *          between two consecutive occurence of the delimiter.
     *          If there is no occurence of the delimiter, it should
     *          return an array of size 1 with the string at element 0
     */
    public static String [] split(String str, char delimiter) {
        int cnt = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == delimiter) cnt++;
        }
        String[] l = new String[cnt];

        int current = 0;
        int j = 0;
        String subs = "";
        while (current < str.length()) {
            if (str.charAt(current) == delimiter) {
                l[j] = subs;
                subs = "";
                j++;
            } else {
                subs += str.charAt(current);
            }
            current++;
        }
        l[j] = subs;
        return l;
    }


    /**
     * Find the first occurence of a substring in a string
     *
     * @param str The string to look in
     * @param sub The string to look for
     * @return The index of the start of the first appearance of
     *          the substring in str or -1 if sub does not appear
     *          in str
     */

    // EXERCICES DUPLIQUE DANS PATTERNMATCHING.java
    public static int indexOf(String str, String sub){
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


    /**
     * Convert a string to lowercase
     *
     * @param str The string to convert
     * @return A new string, same as str but with every
     *          character put to lower case.
     */
    public static String toLowerCase(String str){
        return str.toLowerCase(); // Un peu de la triche mais c'est le meilleur moyen
    }


    /**
     * Check if a string is a palyndrome
     *
     * A palyndrome is a sequence of character that is the
     * same when read from left to right and from right to
     * left.
     *
     * @param str The string to check
     * @return true if str is a palyndrome, false otherwise
     */
    public static boolean palindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }


}

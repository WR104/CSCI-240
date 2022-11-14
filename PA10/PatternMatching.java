package PA10;

import java.util.HashMap;
import java.util.Map;

public class PatternMatching {

    int comparisons=0;
    public PatternMatching(){}

    public int findBrute(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        for (int i=0; i <= n - m; i++) {                 // try every starting index within text
            int k = 0;                                     // k is index into pattern
            while (k < m && text[i+k] == pattern[k])       // kth character of pattern matches
                k++;
            comparisons += k + 1;
            if (k == m)                                    // if we reach the end of the pattern,
                return i;                                    // substring text[i..i+m-1] is a match
        }
        return -1;                                       // search failed
    }

    /** Returns the lowest index at which substring pattern begins in text (or else -1).*/
    public int findBoyerMoore(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0;                            // trivial search for empty string
        Map<Character,Integer> last = new HashMap<>();   // the 'last' map
        for (int i=0; i < n; i++)
            last.put(text[i], -1);               // set -1 as default for all text characters
        for (int k=0; k < m; k++)
            last.put(pattern[k], k);             // rightmost occurrence in pattern is last
        // start with the end of the pattern aligned at index m-1 of the text
        int i = m-1;                                     // an index into the text
        int k = m-1;                                     // an index into the pattern
        while (i < n) {
            if (text[i] == pattern[k]) {                   // a matching character
                if (k == 0) return i;                        // entire pattern has been found
                i--;                                         // otherwise, examine previous
                k--;                                         // characters of text/pattern
            } else {
                i += m - Math.min(k, 1 + last.get(text[i])); // case analysis for jump step
                k = m - 1;                                   // restart at end of pattern
            }
            comparisons += 1;
        }
        return -1;                                       // pattern was never found
    }

    /** Returns the lowest index at which substring pattern begins in text (or else -1).*/
    public int findKMP(char[] text, char[] pattern) {
        int n = text.length;
        int m = pattern.length;
        if (m == 0) return 0;                            // trivial search for empty string
        int[] fail = computeFailKMP(pattern);            // computed by private utility
        int j = 0;                                       // index into text
        int k = 0;                                       // index into pattern
        while (j < n) {
            if (text[j] == pattern[k]) {                   // pattern[0..k] matched thus far
                if (k == m - 1) return j - m + 1;            // match is complete
                j++;                                         // otherwise, try to extend match
                k++;
            } else if (k > 0)
                k = fail[k-1];                               // reuse suffix of P[0..k-1]
            else
                j++;

            comparisons += 1;
        }
        return -1;                                       // reached end without match
    }

    private int[] computeFailKMP(char[] pattern) {
        int m = pattern.length;
        int[] fail = new int[m];                         // by default, all overlaps are zero
        int j = 1;
        int k = 0;
        while (j < m) {                                  // compute fail[j] during this pass, if nonzero
            if (pattern[j] == pattern[k]) {                // k + 1 characters match thus far
                fail[j] = k + 1;
                j++;
                k++;
            } else if (k > 0)                              // k follows a matching prefix
                k = fail[k-1];
            else                                           // no match found starting at j
                j++;
        }
        return fail;
    }
    public int getComparisons(){
        int temp = comparisons;
        comparisons = 0;
        return temp;
    }
    public static void output(char[] text, char[] pattern, String method){
        int index = 0;
        PatternMatching pm = new PatternMatching();
        String T = String.copyValueOf(text);
        String P = String.copyValueOf(pattern);
        if(method.equals("BF")){
            index = pm.findBrute(text,pattern);
            System.out.println("Using Brute Force matching to find \"" + P + "\" in \"" + T + "\"");
            if(index > -1)
                System.out.println("The index is: " + index);
            else
                System.out.println("The pattern is not found");
            System.out.println("The number of comparisons is: " + pm.getComparisons());
        }
        else if(method.equals("BM")){
            index = pm.findBoyerMoore(text,pattern);
            System.out.println("Using BM matching to find \"" + P + "\" in \"" + T + "\"");
            if(index > -1)
                System.out.println("The index is: " + index);
            else
                System.out.println("The pattern is not found");
            System.out.println("The number of comparisons is: " + pm.getComparisons());
        }
        else if(method.equals("KMP")){
            index = pm.findKMP(text,pattern);
            System.out.println("Using KMP matching to find \"" + P + "\" in \"" + T + "\"");
            if(index > -1)
                System.out.println("The index is: " + index);
            else
                System.out.println("The pattern is not found");
            System.out.println("The number of comparisons is: " + pm.getComparisons());
        }
        System.out.println();
    }
}

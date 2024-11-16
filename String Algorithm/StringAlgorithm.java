import java.util.*;

public class StringAlgorithm {

    // Knuth-Morris-Pratt (KMP) pattern matching algorithm
    public int KMPSearch(String text, String pattern) {
        int[] lps = computeLPSArray(pattern);
        int n = text.length();
        int m = pattern.length();
        int i = 0, j = 0;

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                return i - j; // pattern found at index (i - j)
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return -1; // pattern not found
    }

    // Compute Longest Prefix Suffix (LPS) array for KMP algorithm
    public int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // Run Length Encoding (RLE) string compression
    public String runLengthEncoding(String str) {
        StringBuilder encoded = new StringBuilder();
        int n = str.length();

        for (int i = 0; i < n; i++) {
            int count = 1;
            while (i < n - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            encoded.append(count).append(str.charAt(i));
        }
        return encoded.toString();
    }

    public static void main(String[] args) {
        StringAlgorithm stringAlgo = new StringAlgorithm();

        // Test KMP Search
        String text = "abxabcabcaby";
        String pattern = "abcaby";
        int index = stringAlgo.KMPSearch(text, pattern);
        System.out.println("\nPattern found at index (KMP): " + index);

        // Test Run Length Encoding
        String str = "aaabbbcccaaa";
        String encodedStr = stringAlgo.runLengthEncoding(str);
        System.out.println("Run Length Encoding of \"" + str + "\": " + encodedStr);
    }
}

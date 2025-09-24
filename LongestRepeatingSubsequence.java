public class LongestRepeatingSubsequence {
    // Function to find LRS
    static String findLRS(String str) {
        int n = str.length();
        int[][] dp = new int[n + 1][n + 1];

        // Build DP table (like LCS, but with i != j condition)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (str.charAt(i - 1) == str.charAt(j - 1) && i != j) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct LRS from dp table
        int i = n, j = n;
        StringBuilder lrs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j - 1] + 1 &&
                str.charAt(i - 1) == str.charAt(j - 1) &&
                i != j) {
                lrs.append(str.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lrs.reverse().toString();
    }

    public static void main(String[] args) {
        String S = "AABCBDC";
        String lrs = findLRS(S);
        System.out.println("Longest Repeating Subsequence: " + lrs);
        System.out.println("Length: " + lrs.length());
    }
}

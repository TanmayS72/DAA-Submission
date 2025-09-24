public class LCS_Task {
    public static void main(String[] args) {
        String X = "AGCCCTAAGGGCTACCTAGCTT";
        String Y = "GACAGCCTACAAGCGTTAGCTTG";
        int m = X.length();
        int n = Y.length();

        int[][] L = new int[m + 1][n + 1];
        char[][] direction = new char[m + 1][n + 1]; // D, U, L

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                    direction[i][j] = 'D'; // diagonal match
                } else if (L[i - 1][j] >= L[i][j - 1]) {
                    L[i][j] = L[i - 1][j];
                    direction[i][j] = 'U'; // from top
                } else {
                    L[i][j] = L[i][j - 1];
                    direction[i][j] = 'L'; // from left
                }
            }
        }

        // Print cost matrix
        System.out.println("Cost Matrix:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%2d ", L[i][j]);
            }
            System.out.println();
        }

        // Print direction matrix
        System.out.println("\nDirection Matrix:");
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    System.out.print(".  "); // empty boundary
                } else {
                    System.out.print(direction[i][j] + "  ");
                }
            }
            System.out.println();
        }

        // Reconstruct LCS
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs.append(X.charAt(i - 1));
                i--; j--;
            } else if (L[i - 1][j] >= L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        lcs.reverse();

        // Final output
        System.out.println("\nFinal LCS length (cost): " + L[m][n]);
        System.out.println("LCS: " + lcs.toString());
    }
}

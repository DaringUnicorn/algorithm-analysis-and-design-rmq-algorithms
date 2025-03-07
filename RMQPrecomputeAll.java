
public class RMQPrecomputeAll {
    private int[][] dp; // Table to store precomputed minimum values
    private int n;      // Size of the input array

    // Constructor to initialize and precompute
    public RMQPrecomputeAll(int[] array) {
        this.n = array.length;
        dp = new int[n][n];
        precompute(array);
    }

    // Precompute all possible range minimums
    private void precompute(int[] array) {
        for (int i = 0; i < n; i++) {
            dp[i][i] = array[i]; // Single element range
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], array[j]);
            }
        }
    }

    // Query the minimum value in the range [L, R]
    public int query(int L, int R) {
        return dp[L][R]; // Access precomputed value
    }

}

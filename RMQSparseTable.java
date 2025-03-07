public class RMQSparseTable {
    private int[][] st; // Sparse Table
    private int[] log;  // Logarithmic values for efficient computation
    private int n;      // Size of the input array

    // Constructor to initialize and build the Sparse Table
    public RMQSparseTable(int[] array) {
        this.n = array.length;
        int maxLog = (int) (Math.log(n) / Math.log(2)) + 1;
        st = new int[n][maxLog];
        log = new int[n + 1];
        buildLog();
        buildSparseTable(array);
    }

    // Precompute logarithmic values for querying
    private void buildLog() {
        log[1] = 0;
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }
    }

    // Build the Sparse Table using dynamic programming
    private void buildSparseTable(int[] array) {
        for (int i = 0; i < n; i++) {
            st[i][0] = array[i]; // Base case for ranges of size 1
        }

        for (int j = 1; (1 << j) <= n; j++) { // For lengths 2^j
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    // Query the minimum value in the range [L, R]
    public int query(int L, int R) {
        int j = log[R - L + 1]; // Find the largest power of 2 <= range length
        return Math.min(st[L][j], st[R - (1 << j) + 1][j]);
    }
}

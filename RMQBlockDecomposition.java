public class RMQBlockDecomposition {
    private int[] blockMin; // Stores the minimum of each block
    private int blockSize;  // Size of each block
    private int n;          // Size of the input array

    // Constructor to initialize and precompute block minimums
    public RMQBlockDecomposition(int[] array) {
        this.n = array.length;
        this.blockSize = (int) Math.sqrt(n); // Block size is sqrt(n)
        int numBlocks = (n + blockSize - 1) / blockSize; // Total blocks
        blockMin = new int[numBlocks];

        // Initialize block minimums
        for (int i = 0; i < numBlocks; i++) {
            blockMin[i] = Integer.MAX_VALUE; // Set to max initially
        }

        // Compute the minimum for each block
        for (int i = 0; i < n; i++) {
            int blockIndex = i / blockSize;
            blockMin[blockIndex] = Math.min(blockMin[blockIndex], array[i]);
        }
    }

    // Query the minimum value in the range [L, R]
    public int query(int[] array, int L, int R) {
        int min = Integer.MAX_VALUE;

        // Iterate through the range
        while (L <= R) {
            // If the range is within a single block
            if (L % blockSize == 0 && L + blockSize - 1 <= R) {
                int blockIndex = L / blockSize;
                min = Math.min(min, blockMin[blockIndex]);
                L += blockSize; // Skip to the next block
            } else {
                min = Math.min(min, array[L]);
                L++;
            }
        }

        return min;
    }
}

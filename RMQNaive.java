public class RMQNaive {
    private int[] array; // The input array

    // Constructor to initialize the array
    public RMQNaive(int[] array) {
        this.array = array;
    }

    // Query the minimum value in the range [L, R]
    public int query(int L, int R) {
        int min = Integer.MAX_VALUE; // Start with the maximum possible value

        // Iterate through the range and find the minimum
        for (int i = L; i <= R; i++) {
            min = Math.min(min, array[i]);
        }

        return min;
    }
}
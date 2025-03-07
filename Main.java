import java.util.Random;

class Main{
    public static void main(String[] args) {
        int n = 15000; // Size of the input array
        int[] array = ScalabilityAnalysis.generateArray(n); // Generate an array from 0 to n
        int[] shuffled = ScalabilityAnalysis.shuffleArray(array); // Shuffle the array
        int[] reversed = ScalabilityAnalysis.reverseSort(array); // Reverse sort the array

        // Create instances of the different RMQ implementations
        RMQBlockDecomposition rmqBlock = new RMQBlockDecomposition(array);
        RMQNaive rmqNaive = new RMQNaive(array);
        RMQSparseTable rmqSparse = new RMQSparseTable(array);
        RMQPrecomputeAll rmqPrecompute = new RMQPrecomputeAll(array);

        // Test the RMQ implementations on the original array
        testRMQ(rmqBlock, array);
        testRMQ(rmqNaive, array);
        testRMQ(rmqSparse, array);
        testRMQ(rmqPrecompute, array);
        
        // Test the RMQ implementations on the shuffled array
        testRMQ(rmqBlock, shuffled);
        testRMQ(rmqNaive, shuffled);
        testRMQ(rmqSparse, shuffled);
        testRMQ(rmqPrecompute, shuffled);

        // Test the RMQ implementations on the reversed array
        testRMQ(rmqBlock, reversed);
        testRMQ(rmqNaive, reversed);
        testRMQ(rmqSparse, reversed);
        testRMQ(rmqPrecompute, reversed);


    }



    public class ScalabilityAnalysis {
        // Method to generate an array from 0 to the given upperBound
        public static int[] generateArray(int upperBound) {
            int[] array = new int[upperBound + 1]; // Create an array of size upperBound + 1
            for (int i = 0; i <= upperBound; i++) {
                array[i] = i; // Fill array with values from 0 to upperBound
            }
            return array;
        }


        
    public static int[] shuffleArray(int[] array) {
        int[] shuffled = array.clone(); // Original array is not modified
        Random rand = new Random(); // Random number generator

        for (int i = shuffled.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // Random index between 0 and i
            // Swap shuffled[i] and shuffled[j]
            int temp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = temp;
        }

        return shuffled; // Return the shuffled array
    }

    public static int[] reverseSort(int[] array) {
        int[] reversed = array.clone();
        int n = reversed.length;
        for (int i = 0; i < n / 2; i++) {
            // Swap elements from start to end
            int temp = reversed[i];
            reversed[i] = reversed[n - i - 1];
            reversed[n - i - 1] = temp;
        }
        return reversed;
    }
    }


    public static void testRMQ(Object rmq, int[] array) {
        int numQueries = array.length - 1; // Number of test queries
        Random rand = new Random();
        long startTime, endTime; // Variables for timing
    
        System.out.println("Testing " + rmq.getClass().getSimpleName() + " on array of size: " + numQueries);
    
        // Start timing in nanoseconds
        startTime = System.nanoTime();
    
        // Run multiple random queries
        for (int i = 0; i < numQueries + 1; i++) {
            int L = rand.nextInt(array.length); // Random start index
            int R = L + rand.nextInt(array.length - L); // Random end index (L <= R)
    
            int result = 0;
    
            // Call the appropriate query method based on RMQ implementation
            if (rmq instanceof RMQBlockDecomposition) {
                result = ((RMQBlockDecomposition) rmq).query(array, L, R);
            } else if (rmq instanceof RMQNaive) {
                result = ((RMQNaive) rmq).query(L, R);
            } else if (rmq instanceof RMQSparseTable) {
                result = ((RMQSparseTable) rmq).query(L, R);
            } else if (rmq instanceof RMQPrecomputeAll) {
                result = ((RMQPrecomputeAll) rmq).query(L, R);
            }
        }
    
        // End timing in nanoseconds
        endTime = System.nanoTime();
    
        // Convert elapsed time to milliseconds with decimal precision
        double elapsedTimeMs = (endTime - startTime) / 1_000_000.0;
    
        // Print results with higher precision
        System.out.printf("Total time for %d queries: %.3f ms\n\n", numQueries, elapsedTimeMs);
    }
    
}
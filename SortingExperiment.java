import java.util.Random;


public class SortingExperiment {

    private static final int[] SIZES = {20, 50, 100, 500};

    /** Generates an array of 'size' random integers in [1, 10000]. */
    private static int[] generateRandomArray(int size, long seed) {
        Random rand = new Random(seed);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000) + 1;
        }
        return arr;
    }

    /** Builds an "almost sorted" version: sort ascending, then swap 5 neighbouring pairs. */
    private static int[] buildAlmostSorted(int[] sortedBase) {
        int[] arr = sortedBase.clone();
        // simple ascending sort first (uses our own insertion sort logic to avoid built-ins)
        SortingAlgorithms sorter = new SortingAlgorithms();
        sorter.insertionSort(arr, false);

        // swap 5 neighbouring pairs at fixed, spread-out positions
        int n = arr.length;
        int[] swapStartPositions = {5, 20, 45, 65, 88};
        for (int pos : swapStartPositions) {
            if (pos + 1 < n) {
                int temp = arr[pos];
                arr[pos] = arr[pos + 1];
                arr[pos + 1] = temp;
            }
        }
        return arr;
    }

    private static void printRow(String algorithm, int size, long comparisons, long timeNs) {
        System.out.printf("%-15s %-6d %-15d %-15d%n", algorithm, size, comparisons, timeNs);
    }

    private static long[] runAndTime(SortingAlgorithms sorter, String algoName, int[] base, int size) {
        int[] copy = base.clone();
        long comparisons;
        long start, end;

        switch (algoName) {
            case "SelectionSort":
                start = System.nanoTime();
                sorter.selectionSort(copy, false);
                end = System.nanoTime();
                break;
            case "InsertionSort":
                start = System.nanoTime();
                sorter.insertionSort(copy, false);
                end = System.nanoTime();
                break;
            case "MergeSort":
                start = System.nanoTime();
                sorter.mergeSort(copy, false);
                end = System.nanoTime();
                break;
            case "QuickSort":
                start = System.nanoTime();
                sorter.quickSort(copy, false);
                end = System.nanoTime();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algoName);
        }
        comparisons = sorter.getComparisons();
        long timeNs = end - start;
        printRow(algoName, size, comparisons, timeNs);
        return new long[]{comparisons, timeNs};
    }

    /** Runs the full experiment described in Part C and prints a results table. */
    public static void run() {
        System.out.println("\n================== PART C: ALGORITHM EXPERIMENT ==================");
        SortingAlgorithms sorter = new SortingAlgorithms();
        String[] algorithms = {"SelectionSort", "InsertionSort", "MergeSort", "QuickSort"};

        System.out.printf("%-15s %-6s %-15s %-15s%n", "Algorithm", "Size", "Comparisons", "Time(ns)");
        System.out.println("--------------------------------------------------------------");

        for (int size : SIZES) {
            int[] base = generateRandomArray(size, 42L + size); // fixed seed per size for reproducibility
            for (String algo : algorithms) {
                runAndTime(sorter, algo, base, size);
            }
        }

        System.out.println("\n--- Additional test: Almost-sorted 100-element array ---");
        int[] base100 = generateRandomArray(100, 42L + 100);
        int[] almostSorted = buildAlmostSorted(base100);
        System.out.println("(Array sorted ascending, then 5 neighbouring pairs swapped)");
        System.out.printf("%-15s %-6s %-15s %-15s%n", "Algorithm", "Size", "Comparisons", "Time(ns)");
        System.out.println("--------------------------------------------------------------");
        for (String algo : algorithms) {
            runAndTime(sorter, algo, almostSorted, 100);
        }

        ;
    }
}

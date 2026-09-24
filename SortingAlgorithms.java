
public class SortingAlgorithms {

    // Counters reset before each call and read afterwards by the caller.
    private long comparisons;
    private long swapsOrShifts;

    public long getComparisons() { return comparisons; }
    public long getSwapsOrShifts() { return swapsOrShifts; }


    public void selectionSort(int[] arr, boolean verbose) {
        comparisons = 0;
        swapsOrShifts = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++; // data-value comparison
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swapsOrShifts++;
            }
            if (verbose && i < 3) {
                System.out.println("After pass " + (i + 1) + ": " + arrToString(arr));
            }
        }
    }


    public void insertionSort(int[] arr, boolean verbose) {
        comparisons = 0;
        swapsOrShifts = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++; // data-value comparison
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    swapsOrShifts++; // shift
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            if (verbose && i <= 3) {
                System.out.println("After pass " + i + ": " + arrToString(arr));
            }
        }
    }

    // ---------------------------------------------------------------
    // TASK B3 - MERGE SORT
    // ---------------------------------------------------------------
    public void mergeSort(int[] arr, boolean verbose) {
        comparisons = 0;
        swapsOrShifts = 0;
        int[] buffer = new int[arr.length];
        mergeSortHelper(arr, buffer, 0, arr.length - 1, verbose);
    }

    private void mergeSortHelper(int[] arr, int[] buffer, int left, int right, boolean verbose) {
        // Base case: a sub-array of size 0 or 1 is already sorted.
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortHelper(arr, buffer, left, mid, verbose);
        mergeSortHelper(arr, buffer, mid + 1, right, verbose);
        merge(arr, buffer, left, mid, right);
        if (verbose) {
            System.out.println("Merged [" + left + ".." + right + "] -> " + arrToString(slice(arr, left, right)));
        }
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        for (int k = left; k <= right; k++) {
            buffer[k] = arr[k];
        }
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            comparisons++; // data-value comparison
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
        while (j <= right) {
            arr[k++] = buffer[j++];
        }
    }


    public void quickSort(int[] arr, boolean verbose) {
        comparisons = 0;
        swapsOrShifts = 0;
        partitionStageCount = 0;
        quickSortHelper(arr, 0, arr.length - 1, verbose);
    }

    private int partitionStageCount;

    private void quickSortHelper(int[] arr, int low, int high, boolean verbose) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, verbose);
            quickSortHelper(arr, low, pivotIndex - 1, verbose);
            quickSortHelper(arr, pivotIndex + 1, high, verbose);
        }
    }

    /** Lomuto partition scheme, pivot = last element of the sub-array. */
    private int partition(int[] arr, int low, int high, boolean verbose) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            comparisons++; // data-value comparison
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                swapsOrShifts++;
            }
        }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        swapsOrShifts++;
        int pivotFinalIndex = i + 1;

        partitionStageCount++;
        if (verbose && partitionStageCount <= 2) {
            System.out.println("Partition stage " + partitionStageCount + ":");
            System.out.println("  Pivot            = " + pivot);
            System.out.println("  Left partition   = " + arrToString(slice(arr, low, Math.max(low, pivotFinalIndex - 1))));
            System.out.println("  Right partition  = " + arrToString(slice(arr, Math.min(high, pivotFinalIndex + 1), high)));
        }
        return pivotFinalIndex;
    }

    // ---------------------------------------------------------------
    // Helper utilities
    // ---------------------------------------------------------------
    private static int[] slice(int[] arr, int from, int to) {
        if (from > to) return new int[0];
        int[] result = new int[to - from + 1];
        System.arraycopy(arr, from, result, 0, result.length);
        return result;
    }

    public static String arrToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }


}

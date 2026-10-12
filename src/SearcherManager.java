public class SearcherManager {
    public static class SearchResult {
        private final int index;
        private final long steps;
        private final long elapsedNanos;

        public SearchResult(int index, long steps, long elapsedNanos) {
            this.index = index;
            this.steps = steps;
            this.elapsedNanos = elapsedNanos;
        }
        public int getIndex() { return index; }
        public long getSteps() { return steps; }
        public long getElapsedNanos() { return elapsedNanos; }
        public boolean isFound() { return index >= 0; }
    }

    public SearchResult linearSearch(int[] array, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            steps++;
            if (array[i] == target) {
                index = i;
                break;
            }
        }
        return new SearchResult(index, steps, System.nanoTime() - start);
    }

    /** The input must be sorted in ascending order. */
    public SearchResult binarySearch(int[] sortedArray, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int low = 0, high = sortedArray.length - 1, index = -1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (sortedArray[mid] == target) {
                index = mid;
                break;
            } else if (sortedArray[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(index, steps, System.nanoTime() - start);
    }
}
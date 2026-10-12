import java.util.Arrays;

/** Compares linear and binary search using the same target and data set. */
public class PerformanceTest {
    private final SearcherManager searcher = new SearcherManager();

    public void compareSearches(int[] values, int target, ResultLog log) {
        if (values == null || values.length == 0) {
            System.out.println("Add at least one array value before running the performance test.");
            if (log != null) log.add("Performance test skipped: array was empty.");
            return;
        }

        int[] sorted = Arrays.copyOf(values, values.length);
        Arrays.sort(sorted);

        SearcherManager.SearchResult linear = searcher.linearSearch(values, target);
        SearcherManager.SearchResult binary = searcher.binarySearch(sorted, target);

        String linearLine = format("Linear search", linear);
        String binaryLine = format("Binary search (sorted copy)", binary);

        System.out.println("\n===== SEARCH PERFORMANCE COMPARISON =====");
        System.out.println("Target: " + target);
        System.out.println(linearLine);
        System.out.println(binaryLine);
        System.out.println("Note: timings are machine/load dependent; operation counts are usually more useful for a small demo.");
        System.out.println("==========================================");

        if (log != null) {
            log.add("Target " + target + " | " + linearLine);
            log.add("Target " + target + " | " + binaryLine);
        }
    }

    private String format(String name, SearcherManager.SearchResult result) {
        String outcome = result.isFound() ? "found at index " + result.getIndex() : "not found";
        return name + ": " + outcome + ", steps=" + result.getSteps()
                + ", elapsed=" + result.getElapsedNanos() + " ns";
    }
}

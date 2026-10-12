import java.util.Arrays;
import java.util.Scanner;

/** Main menu for the Data Structure and Graph Performance Analyzer. */
public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final MyArray ARRAY = new MyArray();
    private static final MyStack STACK = new MyStack();
    private static final QueueManager QUEUE = new QueueManager();
    private static final LinkedListManager LINKED_LIST = new LinkedListManager();
    private static final Graph GRAPH = new Graph();
    private static final SearcherManager SEARCHER = new SearcherManager();
    private static final ResultLog RESULTS = new ResultLog();
    private static final PerformanceTest PERFORMANCE = new PerformanceTest();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("==============================================");
        System.out.println(" Data Structure and Graph Performance Analyzer");
        System.out.println("==============================================");

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1: arrayMenu(); break;
                case 2: stackMenu(); break;
                case 3: queueMenu(); break;
                case 4: linkedListMenu(); break;
                case 5: searchMenu(); break;
                case 6: graphMenu(); break;
                case 7: PERFORMANCE.compareSearches(ARRAY.toArray(), readInt("Target value: "), RESULTS); break;
                case 8: displayAllResults(); break;
                case 9: RESULTS.display(); break;
                case 0: running = false; System.out.println("Exiting. Thank you!"); break;
                default: System.out.println("Invalid choice. Please choose one of the listed options.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1. Array operations");
        System.out.println("2. Stack operations");
        System.out.println("3. Queue operations");
        System.out.println("4. Linked list operations");
        System.out.println("5. Linear and binary search");
        System.out.println("6. Graph operations (add vertex/edge, BFS, DFS)");
        System.out.println("7. Compare search performance");
        System.out.println("8. Display all data-structure contents");
        System.out.println("9. Display result log");
        System.out.println("0. Exit");
    }

    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--- ARRAY ---");
            System.out.println("1. Insert  2. Delete by value  3. Search  4. Display  0. Back");
            int choice = readInt("Choice: ");
            if (choice == 0) return;
            switch (choice) {
                case 1: {
                    int value = readInt("Value to insert: ");
                    ARRAY.insert(value);
                    RESULTS.add("Array: inserted " + value);
                    break;
                }
                case 2: {
                    int value = readInt("Value to delete: ");
                    boolean deleted = ARRAY.delete(value);
                    String message = deleted ? "Array: deleted " + value : "Array: value " + value + " not found for deletion";
                    System.out.println(message);
                    RESULTS.add(message);
                    break;
                }
                case 3: {
                    int value = readInt("Value to search: ");
                    int index = ARRAY.search(value);
                    String message = index >= 0 ? "Array: " + value + " found at index " + index : "Array: " + value + " not found";
                    System.out.println(message);
                    RESULTS.add(message);
                    break;
                }
                case 4: ARRAY.display(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void stackMenu() {
        while (true) {
            System.out.println("\n--- STACK (LIFO) ---");
            System.out.println("1. Push  2. Pop  3. Peek  4. Display  0. Back");
            int choice = readInt("Choice: ");
            if (choice == 0) return;
            switch (choice) {
                case 1: {
                    int value = readInt("Value to push: ");
                    STACK.push(value);
                    RESULTS.add("Stack: pushed " + value);
                    break;
                }
                case 2: {
                    Integer value = STACK.pop();
                    if (value != null) RESULTS.add("Stack: popped " + value);
                    break;
                }
                case 3: {
                    Integer value = STACK.peek();
                    if (value != null) System.out.println("Top value: " + value);
                    break;
                }
                case 4: STACK.display(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void queueMenu() {
        while (true) {
            System.out.println("\n--- QUEUE (FIFO) ---");
            System.out.println("1. Enqueue  2. Dequeue  3. Peek  4. Display  0. Back");
            int choice = readInt("Choice: ");
            if (choice == 0) return;
            switch (choice) {
                case 1: {
                    int value = readInt("Value to enqueue: ");
                    QUEUE.enqueue(value);
                    RESULTS.add("Queue: enqueued " + value);
                    break;
                }
                case 2: {
                    Integer value = QUEUE.dequeue();
                    if (value != null) RESULTS.add("Queue: dequeued " + value);
                    break;
                }
                case 3: {
                    Integer value = QUEUE.peek();
                    if (value != null) System.out.println("Front value: " + value);
                    break;
                }
                case 4: QUEUE.display(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void linkedListMenu() {
        while (true) {
            System.out.println("\n--- LINKED LIST ---");
            System.out.println("1. Insert at end  2. Delete by value  3. Search  4. Display  0. Back");
            int choice = readInt("Choice: ");
            if (choice == 0) return;
            switch (choice) {
                case 1: {
                    int value = readInt("Value to insert: ");
                    LINKED_LIST.insert(value);
                    RESULTS.add("Linked list: inserted " + value);
                    break;
                }
                case 2: {
                    int value = readInt("Value to delete: ");
                    boolean deleted = LINKED_LIST.delete(value);
                    String message = deleted ? "Linked list: deleted " + value : "Linked list: value " + value + " not found for deletion";
                    System.out.println(message);
                    RESULTS.add(message);
                    break;
                }
                case 3: {
                    int value = readInt("Value to search: ");
                    boolean found = LINKED_LIST.search(value);
                    String message = found ? "Linked list: " + value + " found" : "Linked list: " + value + " not found";
                    System.out.println(message);
                    RESULTS.add(message);
                    break;
                }
                case 4: LINKED_LIST.display(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void searchMenu() {
        int[] values = ARRAY.toArray();
        if (values.length == 0) {
            System.out.println("The array is empty. Insert values in the Array menu first.");
            return;
        }
        int target = readInt("Target value: ");
        SearcherManager.SearchResult linear = SEARCHER.linearSearch(values, target);
        int[] sorted = Arrays.copyOf(values, values.length);
        Arrays.sort(sorted);
        SearcherManager.SearchResult binary = SEARCHER.binarySearch(sorted, target);

        printSearchResult("Linear search", linear);
        printSearchResult("Binary search (sorted copy)", binary);
        RESULTS.add(searchSummary("Linear search", target, linear));
        RESULTS.add(searchSummary("Binary search (sorted copy)", target, binary));
    }

    private static void printSearchResult(String name, SearcherManager.SearchResult result) {
        System.out.println(name + ": " + (result.isFound() ? "found at index " + result.getIndex() : "not found")
                + ", steps=" + result.getSteps() + ", elapsed=" + result.getElapsedNanos() + " ns");
    }

    private static String searchSummary(String name, int target, SearcherManager.SearchResult result) {
        return name + " for " + target + ": " + (result.isFound() ? "index " + result.getIndex() : "not found")
                + ", steps=" + result.getSteps() + ", " + result.getElapsedNanos() + " ns";
    }

    private static void graphMenu() {
        while (true) {
            System.out.println("\n--- GRAPH (UNDIRECTED) ---");
            System.out.println("1. Add vertex  2. Add edge  3. Display  4. BFS  5. DFS  0. Back");
            int choice = readInt("Choice: ");
            if (choice == 0) return;
            switch (choice) {
                case 1: {
                    int vertex = readInt("Vertex value: ");
                    GRAPH.addVertex(vertex);
                    RESULTS.add("Graph: requested vertex " + vertex);
                    break;
                }
                case 2: {
                    int from = readInt("First vertex: ");
                    int to = readInt("Second vertex: ");
                    GRAPH.addEdge(from, to);
                    RESULTS.add("Graph: added edge " + from + " -- " + to);
                    break;
                }
                case 3: GRAPH.displayGraph(); break;
                case 4: {
                    int start = readInt("BFS start vertex: ");
                    RESULTS.add("Graph BFS from " + start + ": " + GRAPH.bfs(start));
                    break;
                }
                case 5: {
                    int start = readInt("DFS start vertex: ");
                    RESULTS.add("Graph DFS from " + start + ": " + GRAPH.dfs(start));
                    break;
                }
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static void displayAllResults() {
        System.out.println("\n========== ALL STRUCTURES ==========");
        ARRAY.display();
        STACK.display();
        QUEUE.display();
        LINKED_LIST.display();
        GRAPH.displayGraph();
        System.out.println("====================================");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = INPUT.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}

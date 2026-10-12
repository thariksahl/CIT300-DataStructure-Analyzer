import java.util.*;

/** Undirected graph using an adjacency list; supports BFS and DFS. */
public class Graph {
    private final Map<Integer, List<Integer>> adjacency = new LinkedHashMap<>();

    public void addVertex(int vertex) {
        if (!adjacency.containsKey(vertex)) {
            adjacency.put(vertex, new ArrayList<>());
            System.out.println("Vertex " + vertex + " added.");
        } else {
            System.out.println("Vertex " + vertex + " already exists.");
        }
    }

    public void addEdge(int from, int to) {
        addVertexSilently(from);
        addVertexSilently(to);
        if (!adjacency.get(from).contains(to)) adjacency.get(from).add(to);
        if (!adjacency.get(to).contains(from)) adjacency.get(to).add(from);
        System.out.println("Edge added: " + from + " -- " + to);
    }

    public void displayGraph() {
        if (adjacency.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Graph adjacency list:");
        for (Map.Entry<Integer, List<Integer>> entry : adjacency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public List<Integer> bfs(int start) {
        if (!adjacency.containsKey(start)) {
            System.out.println("Start vertex does not exist.");
            return Collections.emptyList();
        }
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.remove();
            order.add(current);
            for (int neighbor : adjacency.get(current)) {
                if (visited.add(neighbor)) queue.add(neighbor);
            }
        }
        System.out.println("BFS: " + order);
        return order;
    }

    public List<Integer> dfs(int start) {
        if (!adjacency.containsKey(start)) {
            System.out.println("Start vertex does not exist.");
            return Collections.emptyList();
        }
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new LinkedHashSet<>();
        dfsVisit(start, visited, order);
        System.out.println("DFS: " + order);
        return order;
    }

    public boolean containsVertex(int vertex) { return adjacency.containsKey(vertex); }
    public int vertexCount() { return adjacency.size(); }

    private void dfsVisit(int vertex, Set<Integer> visited, List<Integer> order) {
        visited.add(vertex);
        order.add(vertex);
        for (int neighbor : adjacency.get(vertex)) {
            if (!visited.contains(neighbor)) dfsVisit(neighbor, visited, order);
        }
    }

    private void addVertexSilently(int vertex) {
        if (!adjacency.containsKey(vertex)) adjacency.put(vertex, new ArrayList<>());
    }
}

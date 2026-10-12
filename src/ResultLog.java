import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Stores operation and performance messages for later display. */
public class ResultLog {
    private final List<String> entries = new ArrayList<>();

    public void add(String message) {
        if (message != null && !message.trim().isEmpty()) entries.add(message);
    }

    public void display() {
        if (entries.isEmpty()) {
            System.out.println("No results have been logged yet.");
            return;
        }
        System.out.println("\n========== RESULT LOG ==========");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i));
        }
        System.out.println("================================");
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public void clear() { entries.clear(); }
}

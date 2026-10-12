public class QueueManager {
    private int[] data;
    private int front;
    private int size;

    public QueueManager() { this(10); }

    public QueueManager(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be at least 1.");
        data = new int[capacity];
        front = 0;
        size = 0;
    }

    public void enqueue(int value) {
        if (size == data.length) grow();
        int rear = (front + size) % data.length;
        data[rear] = value;
        size++;
        System.out.println(value + " added to the queue.");
    }

    public Integer dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty; nothing to dequeue.");
            return null;
        }
        int value = data[front];
        front = (front + 1) % data.length;
        size--;
        System.out.println("Removed: " + value);
        return value;
    }

    public Integer peek() {
        if (size == 0) {
            System.out.println("Queue is empty.");
            return null;
        }
        return data[front];
    }

    public void display() {
        if (size == 0) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue (front to rear): ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[(front + i) % data.length] + (i < size - 1 ? " " : ""));
        }
        System.out.println();
    }

    public int getSize() { return size; }

    private void grow() {
        int[] larger = new int[data.length * 2];
        for (int i = 0; i < size; i++) larger[i] = data[(front + i) % data.length];
        data = larger;
        front = 0;
    }
}
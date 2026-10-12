public class MyStack {
    private int[] data;
    private int top;

    public MyStack() {
        this(10);
    }

    public MyStack(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be at least 1.");
        data = new int[capacity];
        top = -1;
    }

    public void push(int value) {
        if (top + 1 == data.length) {
            int[] larger = new int[data.length * 2];
            System.arraycopy(data, 0, larger, 0, data.length);
            data = larger;
        }
        data[++top] = value;
        System.out.println(value + " pushed onto the stack.");
    }

    public Integer pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty; nothing to pop.");
            return null;
        }
        int value = data[top--];
        System.out.println("Popped: " + value);
        return value;
    }

    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return data[top];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(data[i] + (i > 0 ? " " : ""));
        }
        System.out.println();
    }

    public int getSize() { return top + 1; }
    public boolean isEmpty() { return top == -1; }
}
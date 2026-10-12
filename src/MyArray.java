import java.util.Arrays;

/** A small integer array wrapper with insert, delete, search, and display operations. */
public class MyArray {
    private int[] data;
    private int size;

    public MyArray() {
        this(10);
    }

    public MyArray(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
        data = new int[capacity];
        size = 0;
    }

    public boolean insert(int value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = value;
        return true;
    }

    /** Deletes the first matching value. */
    public boolean delete(int value) {
        int index = search(value);
        if (index == -1) return false;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    /** Returns the index of the first matching value, or -1 if absent. */
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) return i;
        }
        return -1;
    }

    public void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + (i < size - 1 ? " " : ""));
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }
}
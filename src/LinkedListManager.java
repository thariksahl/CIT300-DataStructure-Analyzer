public class LinkedListManager {
    private static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private int size;

    public void insert(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) current = current.next;
            current.next = node;
        }
        size++;
        System.out.println(value + " inserted into the linked list.");
    }

    /** Deletes the first occurrence of the value. */
    public boolean delete(int value) {
        if (head == null) return false;
        if (head.value == value) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.value != value) current = current.next;
        if (current.next == null) return false;
        current.next = current.next.next;
        size--;
        return true;
    }

    public boolean search(int value) {
        Node current = head;
        while (current != null) {
            if (current.value == value) return true;
            current = current.next;
        }
        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }
        System.out.print("Linked list: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println(" -> null");
    }

    public int getSize() { return size; }

    public int[] toArray() {
        int[] values = new int[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            values[i++] = current.value;
            current = current.next;
        }
        return values;
    }
}

public class StudentQueue {

    private Student[] items;
    private int front;   // index of the front element
    private int rear;    // index of the last element
    private int count;   // number of elements currently stored
    private int capacity;

    public StudentQueue() {
        this(10);
    }

    public StudentQueue(int initialCapacity) {
        capacity = initialCapacity;
        items = new Student[capacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    /** Adds a student to the rear of the queue. */
    public void enqueue(Student student) {
        if (count == capacity) {
            resize();
        }
        rear = (rear + 1) % capacity;
        items[rear] = student;
        count++;
    }

    /** Removes and returns the student at the front of the queue. */
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty - no student to serve.");
            return null;
        }
        Student served = items[front];
        items[front] = null;
        front = (front + 1) % capacity;
        count--;
        return served;
    }

    /** Returns (without removing) the student at the front of the queue. */
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return items[front];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    /** Displays all students currently waiting, from front to rear. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("-- Waiting queue is empty --");
            return;
        }
        System.out.println("-- Waiting Queue (front -> rear) --");
        int index = front;
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + items[index]);
            index = (index + 1) % capacity;
        }
    }

    /** Doubles the internal array capacity when the queue is full. */
    private void resize() {
        int newCapacity = capacity * 2;
        Student[] newItems = new Student[newCapacity];
        int index = front;
        for (int i = 0; i < count; i++) {
            newItems[i] = items[index];
            index = (index + 1) % capacity;
        }
        items = newItems;
        front = 0;
        rear = count - 1;
        capacity = newCapacity;
    }



}

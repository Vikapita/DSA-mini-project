
public class StudentLinkedList {

    /** Node of the singly linked list. */
    private static class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    /** Inserts a student at the beginning of the list. */
    public void insertAtBeginning(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /** Inserts a student at the end of the list. */
    public void insertAtEnd(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Inserts a student at a specific 1-based position.
     * position = 1 means the new head; position = size+1 means the new tail.
     */
    public boolean insertAtPosition(Student student, int position) {
        if (position < 1 || position > size + 1) {
            System.out.println("Invalid position: " + position);
            return false;
        }
        if (position == 1) {
            insertAtBeginning(student);
            return true;
        }
        Node newNode = new Node(student);
        Node current = head;
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
        return true;
    }

    /** General-purpose insert used by the Part D menu (inserts at the end). */
    public void insertStudent(Student student) {
        insertAtEnd(student);
    }

    /** Deletes the first node whose studentNo matches. Returns true if deleted. */
    public boolean deleteStudent(String studentNo) {
        if (head == null) return false;

        if (head.data.getStudentNo().equals(studentNo)) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        while (current.next != null && !current.next.data.getStudentNo().equals(studentNo)) {
            current = current.next;
        }
        if (current.next == null) {
            return false; // not found
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    /** Linear search by studentNo. Returns the Student, or null if not found. */
    public Student searchStudent(String studentNo) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentNo().equals(studentNo)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /** Displays all student service records from head to tail. */
    public void displayStudents() {
        if (head == null) {
            System.out.println("-- Student Service Records list is empty --");
            return;
        }
        System.out.println("-- Student Service Records (head -> tail) --");
        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data);
            current = current.next;
            i++;
        }
    }

    /** Returns a simple textual sketch of the list, e.g. "HEAD -> A -> B -> NULL". */
    public String sketch() {
        StringBuilder sb = new StringBuilder("HEAD -> ");
        Node current = head;
        while (current != null) {
            sb.append(current.data.getStudentNo());
            sb.append(" -> ");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();
    }


}

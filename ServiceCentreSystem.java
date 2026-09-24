import java.util.Scanner;

public class ServiceCentreSystem {

    private StudentQueue waitingQueue = new StudentQueue();
    private StudentLinkedList serviceRecords = new StudentLinkedList();
    private ArrayStatistics dailyStats = new ArrayStatistics(50);
    private SortingAlgorithms sorter = new SortingAlgorithms();
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Select option: ");
            switch (choice) {
                case 1: addToQueue(); break;
                case 2: serveNextStudent(); break;
                case 3: waitingQueue.displayQueue(); break;
                case 4: addServiceRecord(); break;
                case 5: serviceRecords.displayStudents(); break;
                case 6: searchRecord(); break;
                case 7: removeRecord(); break;
                case 8: dailyStats.displayStatistics(); break;
                case 9: sortServiceTimes(); break;
                case 10: SortingExperiment.run(); break;
                case 11: running = false; System.out.println("Exiting. Goodbye!"); break;
                default: System.out.println("Invalid option. Please choose 1-11.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n========================================");
        System.out.println("              CAMPUS SERVICE CENTRE    ");
        System.out.println("========================================");
        System.out.println("1. Add student to waiting queue");
        System.out.println("2. Serve next student (remove from queue)");
        System.out.println("3. Display waiting students");
        System.out.println("4. Add student service record (Linked List - insertStudent())");
        System.out.println("5. Display student service records");
        System.out.println("6. Search for student record");
        System.out.println("7. Remove student record");
        System.out.println("8. Display daily statistics");
        System.out.println("9. Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
    }

    // Option 1: Queue - enqueue
    private void addToQueue() {
        Student s = readStudentFromInput();
        waitingQueue.enqueue(s);
        System.out.println("Added to queue: " + s);
    }

    // Option 2: Queue - dequeue
    private void serveNextStudent() {
        Student served = waitingQueue.dequeue();
        if (served != null) {
            System.out.println("Now serving: " + served);
            dailyStats.addServiceTime(served.getEstimatedServiceTime());
            recordedTimesMirror.add(served.getEstimatedServiceTime());
        }
    }

    // Option 4: Linked List - insertion
    private void addServiceRecord() {
        Student s = readStudentFromInput();
        serviceRecords.insertStudent(s);
        System.out.println("Service record added: " + s);
    }

    // Option 6: Linked List - search
    private void searchRecord() {
        System.out.print("Enter Student No. to search: ");
        String no = scanner.nextLine().trim();
        Student found = serviceRecords.searchStudent(no);
        System.out.println(found != null ? "Found: " + found : "Student not found.");
    }

    // Option 7: Linked List - deletion
    private void removeRecord() {
        System.out.print("Enter Student No. to remove: ");
        String no = scanner.nextLine().trim();
        boolean removed = serviceRecords.deleteStudent(no);
        System.out.println(removed ? "Record removed." : "Student not found - nothing removed.");
    }

    // Option 9: Sorting algorithm(s) applied to today's recorded service times
    private void sortServiceTimes() {
        System.out.println("Choose sorting algorithm: 1) Selection 2) Insertion 3) Merge 4) Quick");
        int algoChoice = readInt("Choice: ");
        int[] times = extractServiceTimesArray();
        if (times.length == 0) {
            System.out.println("No service times recorded yet (serve some students first via option 2).");
            return;
        }
        System.out.println("Before sorting: " + SortingAlgorithms.arrToString(times));
        switch (algoChoice) {
            case 1: sorter.selectionSort(times, false); break;
            case 2: sorter.insertionSort(times, false); break;
            case 3: sorter.mergeSort(times, false); break;
            case 4: sorter.quickSort(times, false); break;
            default: System.out.println("Invalid choice."); return;
        }
        System.out.println("After sorting:  " + SortingAlgorithms.arrToString(times));
        System.out.println("Comparisons used: " + sorter.getComparisons());
    }


    private int[] extractServiceTimesArray() {
        return recordedTimesMirror.toArrayCopy();
    }

    private final IntList recordedTimesMirror = new IntList();

    private static class IntList {
        private int[] data = new int[10];
        private int size = 0;

        void add(int value) {
            if (size == data.length) {
                int[] bigger = new int[data.length * 2];
                System.arraycopy(data, 0, bigger, 0, size);
                data = bigger;
            }
            data[size++] = value;
        }

        int[] toArrayCopy() {
            int[] copy = new int[size];
            System.arraycopy(data, 0, copy, 0, size);
            return copy;
        }
    }

    private Student readStudentFromInput() {
        System.out.print("Student Number: ");
        String no = scanner.nextLine().trim();
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Service Type: ");
        String type = scanner.nextLine().trim();
        int time = readInt("Estimated Service Time (min): ");
        return new Student(no, name, type, time);
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
}

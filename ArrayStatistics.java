
public class ArrayStatistics {

    private int[] serviceTimes;
    private int count; // number of valid entries currently stored

    public ArrayStatistics(int capacity) {
        serviceTimes = new int[capacity];
        count = 0;
    }

    public void addServiceTime(int minutes) {
        if (count == serviceTimes.length) {
            int[] bigger = new int[serviceTimes.length * 2];
            for (int i = 0; i < count; i++) bigger[i] = serviceTimes[i];
            serviceTimes = bigger;
        }
        serviceTimes[count++] = minutes;
    }

    public int totalStudentsServed() {
        return count;
    }

    public int totalServiceTime() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += serviceTimes[i];
        }
        return total;
    }

    public double averageServiceTime() {
        if (count == 0) return 0.0;
        return (double) totalServiceTime() / count;
    }

    /** Manual traversal maximum - no built-in max(). */
    public int highestServiceTime() {
        if (count == 0) return -1;
        int highest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] > highest) {
                highest = serviceTimes[i];
            }
        }
        return highest;
    }

    public int lowestServiceTime() {
        if (count == 0) return -1;
        int lowest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] < lowest) {
                lowest = serviceTimes[i];
            }
        }
        return lowest;
    }

    public int countLongerThan(int thresholdMinutes) {
        int c = 0;
        for (int i = 0; i < count; i++) {
            if (serviceTimes[i] > thresholdMinutes) {
                c++;
            }
        }
        return c;
    }

    public void displayStatistics() {
        System.out.println("-- Daily Service Statistics --");
        System.out.println("Total students served      : " + totalStudentsServed());
        System.out.println("Total service time (min)   : " + totalServiceTime());
        System.out.printf ("Average service time (min) : %.2f%n", averageServiceTime());
        System.out.println("Highest service time (min) : " + highestServiceTime());
        System.out.println("Lowest service time (min)  : " + lowestServiceTime());
        System.out.println("Services longer than 10 min: " + countLongerThan(10));
    }


    }


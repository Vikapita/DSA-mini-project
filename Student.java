
public class Student {
    private String studentNo;
    private String name;
    private String serviceType;
    private int estimatedServiceTime; // in minutes

    public Student(String studentNo, String name, String serviceType, int estimatedServiceTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedServiceTime = estimatedServiceTime;
    }

    public String getStudentNo() { return studentNo; }
    public String getName() { return name; }
    public String getServiceType() { return serviceType; }
    public int getEstimatedServiceTime() { return estimatedServiceTime; }

    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
    public void setName(String name) { this.name = name; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public void setEstimatedServiceTime(int t) { this.estimatedServiceTime = t; }

    @Override
    public String toString() {
        return String.format("[%s | %-10s | %-12s | %3d min]",
                studentNo, name, serviceType, estimatedServiceTime);
    }
}

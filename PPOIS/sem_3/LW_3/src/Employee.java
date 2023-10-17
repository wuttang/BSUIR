public class Employee {
    private String name;
    private int employeeID;

    public Employee(String name, int employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeID() {
        return employeeID;
    }
}

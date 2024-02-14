package business.domain;

public class Employee {
    private String firstName;
    private String lastName;
    private int employeeID;
    private String pin;

    public Employee(String firstName, String lastName, int employeeID, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String toString(){
        return String.format("Last Name: %s, ID: %s\n", getLastName(),getEmployeeID());
    }
}

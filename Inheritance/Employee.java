public class Employee extends Person{
    private double payRate;    
    private double hoursWorked;
    private String department;

    public final int HOURS = 40;
    public final double OVERTIME = 1.5;

    public Employee(){
        super();
        payRate =0;
        hoursWorked =0;
        department = "";
    }

    public Employee(String firstName, String lastName, double payRate, double hoursWorked, String department) {
        super(firstName, lastName);
        this.payRate = payRate;
        this.hoursWorked = hoursWorked;
        this.department = department;
    }

    public String toString() {
        return "The wages for " + super.toString() + " from the " + department + " department are: " + calculatePay();
    }

    public double calculatePay() {
        if (hoursWorked <= 40) {
        return hoursWorked * payRate;
        } else {
        return (40 * payRate) + ((hoursWorked - 40) * OVERTIME * payRate);
        }
    }

    public void print() {
        System.out.println("The employee " + super.toString() + " from the " + department + " department worked "
        + hoursWorked + " hours with a pay rate of $" + payRate
        + ". The wages for this employee are $" + calculatePay());
    }

    public void setAll(String first, String last, double rate, double hours, String dep) {
        super.setName(first, last);
        this.payRate = rate;
        this.hoursWorked = hours;
        this.department = dep;
    }

    public double getPayRate() {
        return payRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public String getDepartment() {
        return department;
    }

    public boolean equals (Object o){
        Employee e = (Employee) o;
        return super.equals(e) && hoursWorked == e.hoursWorked && payRate == e.payRate
        && department.equals(e.department);
    }

    public Employee getCopy() {
        Employee e = new Employee();
        copy(e);
        return e;
    }
          
    public void copy(Employee e) {
        e.setAll(super.getFirst(),super.getLast(), getPayRate(), getHoursWorked(), getDepartment());
    } 
}

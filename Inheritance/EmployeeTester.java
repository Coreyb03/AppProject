import java.util.Scanner;

public class EmployeeTester {

    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        String last, first, dept;
        double pay_rate;
        int hours;
        Employee prof = new Employee("John", "Doe", 25.50, 50, "COSC"); 
        Employee newEmp = new Employee();

        System.out.println("Enter Employee first name: ");
        first = scanner.next();
        System.out.println("Enter Employee last name: ");
        last = scanner.next();
        System.out.println("Enter Employee department: ");
        dept = scanner.next();
        System.out.println("Enter Employee pay rate: ");
        pay_rate = scanner.nextDouble();
        System.out.println("Enter Employee hours worked: ");
        hours = scanner.nextInt();
        newEmp.setAll(first, last, pay_rate, hours, dept);

        System.out.println(prof);
        System.out.println(newEmp);

        prof.print();
        newEmp.print();
        
        System.out.println("Are the two employees equal? " + prof.equals(newEmp));
    }
 

}
public class Person {
    private String firstName;
    private String lastName;

    public Person(String first, String last){
        firstName = first;
        lastName = last;
    }

    public Person(){
        firstName = "";
        lastName = "";
    }

    public String getFirst(){
        return firstName;
    }

    public String getLast(){
        return lastName;
    }

    public void setFirst(String first){
        firstName = first;
    }

    public void setlast(String last){
        lastName = last;
    }

    public void setName(String first, String last){
        this.setFirst(first);
        this.setlast(last);
    }

    public void print(){
        System.out.print(this.toString());
    }

    public void printLastFirst(){
        System.out.print(lastName +", " + firstName);
    }

    public String toString(){
        return firstName + " " + lastName;
    }

    public boolean equals(Person p){
        return firstName.equals(p.firstName) && lastName.equals(p.lastName);
    }

    public void copy(Person person){
        lastName = person.lastName;
        firstName = person.firstName;
    }

    public Person getCopy(){
        return new Person(firstName, lastName);
    }

}

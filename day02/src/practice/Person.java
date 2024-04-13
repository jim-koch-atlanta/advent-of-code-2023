package practice;

public class Person {
    // Attributes
    private String name;
    private int age;
    private String gender;

    // Constructor
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Method to display information about the person
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }

    // Method to wish the person a happy birthday
    public void celebrateBirthday() {
        age++;
        System.out.println("Happy Birthday, " + name + "! You are now " + age + " years old.");
    }
}

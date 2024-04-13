package practice;

public class Jim extends Person {
    private String job;
    private String[] friends;

    public Jim(String job, String[] friends) {
        super("Jim", 43, "male");
        this.job = job;
        this.friends = friends;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(
                "My job is " +
                this.job +
                " and my friends are " +
                String.join(", ", this.friends));
    }
}

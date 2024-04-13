import java.util.UUID;

public class BankAccount implements AutoCloseable {
    protected UUID accountNumber;
    protected String accountName;
    protected double balance;
    public BankAccount(String accountName, double startingBalance) {
        this.accountName = accountName;
        this.balance = startingBalance;

        this.accountNumber = UUID.randomUUID();
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return this.balance;
    }

    public UUID getAccountNumber() {
        return this.accountNumber;
    }

    public void printSummary() {
        System.out.println("Account Name: " + this.accountName);
        System.out.println("Account Num:  " + this.accountNumber.toString());
        System.out.printf("Balance:      $%.2d" + this.balance);
    }

    /**
     * @param amount Deposit amount. Must be greater than 0.
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        this.balance += amount;
    }

    /**
     * @param amount Withdrawal amount. Must be greater than 0, and must be less than
     *               the account balance.
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException();
        }

        this.balance -= amount;
    }

    @Override
    public void close() throws Exception {
        // Do nothing.
    }
}

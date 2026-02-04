import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Create 2.Deposit 3.Withdraw 4.Balance 5.Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Account No: ");
                    int accNo = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double bal = sc.nextDouble();
                    service.createAccount(accNo, name, bal);
                    break;

                case 2:
                    System.out.print("Enter Account No: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double dep = sc.nextDouble();
                    service.deposit(accNo, dep);
                    break;

                case 3:
                    System.out.print("Enter Account No: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double wd = sc.nextDouble();
                    service.withdraw(accNo, wd);
                    break;

                case 4:
                    System.out.print("Enter Account No: ");
                    accNo = sc.nextInt();
                    service.checkBalance(accNo);
                    break;

                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

class BankService {
    HashMap<Integer, BankAccount> accounts = new HashMap<>();

    public void createAccount(int accNo, String name, double balance) {
        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists");
            return;
        }
        accounts.put(accNo, new BankAccount(accNo, name, balance));
        System.out.println("Account Created Successfully");
    }

    public void deposit(int accNo, double amount) {
        BankAccount acc = accounts.get(accNo);
        if (acc == null) {
            System.out.println("Account not found");
            return;
        }
        acc.deposit(amount);
        System.out.println("Amount Deposited");
    }

    public void withdraw(int accNo, double amount) {
        BankAccount acc = accounts.get(accNo);
        if (acc == null) {
            System.out.println("Account not found");
            return;
        }
        try {
            acc.withdraw(amount);
            System.out.println("Amount Withdrawn");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkBalance(int accNo) {
        BankAccount acc = accounts.get(accNo);
        if (acc == null) {
            System.out.println("Account not found");
            return;
        }
        System.out.println("Balance: " + acc.getBalance());
    }
}

class BankAccount {
    private int accountNo;
    private String name;
    private double balance;

    public BankAccount(int accountNo, String name, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (amount > balance) {
            throw new Exception("Insufficient Balance");
        }
        balance -= amount;
    }
}

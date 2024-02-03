import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM_Interface {
    private static final String USER_ID = "1234";
    private static final String USER_PIN = "9999";
    private double balance = 10000.00;
    private List<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        System.out.print("Enter User PIN: ");
        String userPin = sc.nextLine();

        ATM_Interface atm = new ATM_Interface();

        if (atm.authenticateUser(userId, userPin)) {
            System.out.println("Authentication Successful!");
            atm.showMenu();
        } else {
            System.out.println("Authentication Failed. Exiting...");
        }
    }

    private boolean authenticateUser(String userId, String userPin) {
        return userId.equals(USER_ID) && userPin.equals(USER_PIN);
    }

    private void showMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    View_Balance();
                    break;
                case 2:
                    Withdraw();
                    break;
                case 3:
                    Deposit();
                    break;
                case 4:
                    Transfer();
                    break;
                case 5:
                    Transaction_History();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private void View_Balance() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("Current Balance: " + df.format(balance));
    }

    private void Withdraw() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
            logTransaction("Withdrawal", amount);
        }
    }

    private void Deposit() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();

        balance += amount;
        System.out.println("Deposit successful. Updated balance: " + balance);
        logTransaction("Deposit", amount);
    }

    private void Transfer() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter recipient's account number: ");
        String Account_Number = sc.nextLine();

        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();

        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Transfer successful. Remaining balance: " + balance);
            logTransaction("Transfer", amount);
        }
    }

    private void Transaction_History() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    private void logTransaction(String type, double amount) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String transaction = type + " of " + df.format(amount) + " logged.";
        transactionHistory.add(transaction);
    }
}

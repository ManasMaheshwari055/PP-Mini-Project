import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public Account(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void editBalance(double balance){
        this.balance = balance;
    }
}

class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void transferAmount(int accountNumber1, int accountNumber2, double amount){
        for(Account account : accounts){
            if(amount >= 0.0){
                if(account.getAccountNumber() == accountNumber1){
                    if(account.getBalance() >= amount){
                        double balance1 = account.getBalance();
                        balance1 -= amount;
                        account.editBalance(balance1);
                    }
                    else{
                        System.out.println("\nInsufficient Balance.\n");
                    }
                }
                if(account.getAccountNumber() == accountNumber2){
                    if(account.getBalance() >= amount){
                        double balance2 = account.getBalance();
                        balance2 += amount;
                        account.editBalance(balance2);
                        System.out.println("\nTransaction Successful.\n");
                    }
                }
            }
            else{
                System.out.println("\n Invalid Amount. \n");
            }
        }
    }

    public void displayAllAccounts() {
        for (Account account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolder());
            System.out.println("Balance: Rs." + account.getBalance());
            System.out.println("------------------------");
        }
    }
}

public class BankManagementSystem{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("Bank Management System");
            System.out.println("1. Add Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Amount");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    int accountNumber = sc.nextInt();
                    System.out.print("Enter Account Holder Name: ");
                    String accountHolder = sc.next();
                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    Account newAccount = new Account(accountNumber, accountHolder, balance);
                    bank.addAccount(newAccount);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int depositAccountNumber = sc.nextInt();
                    Account depositAccount = bank.findAccount(depositAccountNumber);
                    if (depositAccount != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = sc.nextDouble();
                        depositAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int withdrawAccountNumber = sc.nextInt();
                    Account withdrawAccount = bank.findAccount(withdrawAccountNumber);
                    if (withdrawAccount != null) {
                        System.out.print("Enter Withdraw Amount: ");
                        double withdrawAmount = sc.nextDouble();
                        withdrawAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                
                case 4:
                    System.out.print("Enter your Account Number: ");
                    int accountNumber1 = sc.nextInt();
                    System.out.print("Enter recievers Account Number: ");
                    int accountNumber2 = sc.nextInt();
                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    bank.transferAmount(accountNumber1, accountNumber2, amount);

                case 5:
                    bank.displayAllAccounts();
                    break;

                case 6:
                    System.out.println("Thank you for using the Bank Management System.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
    }
}
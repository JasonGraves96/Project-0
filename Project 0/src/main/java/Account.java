package src.main.java;

import java.util.Random;

public class Account {
    private String name;
    private String email;
    private String password;
    private int accountNumber;
    private float balance;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        Random rand = new Random();
        this.accountNumber = rand.nextInt();
        this.balance = 0;

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPin(String password) {
        this.password = password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

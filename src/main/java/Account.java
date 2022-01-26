
//This was made mostly irrelevant after implementing all the JDBC and SQL stuff
//Simple POJO
//Originally this program created Account objects after registration
//It's still here out of fear of breaking my program without it and running out of time to fix
public class Account {
    private String name;
    private String email;
    private String password;
    private int accountNumber;
    private float balance;

    public Account(String name, String email, String password, int accountNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = 0;

    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Bank {//you need to find a way to get the account number in here
    public static void insideTheBank(CustomArrayList<Account> active, int accountNumber, Connection TheLegendOfZeldaALinkToThePast) {
        System.out.println("You have successfully logged in!\nyour account is");
        ResultSet rs = null;//rs is for the Accounts table
        ResultSet ls = null;//ls is for the TransactionHistory table
        
        Scanner scnr = new Scanner(System.in);
        Boolean done = false;
        int choice;
        do{
            //running this inside the loop so it refreshes after every transaction
            try {
                Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
                rs = lookup.executeQuery("SELECT *\n" +
                        "FROM test_purposes_only.Accounts\n" +
                        "WHERE id = '"+accountNumber+"';\n");

                ls = lookup.executeQuery("SELECT *\n" +
                        "FROM test_purposes_only.TransactionHistory\n" +
                        "WHERE AccountNumber = '"+accountNumber+"';\n");


                    if(rs.next())
                        accountNumber = rs.getInt("id");

            } catch (SQLException e) {
                e.printStackTrace();
            }

            
            System.out.println("\nPlease select an option\n" +
                    "1- Deposit Money\n" +
                    "2- Withdraw Money\n" +
                    "3- View Transaction History\n" +
                    "4- View Account Information\n" +
                    "5- Transfer Money\n" +
                    "6- Delete Account\n" +
                    "0- Secrets?");
            choice = scnr.nextInt();
            switch(choice){
                case 0:
                    //secrets
                    break;
                case 1:
                    //deposit money
                    deposit(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    break;
                case 2:
                    withdraw(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    //withdraw money
                    break;
                case 3:
                    viewHistory(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    //view transaction history. Second table? send ID and a string of what happened
                    break;
                case 4:
                    viewInformation(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    //view account information
                    break;
                case 5:
                    transfer(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    //transfer money
                    break;
                case 6:
                    delete(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    //delete account
                    break;
                default:
                    break;
            }
        }while(done == false);
    }


    private static void deposit(Connection TheLegendOfZeldaALinkToThePast,ResultSet rs,ResultSet ls, Scanner scnr, int accountNumber){

        System.out.println("How much would you like to deposit?");
        double moneyComingIn = scnr.nextDouble();
        double balance = 0;
        try {
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '"+accountNumber+"';\n");
            if(rs.next())
            balance = rs.getDouble("Balance");
            double result = moneyComingIn + balance;
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            ResultSet newBalance = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                    "SET Balance="+result+
                    "WHERE id="+accountNumber+";\n");
            System.out.println("You have deposited "+moneyComingIn+"$");

           // System.out.println("yay your code got here and your new balance is"+newBalance.getDouble("Balance"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transactionWriter(TheLegendOfZeldaALinkToThePast,rs,"Deposited", accountNumber, moneyComingIn);
    }
    private static void withdraw(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        System.out.println("How much would you like to withdraw?");
        double moneyGoingOut = scnr.nextDouble();
        double balance = 0;
        try {
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '"+accountNumber+"';\n");
            if(rs.next())
                balance = rs.getDouble("Balance");
            double result = balance - moneyGoingOut;
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            ResultSet newBalance = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                    "SET Balance="+result+
                    "WHERE id="+accountNumber+";\n");
            System.out.println("You have withdrew "+moneyGoingOut+"$");
           // System.out.println("Your new balance is"+newBalance.getDouble("Balance"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transactionWriter(TheLegendOfZeldaALinkToThePast,rs,"Withdrew", accountNumber, moneyGoingOut);

    }
    private static void viewHistory(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        CustomArrayList<String> history = new CustomArrayList<String>();
        try {
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.TransactionHistory\n" +
                    "WHERE AccountNumber = 734278324;");
            while(rs.next()){
                history.add(rs.getString("Transaction"));

            }
            for(int i=0;i<history.size();i++)
                System.out.println(history.get(i));


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private static void viewInformation(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){

    }
    private static void transfer(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){


    }
    private static void delete(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){

    }
    private static void transactionWriter(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs,String transactionType, int accountNumber, double amount){
        String result = null;
        java.util.Date date = new java.util.Date();
        //every time something happens have this write something to that table
        result = "You "+transactionType+" "+amount+"$ on "+date.toString()+" at "+java.time.LocalTime.now();


        Statement lookup = null;
        try {
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("INSERT INTO test_purposes_only.TransactionHistory " +
                    "(AccountNumber, `Transaction`) " +
                    "VALUES("+accountNumber+", '"+result+"');");

        } catch (SQLException e) {
            e.printStackTrace();
        }





    }

}

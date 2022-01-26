import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Bank {
    static DecimalFormat df = new DecimalFormat("#.##");//this is to make sure outputs are only 2 decimal places

    //this code runs once you successfully log into the bank
    public static void insideTheBank(CustomArrayList<Account> active, int accountNumber, Connection TheLegendOfZeldaALinkToThePast) {
        System.out.println("You have successfully logged in!\n");
        ResultSet rs = null;//rs is for the Accounts table
        ResultSet ls = null;//ls is for the TransactionHistory table
        
        Scanner scnr = new Scanner(System.in);
        Boolean done = false;//don't believe this ever actually comes into play because I use return statements to exit this class.. The idea is when the user is done inside the bank to set this to true
        String secrets = "";//for the secret changing text
        int choice=-1;//users selection, it is a negative by default for the sake of my valid input checks
        do{
            //running the connection stuff inside the loop so it refreshes the data after every transaction
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

            //menu output
            System.out.println("\nPlease select an option\n" +
                    "1- Deposit Money\n" +
                    "2- Withdraw Money\n" +
                    "3- View Transaction History\n" +
                    "4- View Account Information\n" +
                    "5- Transfer Money\n" +
                    "6- Delete Account\n" +
                    "7- Log Out\n" +
                    "0-"+secrets);

            //checks for valid selections
            while(choice<0||choice>7)
            try{
                choice = scnr.nextInt();
                if(choice<0||choice>7)
                    System.out.println("Numbers between 0-7 please.");
            }catch(InputMismatchException ime){
                System.out.println("Numbers ONLY please!");
                choice = -1;
                scnr.nextLine();
            }

            //switch statement runs whatever the user chose
            switch(choice){
                case 0://secret choice
                    boolean winner = true;
                    if(secrets.equals("")){
                        System.out.println("Why'd you pick 0? There's no SECRETS here...");
                        secrets = " SECRETS???";
                        Art.pauser(3000);
                    }else
                       winner = Secrets.rollin(TheLegendOfZeldaALinkToThePast,rs,ls,accountNumber);
                    if(winner==false) {
                        return;
                    }
                    choice = -1;
                    break;
                case 1://deposit money
                    deposit(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    choice = -1;
                    break;
                case 2://withdraw money
                    withdraw(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    choice = -1;
                    break;
                case 3://view transaction history
                    viewHistory(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    choice = -1;
                    break;
                case 4://view account information
                    viewInformation(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    choice = -1;
                    break;
                case 5://transfer money
                    transfer(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    choice = -1;
                    break;
                case 6://deletes account. then returns back to the login menu
                    delete(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
                    choice = 7;
                    return;
                case 7:
                    //logs out
                    System.out.println("Thank you for choosing Revature Bank!\n" +
                            "See you again soon!");
                    Art.pauser(150);
                    return;
                default://don't think the default can be triggered. But it'll set the choice back to -1 just in case it somehow is
                    choice = -1;
                    break;
            }
        }while(done == false);
    }

    private static void deposit(Connection TheLegendOfZeldaALinkToThePast,ResultSet rs,ResultSet ls, Scanner scnr, int accountNumber){
        System.out.println("How much would you like to deposit?");
        double moneyComingIn = -1;//deposit ammount
        while(moneyComingIn<0)
        try{
            moneyComingIn = scnr.nextDouble();
            if(moneyComingIn<0)//checks for negative value
                System.out.println("You can't deposit negative money");
        }catch(InputMismatchException ime){//checks for bad data
            System.out.println("NUMBERS only please.");
            scnr.nextLine();
            moneyComingIn= -1;
        }

        double balance = 0;//initializes variable for money already in the account
        try {//accesses the database to get the balance
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '"+accountNumber+"';\n");
            if(rs.next())
            balance = rs.getDouble("Balance");
            df.setRoundingMode(RoundingMode.DOWN);
            double result = Double.parseDouble(df.format(moneyComingIn + balance));//calculates the new balance
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            //sets the new balance in the database
            ResultSet newBalance = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                    "SET Balance="+result+
                    "WHERE id="+accountNumber+";\n");
            System.out.println("You have deposited $"+df.format(moneyComingIn)+".");//outputs the result to user

           // System.out.println("yay your code got here and your new balance is"+newBalance.getDouble("Balance"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        transactionWriter(TheLegendOfZeldaALinkToThePast,rs,"Deposited", accountNumber, moneyComingIn);//calls the transaction writer so user can check it later
    }

    private static void withdraw(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        System.out.println("How much would you like to withdraw?");
        double moneyGoingOut = -1;//amount to withdraw
        double balance = 0;//existing balance
        double result = -1;//after calcualtions amount of money

        while(moneyGoingOut<0&&result<0)//checks for positive withdrawl
        try{
            moneyGoingOut= scnr.nextDouble();
            if(moneyGoingOut<0)
                System.out.println("You can't withdraw negative money");
        }catch(InputMismatchException ime){//checks for bad data
            System.out.println("NUMBERS only please.");
            scnr.nextLine();
            moneyGoingOut = -1;
        }

        try {//selects the table
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '"+accountNumber+"';\n");

            if(rs.next()) {
                balance = rs.getDouble("Balance");//gets the amount in account
            }
            df.setRoundingMode(RoundingMode.DOWN);
            result = Double.parseDouble(df.format(balance - moneyGoingOut));
           if(result<0){//checks for negative balance
               System.out.println("That would overdraw your account.");//if negative
           }else{//if positive it sets new value
               lookup = TheLegendOfZeldaALinkToThePast.createStatement();
               ResultSet newBalance = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                       "SET Balance="+result+
                       "WHERE id="+accountNumber+";\n");
               System.out.println("You have withdrew $"+df.format(moneyGoingOut)+".");
               transactionWriter(TheLegendOfZeldaALinkToThePast,rs,"Withdrew", accountNumber, moneyGoingOut);
           }
           // System.out.println("Your new balance is"+newBalance.getDouble("Balance"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void viewHistory(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        CustomArrayList<String> history = new CustomArrayList<String>();//this is the required usage of the custom ArrayList
        try {
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.TransactionHistory\n" +
                    "WHERE AccountNumber = "+accountNumber+";");//selects the table
            while(rs.next()){
                history.add(rs.getString("Transaction"));//this goes through what has been selected and adds all instances of matching account id to an arraylist
            }
            for(int i=0;i<history.size();i++)
                System.out.println(history.get(i));//outputs all instances of this account id in the transaction table
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//end viewHistory

    private static void viewInformation(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        int id =accountNumber;//this method is simple
        String name ="";//it gets values from the database for this account
        double balance =0;//then displays them
        String email ="";
        try {
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '" + accountNumber + "';\n");
            if (rs.next()){
                name = rs.getString("Name");
               balance = rs.getDouble("Balance");
                email = rs.getString("Email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Hi "+name+"!\n"
                        +"Your account number is: "+id+"\n"
                        +"You have $"+Double.parseDouble(df.format(balance))+" in this bank\n"
                        +"Your email address is "+email+"\n"
                        +"Please contact system admin for your password\n"
                        +"Thank you for choosing Revature Bank!"
                );
    }//end viewInformation

    private static void transfer(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        CustomArrayList<Integer> accountList = new CustomArrayList<Integer>();//two different arraylists
        CustomArrayList<String> nameList = new CustomArrayList<String>();//one for account id's and the other for names
        double oldBalance = 0;
        double newBalance = 0;
        int choice = -1;
        try {
            Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n");

            while(rs.next()){//adds names and id's to seperate arrays, names and arrays should match to one another's index
                accountList.add(rs.getInt("id"));
                nameList.add(rs.getString("Name"));
            }
            System.out.println("What account would you like to transfer money to?");
            for(int i=0;i<nameList.size();i++){

                   System.out.println(i+" for: "+nameList.get(i)+" at account #"+accountList.get(i));
            }//prints out every account

            //number checker
            while(choice<0||choice>accountList.size()-1)
            try{
                choice = scnr.nextInt();
                if(choice<0||choice>accountList.size()-1)
                    System.out.println("Only use numbers 0-"+(accountList.size()-1));
            }catch(InputMismatchException ime){
                System.out.println("Numbers ONLY please!");
                scnr.nextLine();
                choice = -1;
            }

            //asking the user how much to transfer over
            System.out.println("How much would you like to give them?");
            df.setRoundingMode(RoundingMode.DOWN);
            Double money = -0.01;
            while(money<0)
                try{
                    money = Double.parseDouble(df.format(scnr.nextDouble()));
                    if(money<0) {//this is a little goof if you try to transfer negative money. It calls you a thief then draws a picture of a cop
                        System.out.println("That's called theft, and I won't stand for it!!");
                        Art.pauser(2000);
                        Art.police();
                        Art.pauser(4000);
                        return;
                    }
                }catch(InputMismatchException ime){
                    System.out.println("NUMBERS ONLY please!!!");
                    scnr.nextLine();
                    money=-0.01;
                }
            //below is all database stuff. Needs to take money out of this account, then adds it to another account
            //not sure if every query is necessary, but this is how I got it to work, and it's too late to mess with it now
            int recievingAccount = accountList.get(choice);
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '"+recievingAccount+"';\n");
            if(rs.next())
             oldBalance = rs.getDouble("Balance");
             newBalance = oldBalance + money;
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                            "SET Balance="+newBalance+"\n"+
                    "WHERE id="+recievingAccount+";\n");

            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '"+accountNumber+"';\n");
            if(rs.next())
            newBalance = (rs.getDouble("Balance")-money);
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            ResultSet nnnrs = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                    "SET Balance="+newBalance+"\n"+
                    "WHERE id="+accountNumber+";");

            transactionWriter(TheLegendOfZeldaALinkToThePast,rs,"Gave away ", accountNumber, money);//calls transaction writer
        }catch(SQLException e){
            e.printStackTrace();
        }
    }//end transfer

    public static void delete(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, Scanner scnr, int accountNumber){
        Statement lookup = null;
        try {//deleting is simple. Calls 1 query
           lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("DELETE" +
                    " FROM test_purposes_only.Accounts\n" +
                    "WHERE id = '" + accountNumber + "';\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Your account has been terminated...");
    }

    public static void transactionWriter(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs,String transactionType, int accountNumber, double amount){
        String result = null;
        java.util.Date date = new java.util.Date();
        //every time something happens have this write something to that table
        df.setRoundingMode(RoundingMode.DOWN);
        result = "You "+transactionType+" $"+Double.parseDouble(df.format(amount))+" on "+date.toString()+" at "+java.time.LocalTime.now();

        Statement lookup = null;
        try {
            lookup = TheLegendOfZeldaALinkToThePast.createStatement();
            rs = lookup.executeQuery("INSERT INTO test_purposes_only.TransactionHistory " +
                    "(AccountNumber, `Transaction`) " +
                    "VALUES("+accountNumber+", '"+result+"');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//end transactionWriter

}//end Bank class.. In hindsight this could have been split into multiple classes so this one isn't so long.
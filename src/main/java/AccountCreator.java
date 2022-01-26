import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreator {
    public static Account signUp(Connection TheLegendOfZeldaALinkToThePast) {
        //defines the fields
        Scanner scnr = new Scanner(System.in);
        String name = null;
        String email;
        String password;
        Integer accountNumber = 0;


        //asks for the name. then runs through the cleaner to get it in First Last format
        System.out.println("What is your name? First and last separated by a space.");
            do{
                try{
                    name = scnr.nextLine();
                    name = nameCleaner(name);
                }catch(ArrayIndexOutOfBoundsException aioobe){//this triggers if they either don't input anything or if there are no spaces
                 System.out.println("First AND Last names seperated by a space please!");
                name = null;
                }
            }while(name==null);
        System.out.println("You entered: "+name);


        //asks for the email. checks the format to see if it conforms to xxx@xxx.xxx
        System.out.println("What is your email?");
        String result=null;
        boolean checks = false;
        do{
           email = scnr.nextLine();
           try {
               Statement emailLookup = TheLegendOfZeldaALinkToThePast.createStatement();
                ResultSet rs = emailLookup.executeQuery("SELECT *\n" +
                        "FROM test_purposes_only.Accounts\n" +
                        "WHERE Email = '"+email+"';\n");
                    if(rs.next())
                        result = rs.getString("Email");
           } catch (SQLException e) {
               e.printStackTrace();
           }
            if(emailChecker(email)==false){
                System.out.println("Email invalid. Please try again.");
                checks = false;
            }else if(email.equals(result)==true) {
                System.out.println("There is already an account with that email.\nPlease Try again.");
                checks = false;
            }else
                checks = true;
        }while(checks == false);


        //asks for a password. scans the line twice because it skips otherwise
        System.out.println("What will your password be? Input anything, just make sure you remember it");

        password = scnr.nextLine();


        //randomly generates an account number
        accountNumber = accountNumberGenerator();


        //creates the Account object. NOTE I put this in before the database.. It is sort of redundant now
        Account newAccount = new Account(name, email, password, accountNumber);
        System.out.println("Your account has been created.\nWelcome to Revature Bank!\n");
        Art.pauser(300);

        //this puts the info just entered into our database
        try {
            Statement TheSQLStatement = TheLegendOfZeldaALinkToThePast.createStatement();
            String TheSQLQuery = "INSERT INTO test_purposes_only.Accounts\n" +
                    "(id, `Name`, Balance, Email, Password)\n" +
                    "VALUES("+newAccount.getAccountNumber()+", '"+newAccount.getName()+"'" +
                    ", 0, '"+newAccount.getEmail()+"', '"+newAccount.getPassword()+"');\n";

            TheSQLStatement.executeUpdate(TheSQLQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newAccount;
    }

    public static String nameCleaner(String name) {
        String[] nameArray;
        nameArray = name.split(" ", 2);//searches for the space
        String first = nameArray[0];
        String last  = nameArray[1];//this first last bit is a relic from when I was making objects instead up updating a database. Switch from first and last fields to just one name field
        first = first.replaceAll("[^a-zA-Z]+", "");//removes special characters from both first and last
        last = last.replaceAll("[^a-zA-Z]+", "");
        return first + " " + last;
    }

    public static boolean emailChecker(String email){
        Pattern daMail = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");//email format checker is characters + @symbol + characters + . + characters
        Matcher match = daMail.matcher(email);
        boolean matchCheck = match.matches();//gets a true/false depending if the user's string matches the pattern outlined above
        return matchCheck;
    }
    public static int accountNumberGenerator(){
        Random rand = new Random();//randomly assigns a HUGE number for account id purposes
        int result = Math.abs(rand.nextInt());//only want positive numebrs for this
        return result;
    }
}

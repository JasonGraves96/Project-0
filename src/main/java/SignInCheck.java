
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SignInCheck {
    public static int signIn(CustomArrayList<Account> cal, Connection TheLegendOfZeldaALinkToThePast){
    // ask for email. check if it is an object email address.. ask for password, check if it is correct
        Scanner scnr = new Scanner(System.in);
        String userEmail=null;
        String userPassword="";
        int accountNumber=-1;
        boolean goodSoFar=false;
        String theirRealPassword = "  ";

        while(goodSoFar == false) {
            System.out.println("What is your email address?");
            userEmail = scnr.next();
            /*
            for (int i = 0; i < cal.size(); i++) {
                if (cal.get(i).getEmail().equals(userEmail)) {
                    goodSoFar = true;
                    index = i;
                }
            }OLD USED FOR CYCLING THROUGH ARRAYLIST*/
            String result = "Email not found";
            try {
                Statement emailLookup = TheLegendOfZeldaALinkToThePast.createStatement();
                ResultSet rs = emailLookup.executeQuery("SELECT *\n" +
                        "FROM test_purposes_only.Accounts\n" +
                        "WHERE Email = '"+userEmail+"';\n");
                if(rs.next()) {
                    result = rs.getString("Email");
                    goodSoFar = true;
                    theirRealPassword = rs.getString("Password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (goodSoFar == false)
                System.out.println("Sorry we can't find that email. Try again.");
        }

            userPassword=scnr.nextLine();
        do{
            System.out.println("What is your password?");
            userPassword = scnr.nextLine();
            if(userPassword.equals(theirRealPassword)== false)
                System.out.println("That password is wrong. Try again.");
            else {

                Statement accountLookup = null;
                try {
                    accountLookup = TheLegendOfZeldaALinkToThePast.createStatement();
                    ResultSet rs = accountLookup.executeQuery("SELECT *\n" +
                            "FROM test_purposes_only.Accounts\n" +
                            "WHERE Email = '"+userEmail+"';\n");

                    if(rs.next())
                    accountNumber = rs.getInt("id");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }while(userPassword.equals(theirRealPassword)== false);

    return accountNumber;//returning the account number so when we're inside the bank it knows what account to access
    }
}
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

//project 0 To Do

//tuesday to do
//view account information
//transfer money
//2 DECIMALS!!!!!
//check math
//review project details to double check you're not missing anything

public class MainEntryPoint {
    public static void main(String[] args){
        //defining variables
        CustomArrayList<Account> cal = new CustomArrayList<Account>();
        int inOrUp;
        //sets up database connection

        Connection theHookup = DatabaseConnectionManager.startConnection();
        //runs the ascii art welcome screen
        Art.welcome();

            //asks if the user wants to sign up or sign in
            do {
                inOrUp = signInOrUp();
                int dex = -1;
                if (inOrUp == 1) {
                    //adds the Account item created with AccountCreator to our cal (custom array list)
                    cal.add(AccountCreator.signUp(theHookup));
                    //System.out.println(cal.get(0).getName()); just used this to test if it saved
                } else if (inOrUp == 2) {
                    dex = SignInCheck.signIn(cal,theHookup);//dex is the account number we're using
                    if (dex >= 0){
                        Bank.insideTheBank(cal,dex,theHookup);//this calls the bank, passes it the account number and database connection. cal is an arraylist, not being used
                    }
                }
                else
                    System.out.println("only 1 or 2 please");
            } while ((inOrUp != 1) || (inOrUp != 2));
    }
    public static int signInOrUp(){
        Scanner scnr = new Scanner(System.in);
        int result;
        System.out.println("Please Select an option below\n" +
                "1: To Sign Up\n" +
                "2: To Sign In");
        try {
            result = scnr.nextInt();
        }catch(InputMismatchException ime){
            result = -1;
        }
        return result;
    }
}
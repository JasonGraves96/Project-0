package src.main.java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainEntryPoint {
    public static void main(String[] args){
        //defining variables
        CustomArrayList<Account> cal = new CustomArrayList<Account>();
        int inOrUp;
        //runs the ascii art welcome screen
        welcome();

            //asks if the user wants to sign up or sign in
            do {
                inOrUp = signInOrUp();
                if (inOrUp == 1) {
                    //adds the Account item created with AccountCreator to our cal (custom array list)
                    cal.add(AccountCreator.signUp());
                    //System.out.println(cal.get(0).getName()); just used this to test if it saved
                }
                else if (inOrUp == 2)
                    if(SignInCheck.signIn(cal)==true)
                        insideTheBank();
                else
                    System.out.println("only 1 or 2 please");
            } while ((inOrUp != 1) || (inOrUp != 2));

    }



    public static void welcome(){
        System.out.println(" _______   ________  __     __   ______   ________  __    __  _______   ________ ");
        pauser(100);
        System.out.println("/       \\ /        |/  |   /  | /      \\ /        |/  |  /  |/       \\ /        |");
        pauser(100);
        System.out.println("$$$$$$$  |$$$$$$$$/ $$ |   $$ |/$$$$$$  |$$$$$$$$/ $$ |  $$ |$$$$$$$  |$$$$$$$$/ ");
        pauser(100);
        System.out.println("$$ |__$$ |$$ |__    $$ |   $$ |$$ |__$$ |   $$ |   $$ |  $$ |$$ |__$$ |$$ |__    ");
        pauser(100);
        System.out.println("$$    $$< $$    |   $$  \\ /$$/ $$    $$ |   $$ |   $$ |  $$ |$$    $$< $$    |  ");
        pauser(100);
        System.out.println("$$$$$$$  |$$$$$/     $$  /$$/  $$$$$$$$ |   $$ |   $$ |  $$ |$$$$$$$  |$$$$$/    ");
        pauser(100);
        System.out.println("$$ |  $$ |$$ |_____   $$ $$/   $$ |  $$ |   $$ |   $$ \\__$$ |$$ |  $$ |$$ |_____ ");
        pauser(100);
        System.out.println("$$ |  $$ |$$       |   $$$/    $$ |  $$ |   $$ |   $$    $$/ $$ |  $$ |$$       |");
        pauser(100);
        System.out.println("$$/   $$/ $$$$$$$$/     $/     $$/   $$/    $$/     $$$$$$/  $$/   $$/ $$$$$$$$/ ");
        pauser(100);
        System.out.println("<------------------------------------------------------------------------------->");
        pauser(750);
        System.out.println("                   _______    ______   __    __  __    __                       ");
        pauser(100);
        System.out.println("                  /       \\  /      \\ /  \\  /  |/  |  /  |                       ");
        pauser(100);
        System.out.println("                  $$$$$$$  |/$$$$$$  |$$  \\ $$ |$$ | /$$/                        ");
        pauser(100);
        System.out.println("                  $$ |__$$ |$$ |__$$ |$$$  \\$$ |$$ |/$$/                         ");
        pauser(100);
        System.out.println("                  $$    $$< $$    $$ |$$$$  $$ |$$  $$<                          ");
        pauser(100);
        System.out.println("                  $$$$$$$  |$$$$$$$$ |$$ $$ $$ |$$$$$  \\                         ");
        pauser(100);
        System.out.println("                  $$ |__$$ |$$ |  $$ |$$ |$$$$ |$$ |$$  \\                        ");
        pauser(100);
        System.out.println("                  $$    $$/ $$ |  $$ |$$ | $$$ |$$ | $$  |                       ");
        pauser(100);
        System.out.println("                  $$$$$$$/  $$/   $$/ $$/   $$/ $$/   $$/                        ");
        System.out.println("                                                                     -Jason Graves");
        pauser(750);
        System.out.println("\n\nWelcome to Revature Bank!");


    }

    public static void pauser(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private static void insideTheBank() {
        System.out.println("You made it inside the bank!! Huzzah!");
        System.exit(1);
    }
}
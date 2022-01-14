package src.main.java;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreator {
    public static Account signUp() {
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
       do{
           email = scnr.next();
           boolean checks = false;
            if(emailChecker(email)==false)
                System.out.println("Email invalid. Try again.");
       }while(emailChecker(email)==false);


        //asks for a password. scans the line twice because it skips otherwise
        System.out.println("What will your password be? Input anything, just make sure you remember it");
        password = scnr.nextLine();
        password = scnr.nextLine();


        //randomly generates an account number
        accountNumber = accountNumberGenerator();


        //creates the Account object
        Account newAccount = new Account(name, email, password, accountNumber);
        System.out.println("Your account has been created.\nWelcome to Revature Bank!\n");
        MainEntryPoint.pauser(300);

        return newAccount;
    }

    public static String nameCleaner(String name) {
        String[] nameArray;
        nameArray = name.split(" ", 2);
        String first = nameArray[0];
        String last  = nameArray[1];
        first = first.replaceAll("[^a-zA-Z]+", "");
        last = last.replaceAll("[^a-zA-Z]+", "");
        return first + " " + last;
    }

    public static boolean emailChecker(String email){
        Pattern daMail = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher match = daMail.matcher(email);
        boolean matchCheck = match.matches();
        return matchCheck;
    }
    public static int accountNumberGenerator(){
        Random rand = new Random();
        int result = rand.nextInt();
        //come back to this and have it check your array for matching values
        return result;
    }
}

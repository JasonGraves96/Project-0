package src.main.java;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountCreator {
    public static void signUp() {


        // generate an account number
        // set balance to 0
        Scanner scnr = new Scanner(System.in);
        String name;
        String email;
        String password;
        System.out.println("What is your name? First and last separated by a space.");


        name = scnr.nextLine();
        name = nameCleaner(name);
        System.out.println(name);

        System.out.println("What is your email?");


       do{
           email = scnr.next();
           boolean checks = false;
            if(emailChecker(email)==false)
                System.out.println("Email invalid. Try again.");

       }while(emailChecker(email)==false);

        System.out.println("What will your password be? Input anything, just make sure you remember it");

        password = scnr.nextLine();



    }

    public static String nameCleaner(String name){
        String[] nameArray;
        nameArray = name.split(" ",2);
        String first = nameArray[0];
        String last = nameArray[1];
        first = first.replaceAll("[^a-zA-Z]+", "");
        last = last.replaceAll("[^a-zA-Z]+", "");
        return first+" "+last;
    }

    public static boolean emailChecker(String email){
        Pattern daMail = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher match = daMail.matcher(email);
        boolean matchCheck = match.matches();
        return matchCheck;
    }
}

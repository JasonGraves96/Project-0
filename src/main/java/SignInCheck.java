package src.main.java;

import java.util.Scanner;

public class SignInCheck {
    public static boolean signIn(CustomArrayList<Account> cal){
    // ask for email. check if it is an object email address.. ask for password, check if it is correct
        Scanner scnr = new Scanner(System.in);
        String userEmail;
        String userPassword="";
        int index=0;
        boolean goodSoFar=false;

        while(goodSoFar == false) {
            System.out.println("What is your email address?");
            for (int i = 0; i < cal.size(); i++) {
                userEmail = scnr.next();
                if (cal.get(i).getEmail().equals(userEmail)) {
                    goodSoFar = true;
                    index = i;
                }
            }
            if (goodSoFar == false)
                System.out.println("Sorry we can't find that email. Try again.");
        }
            userPassword=scnr.nextLine();
        do{
            System.out.println("What is your password?");
            userPassword = scnr.nextLine();

            if(userPassword.equals(cal.get(index).getPassword())== false)
                System.out.println("That password is wrong. Try again.");

        }while(userPassword.equals(cal.get(index).getPassword())== false);

    return true;
    }
}

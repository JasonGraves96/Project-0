package src.main.java;

import src.main.java.AccountCreator;

import java.util.Scanner;

public class MainEntryPoint {
    public static void main(String[] args){

        int inOrUp;
        welcome();

            do{
                inOrUp = signInOrUp();
                if(inOrUp == 1)
                    AccountCreator.signUp();
                else if(inOrUp == 2)
                    signIn();
                else
                    System.out.println("only 1 or 2");
            }while((inOrUp != 1)||(inOrUp !=2) );

    }

    public static void welcome(){
        System.out.println(" _______   ________  __     __   ______   ________  __    __  _______   ________ ");
        pauser(150);
        System.out.println("/       \\ /        |/  |   /  | /      \\ /        |/  |  /  |/       \\ /        |");
        pauser(150);
        System.out.println("$$$$$$$  |$$$$$$$$/ $$ |   $$ |/$$$$$$  |$$$$$$$$/ $$ |  $$ |$$$$$$$  |$$$$$$$$/ ");
        pauser(150);
        System.out.println("$$ |__$$ |$$ |__    $$ |   $$ |$$ |__$$ |   $$ |   $$ |  $$ |$$ |__$$ |$$ |__    ");
        pauser(150);
        System.out.println("$$    $$< $$    |   $$  \\ /$$/ $$    $$ |   $$ |   $$ |  $$ |$$    $$< $$    |  ");
        pauser(150);
        System.out.println("$$$$$$$  |$$$$$/     $$  /$$/  $$$$$$$$ |   $$ |   $$ |  $$ |$$$$$$$  |$$$$$/    ");
        pauser(150);
        System.out.println("$$ |  $$ |$$ |_____   $$ $$/   $$ |  $$ |   $$ |   $$ \\__$$ |$$ |  $$ |$$ |_____ ");
        pauser(150);
        System.out.println("$$ |  $$ |$$       |   $$$/    $$ |  $$ |   $$ |   $$    $$/ $$ |  $$ |$$       |");
        pauser(150);
        System.out.println("$$/   $$/ $$$$$$$$/     $/     $$/   $$/    $$/     $$$$$$/  $$/   $$/ $$$$$$$$/ ");
        pauser(150);
        System.out.println("\n");
        pauser(1000);
        System.out.println("                   _______    ______   __    __  __    __                       ");
        pauser(150);
        System.out.println("                  /       \\  /      \\ /  \\  /  |/  |  /  |                       ");
        pauser(150);
        System.out.println("                  $$$$$$$  |/$$$$$$  |$$  \\ $$ |$$ | /$$/                        ");
        pauser(150);
        System.out.println("                  $$ |__$$ |$$ |__$$ |$$$  \\$$ |$$ |/$$/                         ");
        pauser(150);
        System.out.println("                  $$    $$< $$    $$ |$$$$  $$ |$$  $$<                          ");
        pauser(150);
        System.out.println("                  $$$$$$$  |$$$$$$$$ |$$ $$ $$ |$$$$$  \\                         ");
        pauser(150);
        System.out.println("                  $$ |__$$ |$$ |  $$ |$$ |$$$$ |$$ |$$  \\                        ");
        pauser(150);
        System.out.println("                  $$    $$/ $$ |  $$ |$$ | $$$ |$$ | $$  |                       ");
        pauser(150);
        System.out.println("                  $$$$$$$/  $$/   $$/ $$/   $$/ $$/   $$/                        ");
        pauser(1000);
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
        System.out.println("Would you like to sign in or sign up? 1 for up 2 for in");
        result = scnr.nextInt();
        return result;
    }

    public static boolean signIn(){
// ask for email. check if it is an object email address.. ask for pin.. check if it is the right pin

        return false;
    }
}

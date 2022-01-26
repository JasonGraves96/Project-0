import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//this is the bonus hidden dice game included
public class Secrets {
    //rollin is like the main game method
    public static boolean rollin(Connection TheLegendOfZeldaALinkToThePast, ResultSet rs, ResultSet ls, int accountNumber){
        boolean win = false;
    System.out.println("you found Grimlar's gambling game!!!");
    Scanner scnr = new Scanner(System.in);
    int choice = -1;
    Art.pauser(1000);
    Art.Grimlar();
    Art.pauser(750);
    System.out.println("Ahoy, matey! ye've found me gamblin' game!\n" +
            "'ere's the deal, we be goin' to play a wee dice game.\n" +
            "If ye win ye get 1,000,000.01 dollarrs!\n" +
            "but if ye lose yer account gets deleted!\n" +
            "an' ye must walk the plank arr, matey!\n");//Grimlar tells the user what is going on
    Art.pauser(1000);
    System.out.println("\nHow do you respond?\n" +
            "1- No Way!\n" +
            "2- I'm in!");
    while(choice<1 || choice>2)//checks for valid inputs
        try{
            choice = scnr.nextInt();
            if(choice<1 || choice>2)
                System.out.println("1 or 2 only..");
        }catch(InputMismatchException ime){
            System.out.println("1 or 2 only..");
            scnr.nextLine();
            choice=-1;
        }
    if(choice ==1){//if user picks no
        System.out.println("Some 'ther time then?\n" +
                "farewell...");
        return true;
    }//method continues if the user picks yes
    System.out.println("Here are the rules:\n" +
            "Both your score and Grimlar's score starts at 0\n" +
            "The object of the game is to be the first to reach 40 points\n" +
            "You and Grimlar take turns\n" +
            "On each turn you or Grimlar will pick a number 2 thorugh 6\n" +
            "You will then roll 2 dice\n" +
            "Your score will have the sum of the two dice added to it, and control will revert to the other player UNLESS:\n" +
            "If a 1 appears on both dice, that player's score is reduced by 25 and it becomes the other players turn\n" +
            "If a 1 appears on only one die, nothing happens and it becomes the other player's turn\n" +
            "If your chosen number appears on one die you get to roll again\n" +
            "Rolling a 1 takes precedence over rolling your chosen number\n" +
            "if your chose number appears on BOTH dice you instantly win\n");//rules of the game


        win = play();//calls play() where the game itself happens
        if(win==true){//checks if you won or not. if so it deposits 1000000.01 to this account, if not it delets and exits to the main login menu
            double moneyBeingGiven = 1000000.01;
            double balance = 0;
            double result = 0;
            try{
                Statement lookup = TheLegendOfZeldaALinkToThePast.createStatement();
                rs = lookup.executeQuery("SELECT *\n" +
                        "FROM test_purposes_only.Accounts\n" +
                        "WHERE id = '"+accountNumber+"';\n");
                if(rs.next())
                    balance = rs.getDouble("Balance");
                result = balance + moneyBeingGiven;
                lookup = TheLegendOfZeldaALinkToThePast.createStatement();
                ResultSet newBalance = lookup.executeQuery("UPDATE test_purposes_only.Accounts\n" +
                        "SET Balance="+result+
                        "WHERE id="+accountNumber+";\n");
            }catch(SQLException e){
                e.printStackTrace();
            }
            //the transactionWriter is throwing an error for some reason so it has been commented out
            //Bank.transactionWriter(TheLegendOfZeldaALinkToThePast,rs,"Won Grimlar's game ", accountNumber,moneyBeingGiven);
        return true;//return is so Bank.java knows if you won or not
        }
        else{//if you lose
            System.out.println("you lose");
            Art.pauser(5000);
            Bank.delete(TheLegendOfZeldaALinkToThePast,rs,ls,scnr,accountNumber);
            return false;//return is so Bank.java knows if you won or not
        }//delete account
    }

    static boolean insta = false;//this is used to check for instant win conditions
    public static boolean play(){//where the game itself happens
        int userScore=0,cpuScore=0,currentNumber = 0,roll1,roll2,turnResult;//variables
        Die sixer = new Die();//creates a Die object
        boolean gameOver = false, turn = true, winner = true;//more variables
        Scanner scnr = new Scanner(System.in);//scanner for number guesser

        do {
            Random rand = new Random();
            if(turn==true)//checks to see whos turn it is
                System.out.println("\nPick a number 2-6");
            else {
                System.out.println("It is Grimlar's turn.");
            }

            if(turn==true) {//if it is your turn
                try {
                    currentNumber = scnr.nextInt();
                }catch(Exception ime) {System.out.println("congrats you broke the program! numbers only next time!!!");
                    try {Thread.sleep(1800);} catch (InterruptedException e) {e.printStackTrace();}};
                while(currentNumber <2 || currentNumber > 6){
                    System.out.println("follow the rules please!");
                    currentNumber = scnr.nextInt();
                }
            }

            else {//if it is Grimlar's turn
                currentNumber = (rand.nextInt(5))+2;
                System.out.println("\nGrimlar guesses "+currentNumber+".\n");
                Art.pauser(600);
            }
            //rolls 2 dice
            roll1 = sixer.Roll();
            sixer.Draw();
            Art.pauser(500);
            roll2 = sixer.Roll();
            sixer.Draw();
            Art.pauser(500);
            turnResult = checkConditions(roll1,roll2,currentNumber);//gets a value which decides what switch case to call
            Art.pauser(200);
            switch(turnResult) {
                case 1://if snake eyes
                    if(turn == true) {//your turn
                        userScore=userScore-25;
                        System.out.println("\nSnake eyes......\n25 points will be subtracted from your score...");
                    }
                    else {//grimlar's turn
                        cpuScore=cpuScore-25;
                        System.out.println("\nSnake eyes!\nKaching!\nNow Grimlar loses 25 points!!!!");
                    }
                    turn = !turn;//changes state of turn
                    break;

                case 2://one die is a 1
                    if(turn==true)
                        System.out.println("\nAw shucks. \nYou rolled a 1.\nUnfortunately no points are gained, and you lose your turn....");
                    else
                        System.out.println("\nNice!\nSince Grimlar rolled a 1, you get your turn back!");
                    turn=!turn;
                    break;

                case 3://one of the chosen numbers was rolled
                    if(turn==true) {
                        userScore = userScore + roll1 + roll2;
                        if(userScore < 40)
                            System.out.println("\nGreat galloping ghosts!?!\nBecause your chosen number was rolled,\nYou get another turn!!!");
                    }
                    else {
                        cpuScore = cpuScore + roll1 + roll2;
                        if(cpuScore < 40)
                            System.out.println("\nOh no?!? \nGrimlar managed to correctly guess one of his rolls.\nMeaning he gets to go again...");
                    }
                    break;

                case 4://both chosen numbers were rolled
                    gameOver= true;
                    if(turn == false)
                        winner = false;

                    if(winner ==true) {//printing out that you won
                        System.out.print("YOU HAVE DONE THE");
                        Art.pauser(300);
                        System.out.print(" I");
                        Art.pauser(300);
                        System.out.print("M");
                        Art.pauser(300);
                        System.out.print("P");
                        Art.pauser(300);
                        System.out.print("O");
                        Art.pauser(300);
                        System.out.print("S");
                        Art.pauser(300);
                        System.out.print("S");
                        Art.pauser(300);
                        System.out.print("I");
                        Art.pauser(300);
                        System.out.print("B");
                        Art.pauser(300);
                        System.out.print("L");
                        Art.pauser(300);
                        System.out.print("E\n");
                        Art.pauser(300);
                        System.out.println("BOTH of your rolls were the guessed number!?!?");
                        insta = true;
                    }else{
                        System.out.println("Bad luck. Grimlar guessed both rolls.... \nIt's all over\n");
                    }
                    break;

                case 5:
                    if(turn==true) {//if neither rolls were a 1 or chosen number
                        userScore = userScore + roll1 + roll2;
                        if(userScore < 40)
                            System.out.println("\nOk. You get your points.\nBut it is now Grimlar's turn...");
                    }

                    else {
                        cpuScore = cpuScore + roll1 + roll2;
                        if(cpuScore<40)
                            System.out.println("\nOk. Grimlar gets his points.\nYou have your chance now..");
                    }
                    turn = !turn;
                    break;
            }
            Art.pauser(500);
            if(userScore >= 40 || cpuScore >=40)//checks scores for win condition
                gameOver = true;
            if(gameOver ==false)//if game is not over, print scores
                printScore(userScore,cpuScore);
        }while(gameOver == false);

        if(winner==false)
        {}//do nothing
        else {
            if(insta == false)
                if (userScore > cpuScore)
                {}//do nothing
                else {
                    winner = false;
                }
        }
        if(winner == true)
            return true;
        else
            return false;

    }//end play

    public static void printScore(int userScore, int cpuScore) {
        if(userScore == cpuScore)
            System.out.println("\n\nYou and Grimlar are currently tied.\n");
        if(userScore > cpuScore)
            System.out.println("\n\nYou are currently beating Grimlar!!!\n");
        else if(userScore < cpuScore) {
            if((cpuScore - userScore) > 35 )
                System.out.println("\n\nGrimlar thinks your attempts are pathetic");
            System.out.println("\n\nYou are currently losing to Grimlar\n");
        }
        Art.pauser(400);
        System.out.println("Your score is:\t"+userScore+"\n");
        System.out.println("Grimlar's:\t\t"+cpuScore+"\n");
        Art.pauser(400);
    }//end printScore

    public static int checkConditions(int roll1, int roll2, int currentNumber) {//this is the logic to see what happens in game
        int result = 0;
        if(roll1 == 1 || roll2 == 1) {
            if(roll1 == 1 && roll2 == 1)
                result = 1;
            else
                result = 2;
        }else {
            if(currentNumber == roll1 || currentNumber == roll2) {
                if(currentNumber == roll1 && currentNumber == roll2) {
                    result = 4;
                }else
                    result = 3;
            }else
                result = 5;
        }
        return result;
    }

    public static void youWon() {
        System.out.println("You Won! Grimlar tips his cap!");
        Art.pauser(1500);
    }
    public static void youLost() {
        System.out.println("");
        System.out.println("It is over... Grimlar has won...\n\n");
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
    }
}



import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class DatabaseConnectionManager {
    public static void Connects() {

/*
        Connection testing = null;
        try {
            //this connects to database
            testing = DriverManager.getConnection(
                    "jdbc:mariadb://testing-database.c5cbc3pw947g.us-east-1.rds.amazonaws.com:3306/test_purposes_only"
                    , "admin"
                    , "aaaaaaaa");

            //this creates our statement
            Statement tests = testing.createStatement();
            Random rand = new Random();
            String mdbStuff = "INSERT INTO test_purposes_only.Accounts\n" +
                    "(id, `First`, `Last`, Balance, Email, Password)\n" +
                    "VALUES(" + rand.nextInt() + ", 'billy', 'bonkrods', 0, 'doesnotexist', 'aa');\n";

            tests.executeUpdate(mdbStuff);


            //everything below gets the string
            ResultSet rs = tests.executeQuery("SELECT *\n" +
                    "FROM test_purposes_only.Accounts\n" +
                    "WHERE Email = 'doesnotexist';\n");
            //sql query

            String result = null;

            if (rs.next())
                result = rs.getString("Email");
            System.out.println(result);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

 THIS METHOD NO LONGER DOES ANYTHING
 WAS USED TO TEST
 */
    }//this is never used

     static Connection startConnection(){//main connection method
         Connection TheLegendOfZeldaALinkToThePast = null;
         File myFile = new File("connectionString.properties");//reads the .properties file that will not be uploaded to github for security reasons
         try {
             Scanner scnr = new Scanner(myFile);//bounds a scanner to the file
             String connectionInfo = scnr.next();
             scnr.next();//these are required to bypass spaces
             String userName = scnr.next();
             scnr.next();
             String password = scnr.next();
             TheLegendOfZeldaALinkToThePast = DriverManager.getConnection(connectionInfo,userName,password);
         } catch (FileNotFoundException | SQLException e) {
             e.printStackTrace();
         }

         return TheLegendOfZeldaALinkToThePast;
    }//end startConnection
    //this should only be run 1 time, the connection object named 'TheLegendOfZeldaALinkToThePast is passed directly to methods that use it
}


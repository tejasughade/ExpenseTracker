package org.example;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;
import static org.example.Features.*;

public class App 
{
    public static void main( String[] args ) throws SQLException, FileNotFoundException {

        connect();

        Scanner sc = new Scanner(System.in);

        String type;
        String category;
        int amount;

        while(true){
            System.out.println();
            System.out.println("Enter 1: to add the transaction using file");
            System.out.println("Enter 2: to add the transaction manually");
            System.out.println("Enter 3: for summery");
            System.out.println("Enter 4: to exit");

            int i = sc.nextInt();

            String loc = sc.nextLine();
            if(i==1){
                System.out.println("Enter the file location");
                loc = sc.nextLine();

                System.out.println("Enter the file location");

                addFile(loc);
            }else if (i == 2){
                System.out.println("Enter 1: for income");
                System.out.println("Enter 2: for expense");
                i = sc.nextInt();

                if (i == 1){
                    System.out.println("Enter 1: from salary");
                    System.out.println("Enter 2: from business");
                    i = sc.nextInt();

                    System.out.println("Enter amount of the transaction");
                    amount = sc.nextInt();


                    if(i==1){
                        addTransaction("income", "salary", amount);
                    } else if(i==2) {
                        addTransaction("income", "business", amount);
                    }else {
                        System.out.println("invalid input, please try again!");
                    }
                }else if(i == 2){
                    System.out.println("Enter 1: for food");
                    System.out.println("Enter 2: for rent");
                    System.out.println("Enter 3: for travel");
                    i = sc.nextInt();

                    System.out.println("Enter amount of the transaction");
                    amount = sc.nextInt();

                    if(i==1){
                        addTransaction("expense", "food", amount);
                    } else if(i==2) {
                        addTransaction("expense", "rent", amount);
                    }else if(i==3) {
                        addTransaction("expense", "travel", amount);
                    }else {
                        System.out.println("invalid input, please try again!");
                    }
                }else{
                    System.out.println("invalid input, please try again!");
                }
            }else if(i == 3){
                viewTable();

            }else if(i == 4){
                break;
            }else {
                System.out.println("wrong input, please try again!");
            }
        }
        conn.close();
    }
}

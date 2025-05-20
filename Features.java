package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Features {

    static Connection conn;

    static void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ExpenseTracker", "postgres", "postgre123");
    }

    static void addTransaction(String type, String category, int amount) throws SQLException {
        //System.out.print("Enter feedback: ");
        //String feedback = scanner.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "insert into main(type,category, amount) values (?, ?, ?)"
        );
        ps.setString(1, type);
        ps.setString(2, category);
        ps.setInt(3,amount);
        ps.executeUpdate();
        System.out.println("Transaction added.");
    }

    static void addTransaction(String type, String category, int amount, java.util.Date date) throws SQLException {
        //System.out.print("Enter feedback: ");
        //String feedback = scanner.nextLine();
        PreparedStatement ps = conn.prepareStatement(
                "insert into main(type,category, amount, transaction_date) values (?, ?, ?, ?)"
        );
        ps.setString(1, type);
        ps.setString(2, category);
        ps.setInt(3,amount);
        ps.setDate(4, (Date) date);
        ps.executeUpdate();
        System.out.println("Transaction added.");
    }

    static void viewTable() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("""
               select type, category, sum(amount) amount from main group by type, category;""");
        System.out.println("\nTransactions:");
        while (rs.next()) {
            System.out.println(rs.getString("type") + ": " + rs.getString("category") + ":" + rs.getInt("amount") );
        }
    }

    static void addFile(String loc) throws FileNotFoundException, SQLException {


        Scanner sca = new Scanner(new File(loc));

        if(sca.hasNextLine()){
            sca.nextLine();
        }

        while(sca.hasNextLine()){
            String line = sca.nextLine();

            String[] values = line.split(",");

            //System.out.println(values);

            String type = values[0];
            String category = values[1];
            int amount = Integer.parseInt(values[2]);
            Date transaction_date = Date.valueOf(values[3]);


            //System.out.println(type + ":" + category + ":" +amount + ":" + transaction_date + ":"  );
            addTransaction(values[0], values[1], amount, transaction_date);
        }

    }
    }

//C:\\Users\\ughad\\Downloads\\data-1747726107918.csv

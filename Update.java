package com.bec.oop;

import com.bec.oop.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Update {

    static Connection conn;
    static Database db = new Database();

    public static void main(String[] args) {
        Students s1 = new Students();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter lastname: ");
//        String lastname = scan.nextLine();

//        viewAll();
//        viewStudent(lastname);
        deleteStudent("1");
    }

   public static void updateStudent(String id, String newFirstName, String newLastName, String newAddress) {
    try {
        String sql = "UPDATE students SET firstname = ?, lastname = ?, address = ? WHERE id = ?";
        conn = db.connect();
        PreparedStatement ps = conn.prepareStatement(sql);

        // Set parameters for the prepared statement
        ps.setString(1, newFirstName);
        ps.setString(2, newLastName);
        ps.setString(3, newAddress);
        ps.setString(4, id);

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("No student found with the given ID or no changes were made.");
        }

    } catch (Exception ex) {
        System.out.println("Error: " + ex.getMessage());
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}

    public static void viewAll() {
        try {
            String sql = "SELECT * FROM students";
            conn = db.connect();
//            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public static void viewStudent(String lastname) {
        try {
            String sql = "SELECT * FROM students WHERE lastname = '" + lastname + "'";
            conn = db.connect();
//            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("firstname") + " " + rs.getString("lastname"));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public static void deleteStudent(String id) {
        try {
            String sql = "DELETE FROM students WHERE id = '" + id + "'";
            conn = db.connect();
//            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);

            int result = ps.executeUpdate();
            if (result > 0  ) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("There were errors while deleting the student.");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
    //update student set firstname = ?, lastname = ?, address = ? where id = ?
    //insert into student (firstname,lastname, address) values("",)

}

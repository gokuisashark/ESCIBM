package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SQLUtilities {

    private static final String URL = "jdbc:sqlserver://localhost;database=login_DB;integratedSecurity=true;";
    static java.sql.Connection connection;

    static boolean Connect(){
        try {
            System.out.println("Connecting to database...");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL);
            System.out.println("Connect successfully");
            return true;
        } catch (ClassNotFoundException e) {
            e.getStackTrace();
            return false;
        } catch (SQLException e) {
            e.getStackTrace();
            return false;
        }
    }

    static boolean Disconnect() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //psvm for lib testing
//    public static void main(String[] args) throws SQLException {
//        String sql = "";
//        List<HashMap<String,Object>> ls = null;
//
//        System.out.println("Test Case 1: get a list of all the data");
//        sql = "select * from login";
//        ls =  getData.ExecuteQuery(sql);
//        System.out.println(ls);
//
//        System.out.println("Test Case 2: get a list of the username and userid");
//        sql = "select name,uid from login";
//        ls =  getData.ExecuteQuery(sql);
//        System.out.println(ls);
//
//        System.out.println("Test Case 3: create a new account");
//        sql = "INSERT INTO login VALUES ('aya', 'psssss', '654321')";
//        updateData.ExecuteNonQuery(sql);
//
//        System.out.println("Test Case 4: update the data of an account");
//        sql = "UPDATE login SET pwd = 'newpassword', uid = '100000' WHERE name = 'xixi'";
//        updateData.ExecuteNonQuery(sql);
//
//        System.out.println("Test Case 5: delete an existing account");
//        sql = "DELETE login WHERE name = 'lalalu'";
//        updateData.ExecuteNonQuery(sql);
//    }

}

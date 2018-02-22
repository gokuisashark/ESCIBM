package com.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Li Xueqing on 9/2/2018.
 *
 * class to update SQL server with query
 *
 * returns the following states for query:
 * 0: No error
 * -1: Error
 */

public class updateData extends SQLUtilities {

    private static PreparedStatement statement = null;

    static int ExecuteNonQuery(String query){
        int sig = 0;
        System.out.println(query);
        try {
            //Open a connection
            Connect();

            //Execute a query
            System.out.println("Creating statement...");
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            //lean up
            statement.close();

        } catch (SQLException e) {
            //Handle exceptions for JDBC
            e.printStackTrace();
            sig =  -1;
        } catch (Exception e) {
            //Handle exceptions for Class.forName
            e.printStackTrace();
            sig =  -1;
        } finally {
            //close resources
            try {
                if (statement!=null) {
                    statement.close();
                }
            } catch (SQLException ex) {
                //do nothing
            }
            Disconnect();
        }
        System.out.println("Update finished!");
        return sig;
    }

}

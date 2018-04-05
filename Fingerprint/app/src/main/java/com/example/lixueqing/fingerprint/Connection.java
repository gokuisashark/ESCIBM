package com.example.lixueqing.fingerprint;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Li Xueqing on 2/2/2018.
 */

public class Connection {
    private static final String URL = "jdbc:sqlserver://localhost;database=login_DB;integratedSecurity=true;";
    static java.sql.Connection connection;

    static boolean Connect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(URL);
            System.out.println("connect successfully");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            return false;
        } catch (SQLException e) {
            System.out.println("sql exception");
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

    static int ExecuteNonQuery(PreparedStatement preparedStatement){
        try {
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    static ResultSet ExecuteQuery(PreparedStatement sql){
        try {
            return sql.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection.Connect();
        String sql = "select * from login";
        PreparedStatement s = Connection.connection.prepareStatement(sql);
        ResultSet r = Connection.ExecuteQuery(s);
        System.out.println("-----------------------------");
        while(r.next()){
            System.out.println(r.getString(1)+"\t"+r.getString(2));
        }
        Connection.Disconnect();
    }


//    protected static final String DB_URL = "jdbc:jtds:sqlserver://" +
//            "50-001.database.windows.net" + //server
//            ":1433" + //port
//            "/50-001;" + //???
//            "user=superadmin@50-001;" + //user
//            "password=Password1!;"; //password

    //jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
    //ConnectionURL = "jdbc:jtds:sqlserver://" + server + database + ";user=" + user+ ";password=" + password + ";";
}

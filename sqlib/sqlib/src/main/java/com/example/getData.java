package com.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Li Xueqing on 9/2/2018.
 *
 * class to query SQL server for data
 */

public class getData extends SQLUtilities {

    private static ResultSet r = null;
    private static PreparedStatement statement = null;

    static List<HashMap<String,Object>> ExecuteQuery(String query) {
        List<HashMap<String,Object>> rows = new ArrayList<HashMap<String,Object>>();
        try {
            //Open a connection
            Connect();

            //Execute a query
            System.out.println("Creating statement...");
            statement = connection.prepareStatement(query);
            r = statement.executeQuery();

            //
            while(r.next()) {
                HashMap<String,Object> resultMap = new HashMap<String,Object>();
                ResultSetMetaData m = r.getMetaData();
                for (int i=1; i<m.getColumnCount()+1; i++) {
                    resultMap.put(m.getColumnName(i), r.getObject(i));
                }
                rows.add(resultMap);
            }

            //lean up
            r.close();
            statement.close();

        } catch (SQLException e) {
            //Handle exceptions for JDBC
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            //Handle exceptions for Class.forName
            e.printStackTrace();
        } finally {
            //close resources
            try{
                if (statement!=null) statement.close();
            } catch (SQLException ex) {
                //do nothing
            }
            Disconnect();
        }
        return rows;
    }

}

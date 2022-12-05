package org.usageexample;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        try {
        //Connection conn = DriverManager.getConnection("jdbc:Skyflow:/home/deq/Downloads/Skyflow-jdbcdriver/Skyflow-jdbc-driver-example-master/src/main/resources");
        Connection conn = DriverManager.getConnection("jdbc:skyflow:/home/deq/Downloads/Skyflow-jdbcdriver/Skyflow-jdbc-driver-master/vault");
        Statement stmt = conn.createStatement();
        ResultSet rs1 = stmt.executeQuery("select skyflow_id, id1, id2 from template;");
       
        //ResultSet rs=stmt.getResultSet();
        		 //System.out.println(rs);
        		  rs1.getMetaData();
          while (rs1.next())System.out.println(rs1.getString(1) + " - " + rs1.getString(2)+ " - " + rs1.getString(3));
        }
      catch(Exception e){
  		System.out.println("Exception in main");
  		e.printStackTrace();
  		

      }
    }
}

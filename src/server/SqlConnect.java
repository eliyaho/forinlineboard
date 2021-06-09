/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class SqlConnect {
    
    private Connection conn = null;

    /*public SqlConnect(String tmp) {
    this.tmp = tmp;
    }*/

    
    
    public SqlConnect() throws IOException, SQLException, ClassNotFoundException {
    
            String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=forinline;integratedSecurity=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("connection sql");
            Statement st;
            st = conn.createStatement();
            String sql = "select * from users";
            ResultSet rs = st.executeQuery(sql);
            /*
            // declare of statement and resultSet
            Statement statement;
            ResultSet rs;
            // the statement to be execute
            String query = "select * from users";
            statement = conn.createStatement();
            // execute and get the result set.
            rs = statement.executeQuery(query);
            */
            
            while(rs.next()){
                
                System.out.print(rs.getInt("id")+" ");
                System.out.print(rs.getString("firstName")+ " ");
                System.out.print(rs.getString("lastName")+" ");
               // System.out.print(rs.getDate(""));
                System.out.println("");
            }
            
            // close the resorces
            rs.close();
            //statement.close();
            //conn.close();
            
//Create Statment

//Execute
        /*} catch (SQLException ex) {
            Logger.getLogger(SqlConnect.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    public void create_user(String firstName, String lastName, String gmail, String password) {
        try {
            Statement stmt = conn.createStatement();
 /*try {
            //String connectionUrl = "jdbc:sqlserver://localhost:1433;"
            //       + "databaseName=NORTHWND;";
            String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=NORTHWND;integratedSecurity=true";*/
            //String sql_query = "INSERT INTO [forinline].[dbo].[users] ([firstName], [lastName], [gmail], [pasword]) VALUES ('"+ firstName +"', '"+ lastName +"', '"+ gmail +"', '"+ password +"') GO";
            String sql_query = "INSERT INTO [forinline].[dbo].[users]\n" +
"           ([firstName]\n" +
"           ,[lastName]\n" +
"           ,[gmail]\n" +
"           ,[pasword])\n" +
"     VALUES\n" +
"           ('"+ firstName +"'\n" +
"           ,'"+ lastName +"'\n" +
"           ,'"+ gmail +"'\n" +
"           ,'"+ password +"')";
            System.out.println(sql_query);
            stmt.executeUpdate(sql_query);
            System.out.println("Client '"+firstName+" "+lastName+"' Signup!");
            // conn.commit();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
        }
    }
   //אם המשתמש קיים באסקיו אל
    public boolean is_username_exist(String gmail, String password) {
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * FROM [forinline].[dbo].[users] WHERE [gmail] = '" + gmail + "' AND [pasword] = '"+ password + "'");
            
            while ( rs.next() ) {
                String firstName = rs.getString("firstName");
                String  lastName = rs.getString("lastName");
                System.out.println("Client '"+firstName+" "+lastName+"' Login!");
                
                return true;
            }
            rs.close();
            
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            // System.err.println(e.getMessage());
        }
        
        return false;
    }
    
}

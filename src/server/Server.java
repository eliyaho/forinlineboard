/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author user
 */
public final class Server {
    /*
     public Server()throws IOException{
     runserver();
     }*/

    private thred a;
    private String firstName, lastName, gmail, password = "";
    private SqlConnect sql_server = null;
    public void runserver() throws IOException {
        ServerSocket ss = null;
        //connection with sql
        try {
            sql_server = new SqlConnect();
        } catch (Exception e) {
            System.out.println("C'not connect to sql server");
        }
        //connection to server 
        try {
            ss = new ServerSocket(130);
        } catch (Exception e) {
            System.out.println("not connect server");
        }

        Socket s1, s2;
        while (true) {
            s1 = ss.accept();
            client_connect(s1);
            s2 = ss.accept();
            //client_connect(s2);
            a = new thred(sql_server, s1, s2);
            a.start();

            /*ObjectOutputStream out1 = new ObjectOutputStream(s1.getOutputStream());
             out1.writeObject(a.getFb());
             ObjectOutputStream out2 = new ObjectOutputStream(s2.getOutputStream());
             out2.writeObject(a.getFb());*/
        }

    }
    
    public void client_connect(Socket s1) {
        try {
            boolean while_flag = true;
            while (while_flag) {
                password = "";
                gmail = "";
                lastName = "";
                firstName = ""; 
                
                //ObjectOutputStream s1_out = new ObjectOutputStream(s1.getOutputStream());
                PrintWriter s1_out = new PrintWriter(s1.getOutputStream());
                InputStreamReader s1_input = new InputStreamReader(s1.getInputStream());
                BufferedReader s1_in = new BufferedReader(s1_input);
                String s1_log = s1_in.readLine();
                String[] s1_details = s1_log.split("&");
                for ( String str: s1_details) {
                    String[] param = str.split("=");
                    
                    if        ( param[0].contains("firstName") ) {
                        firstName = param[1];
                    } else if (param[0].contains("lastName") ) {
                        lastName = param[1];
                    } else if (param[0].contains("gmail")) {
                        gmail = param[1];
                    } else if (param[0].contains("pass")) {
                        password = param[1];
                    }
                }
                   
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                if ( !firstName.isEmpty() && !lastName.isEmpty() && !gmail.isEmpty() && !password.isEmpty() ) {
                    sql_server.create_user(firstName, lastName, gmail, password);
                    s1_out.println("true");
                    s1_out.flush();
                    while_flag=false;
                } else if ( !gmail.isEmpty() && !password.isEmpty() ) {
                    if ( sql_server.is_username_exist(gmail, password) ) {
                        s1_out.println("true");
                        while_flag=false;
                    } else {
                        s1_out.println("false");
                    }
                    s1_out.flush();
                } else {
                    s1_out.println("false");
                    s1_out.flush();
                }
                
            }
                
        } catch (Exception e) {
            System.err.println("erorr s1");
            e.printStackTrace();
        }
    }
}

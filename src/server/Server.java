/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author user
 */
public class Server {

    public Server() throws IOException {
        funk();
    }
    
   private thred a;
    
 public void funk() throws IOException{
        // TODO code application logic here
        ServerSocket ss = null;
        
       
        try {
            ss = new ServerSocket(58);
        } catch (Exception e) {
            System.err.println("go fuck yuor self");
        }
         
        Socket socket1, socket2; 
        
        while (true) {         
         socket1=ss.accept();
         socket2=ss.accept();
         
         a=new thred(socket1, socket2);
         a.start();
         
     }
      

}   
}


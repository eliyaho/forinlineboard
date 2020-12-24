/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import fourInline.FourInlineBoard;
import fourInline.placeInBoard;
import fourInline.print;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.Global.print;


/**
 *
 * @author user
 */
public class thred extends Thread {

    public thred(Socket socket1,Socket socket2) {
        this.socket1 = socket1;
        this.socket2=socket2;
        
        
        try {
         //   out = new PrintWriter(Mysocke.getOutputStream());
         //   System.out.println("PrintWriter was created");
            InputStreamReader input1 = new InputStreamReader(socket1.getInputStream());
            in1 = new BufferedReader(input1);
            InputStreamReader input2 = new InputStreamReader(socket2.getInputStream());
            in2 = new BufferedReader(input2);
            
            
        } catch (Exception e) {
        }
    }

    private Socket socket1,socket2;
    private FourInlineBoard fb = new FourInlineBoard();
    private PrintWriter out = null;
    private BufferedReader in1 = null;
    private BufferedReader in2 = null;

    @Override
    public void run() {

        print p = new print();
        //the first playr is false and the second is true
        boolean turn = false;
        int column=0;
        
        do {      
            p.printBoard(fb);
            try {
                turn=true;
                column = Integer.parseInt(in1.readLine());
                fb.enterNewPiece(column, turn);
            } catch (IOException ex) {
                System.err.println("error");
            }
            
            
            p.printBoard(fb);
            try {
                turn=false;
                column = Integer.parseInt(in2.readLine());
                fb.enterNewPiece(column, turn);
            } catch (IOException ex) {
                System.err.println("error");
            }
            
        } while (!fb.checkIfWon(column));

    }

}

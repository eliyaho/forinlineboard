/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import ser.print;
import ser.FourInlineBoard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 *
 * @author user
 */
public class thred extends Thread {

    private FourInlineBoard fb;
    private SqlConnect sql_server;

    public thred(SqlConnect sql_server, Socket socket1, Socket socket2) {
        this.socket1 = socket1;
        this.socket2 = socket2;
        this.sql_server = sql_server;

        try {
            InputStreamReader input1 = new InputStreamReader(socket1.getInputStream());
            in1 = new BufferedReader(input1);
            InputStreamReader input2 = new InputStreamReader(socket2.getInputStream());
            in2 = new BufferedReader(input2);

        } catch (Exception e) {
        }
    }

    private Socket socket1, socket2;
    private BufferedReader in1 = null;
    private BufferedReader in2 = null;

    @Override
    public void run() {
       
        fb = new FourInlineBoard();
        print p = new print();
        //the first playr is false and the second is true
        boolean turn = false;
        int column = 0;
        do {
            p.printBoard(fb);
            do {
                try {
                    turn = true;
                    String S1 = in1.readLine();
                    do {
                        column = Integer.parseInt(S1);
                    } while (!(S1.equals("0") || S1.equals("1")
                            || S1.equals("2") || S1.equals("3")
                            || S1.equals("4") || S1.equals("5") || S1.equals("6")));

                    fb.enterNewPiece(column, turn);
                    //sending the board to the client
                    try {
                        ObjectOutputStream obo = new ObjectOutputStream(socket1.getOutputStream());
                        obo.writeObject(fb);
                    } catch (Exception e) {
                        System.err.println("erorr obo");
                    }
                } catch (IOException ex) {
                    System.err.println("error");
                }
            } while (!(column >= 0 && column <= 6));
            if (fb.checkIfWon(column)) {
                break;
          }
           
            p.printBoard(fb);
            do {
                try {
                    turn = false;
                    String S2 = in2.readLine();
                    do {
                        column = Integer.parseInt(S2);
                    } while (!(S2.equals("0") || S2.equals("1")
                            || S2.equals("2") || S2.equals("3")
                            || S2.equals("4") || S2.equals("5") || S2.equals("6")));
                    fb.enterNewPiece(column, turn);
                    try {
                        ObjectOutputStream obo = new ObjectOutputStream(socket2.getOutputStream());
                        obo.writeObject(fb);
                    } catch (Exception e) {
                        System.err.println("erorr obo");
                    }
                } catch (IOException ex) {
                    System.err.println("error");
                }
            } while (!(column >= 0 && column <= 6));
        } while (!fb.checkIfWon(column));

        p.printBoard(fb);
        if (turn) {
            System.out.println("player black won!!!!!!!!!");
        } else {
            System.out.println("player red won!!!!!!!!!");
        }
    }

    public FourInlineBoard getFb() {
        return this.fb;
}

}

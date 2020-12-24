package fourInline;


import java.io.IOException;
import java.util.Scanner;
import server.Server;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class FourInline00 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*        FourInlineBoard board1 = new FourInlineBoard();
        playGame(board1);*/
        
        Server a = new Server();
    }
    public static void playGame(FourInlineBoard board){
        
        print p = new print();
        //the first playr is false and the second is true
        boolean turn = false;
        int column;
        
        do {      
            p.printBoard(board);
            
            do {
                column = getColumn(turn);//קריאה לשחקן להכניס נתון
            } while (!board.isColumnLegal(column));//בדיקה אם התור מלא 

            board.enterNewPiece(column, turn);
            turn = !turn;
        } while (!board.checkIfWon(column));

        if (turn) {
            System.out.println("player black won!!!!!!!!!");
        } else {
            System.out.println("player red won!!!!!!!!!");
        }
    }
    
    public static int getColumn(boolean turn) {
        Scanner s = new Scanner(System.in);
        if (turn) {
            System.out.println("black enter column");
        } else {
            System.out.println("red enter column");
        }

        return s.nextInt();
    }
}

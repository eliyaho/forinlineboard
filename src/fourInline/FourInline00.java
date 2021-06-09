package fourInline;



import java.io.IOException;
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
    public static void main(String[] args) throws IOException  {
        /*FourInlineBoard board1 = new FourInlineBoard();
        playGame(board1);*/
     
        Server a = new Server();
        a.runserver();
               
    }
}

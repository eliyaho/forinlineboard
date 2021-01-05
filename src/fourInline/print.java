package fourInline;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class print {
        public void printBoard(FourInlineBoard board){
            for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.getBoard()[i][j].getType() == state.empty) {
                    System.out.printf("%-4s", "[]");
                } else if (board.getBoard()[i][j].getType() == state.black) {
                    System.out.printf("%-4d", 0);
                } else if (board.getBoard()[i][j].getType() == state.red) {
                    System.out.printf("%-4d", 1);
                    //todo Change to swits case
                }
            }
            System.out.println("");
        }
            System.out.println();
    }
}

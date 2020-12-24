package fourInline;


import java.util.Scanner;
import org.omg.CORBA.INTERNAL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class FourInlineBoard {
    private placeInBoard[][] board ;
    private int amountOfEmpty;
    int[] amountEmptyInColumn;

    public FourInlineBoard() {
        board = new placeInBoard[6][7];
        amountEmptyInColumn = new int[7];
        amountOfEmpty = 6 * 7;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board [i][j] = new placeInBoard();
            }
        }
        
        for (int i = 0; i < 7; i++) {
            amountEmptyInColumn[i] = 5;
        }
    }
    
    public  boolean isColumnLegal(int column){
       if(column < 0 || column > 6){
           return false;
       }
       if(amountEmptyInColumn[column] >= 0){
           return true;
       }
       return false;
    }
    
    public boolean checkIfWon(int column) {

        int r = amountEmptyInColumn[column] + 1;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if((i == 0 && j == 0) || (i == -1 && j ==0)){
                    continue;
                }
                int k;
                for (k = 1; k < 4; k++) {
                    if (!validLocation(r + i * k, column + j * k)){
                        break;
                    }
                    if(board[r + i * k][column + j * k].getType() != board[r][column].getType()){
                        break;
                    }
                }
                if(k == 4){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean validLocation(int i, int j){
        if(i < 0 || i > 5 || j < 0 || j > 6){
            return false;
        }
        return true;
    }
    
    public void enterNewPiece(int column, boolean turn){
        board[amountEmptyInColumn[column]][column].setType((turn)? state.red : state.black );
        amountEmptyInColumn[column]--;
    }
    
    public void decAmouOfEmp(){
        this.amountOfEmpty--;
    }
    
    public int getAmountOfEmpty() {
        return amountOfEmpty;
    }

    public void setAmountOfEmpty(int amountOfEmpty) {
        this.amountOfEmpty = amountOfEmpty;
    }
    
    public placeInBoard[][] getBoard() {
        return board;
    }

    public void setBoard(placeInBoard[][] board) {
        this.board = board;
    }
    

}

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
public class placeInBoard {
    private state type;

    public placeInBoard() {
        this.type = type.empty;
    }

    public placeInBoard(state type) {
        this.type = type;
    }

    public placeInBoard(int i) {
        switch(i){
            case 1: {
                this.type = type.red;
                break;
            }
            case 0: {
                this.type = type.empty;
                break;
            }
            case -1: {
                this.type = type.black;
                break;
            }
            default:{
                
            }
        }
    }

    public state getType() {
        return type;
    }

    public void setType(state type) {
        this.type = type;
    }
    
}
enum state{
    empty,
    black,//second
    red //red starts
}
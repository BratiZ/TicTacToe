package tictactoe;

import java.util.ArrayList;

public class TTTGameLogic {

    int[][] logicBoard;
    ArrayList< Integer> winningFields;
    
    void CreateLogicBoard(){
        for( int f = 0; f < 3; ++f)
            for (int g = 0; g < 3; ++g)
                this.logicBoard[f][g] = 8;
    }
    
    public TTTGameLogic() {
        this.logicBoard = new int[3][3];
        this.winningFields = new ArrayList<>();
        this.CreateLogicBoard();
    }
    
    void setLogicBoardField( int field, int who){
        switch( field){
            case 1:
                this.logicBoard[0][0] = who%2;
                break;
                
            case 2:
                this.logicBoard[0][1] = who%2;
                break;
                
            case 3:
                this.logicBoard[0][2] = who%2;
                break;
                
            case 4:
                this.logicBoard[1][0] = who%2;
                break;
                
            case 5:
                this.logicBoard[1][1] = who%2;
                break;
                
            case 6:
                this.logicBoard[1][2] = who%2;
                break;
                
            case 7:
                this.logicBoard[2][0] = who%2;
                break;
                
            case 8:
                this.logicBoard[2][1] = who%2;
                break;
                
            case 9:
                this.logicBoard[2][2] = who%2;
                break;
        }
    }
    
    int getValueOfField( int fieldIndex){
        switch( fieldIndex){
            case 1:
                return this.logicBoard[0][0];
                
            case 2:
                return this.logicBoard[0][1];
                
            case 3:
                return this.logicBoard[0][2];
                
            case 4:
                return this.logicBoard[1][0];
                
            case 5:
                return this.logicBoard[1][1];
                
            case 6:
                return this.logicBoard[1][2];
                
            case 7:
                return this.logicBoard[2][0];
                
            case 8:
                return this.logicBoard[2][1];
                
            case 9:
                return this.logicBoard[2][2];
        }
        
        return 8;
    }
    
           
    int CheckWinnerInLogicBoardRows(){
        int controlSum = 0,
            winner = 8;
        
        for( int f = 0; f < 3; ++f){
            for (int g = 0; g < 3; ++g)
                controlSum += this.logicBoard[f][g];
            
            //[1, 2, 3] -> [1,2,3] -> 0
            //[2, 3, 4] -> [4,5,6] -> 1
            //[3, 4, 5] -> [7,8,9] -> 2
            
            if( controlSum == 0){
                winner = 1;
                this.winningFields.add( 3*f+1);
                this.winningFields.add( 3*f+2);
                this.winningFields.add( 3*f+3);
            }
            else if( controlSum == 3){
                winner = 2;
                this.winningFields.add( 3*f+1);
                this.winningFields.add( 3*f+2);
                this.winningFields.add( 3*f+3);
            }
            controlSum = 0;
        }
        return winner;
    }
    
    int CheckWinnerInLogicBoardColumns(){
        int controlSum = 0,
            winner = 8;
        
        for( int f = 0; f < 3; ++f){
            for( int g = 0; g < 3; ++g)
                controlSum += this.logicBoard[g][f];
            
            if( controlSum == 0){
                winner = 1;
                this.winningFields.add( f+1);
                this.winningFields.add( f+4);
                this.winningFields.add( f+7);
            }
            else if( controlSum == 3){
                winner = 2;
                this.winningFields.add( f+1);
                this.winningFields.add( f+4);
                this.winningFields.add( f+7);
            }
            controlSum = 0;
        }
        return winner;
    }
    
    int CheckwinnerInLogicBoardLeftDiagonal(){
        int controlSum = 0,
            winner = 8;
        
        for( int f = 0; f < 3; ++f)
            controlSum += this.logicBoard[f][f];

        if( controlSum == 0){
            winner = 1;
            this.winningFields.add( 1);
            this.winningFields.add( 5);
            this.winningFields.add( 9);
        }
        else if( controlSum == 3){
            winner = 2;
            this.winningFields.add( 1);
            this.winningFields.add( 5);
            this.winningFields.add( 9);
        }        
        return winner;
    }
    
    int CheckWinnerInLogicBoardRightDiagonal(){
        int controlSum = 0,
            IteratorG = 2,
            winner = 8; 
        
        for( int f = 0; f < 3; ++f)
            controlSum += this.logicBoard[IteratorG--][f];                
            
        if( controlSum == 0){
            winner = 1;
            this.winningFields.add( 7);
            this.winningFields.add( 5);
            this.winningFields.add( 3);
        }
        else if( controlSum == 3){
            winner = 2;
            this.winningFields.add( 7);
            this.winningFields.add( 5);
            this.winningFields.add( 3);
        }        
        return winner;
    }
        
    boolean isPlayerOneWinner(){
        return this.CheckWinnerInLogicBoardColumns() == 1 |
                this.CheckWinnerInLogicBoardRows() == 1 |
                this.CheckwinnerInLogicBoardLeftDiagonal() == 1 |
                this.CheckWinnerInLogicBoardRightDiagonal() == 1;
    }
    
    boolean isPlayerTwoWinner(){
        return this.CheckWinnerInLogicBoardColumns() == 2 |
                this.CheckWinnerInLogicBoardRows() == 2 |
                this.CheckwinnerInLogicBoardLeftDiagonal() == 2 |
                this.CheckWinnerInLogicBoardRightDiagonal() == 2;
    }
    
    boolean isFildWinner( int fieldIndex){
        return this.winningFields.indexOf( fieldIndex) != -1;
    }
    
    boolean isThereAnyWinner(){
        return this.isPlayerOneWinner() || this.isPlayerTwoWinner();
    }   
   
    boolean isSpaceInBoard(){
        for( int f = 0; f < 3; ++f)
            for( int g = 0; g < 3; ++g)
                if( this.logicBoard[f][g] == 8)
                    return true;
        return false;
    }
    
    boolean isGameEnded(){
        return !this.isPlayerOneWinner() && !this.isPlayerTwoWinner() && !this.isSpaceInBoard();
    }
    
    int WhoIsWinner(){
        if( this.isPlayerOneWinner())
            return 1;
        else if ( this.isPlayerTwoWinner())
            return 2;
        
        return 8;
    }
    
    void gameReset(){
        this.CreateLogicBoard();
        this.winningFields.clear();
    }
    
    @Override
    public String toString(){
        String resoult = "";
        
        for( int f = 0; f < 3; ++f){
            for( int g = 0; g < 3; ++g)
                resoult += this.logicBoard[f][g];
            resoult += "\n";
        }
        
        return resoult;
    }
}

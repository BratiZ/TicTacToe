package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyBoard extends JFrame {
    final String colourTextPlayerNormal = "#f9f9f9",
                 colourTextPlayerMuve = "#3399ff",
                 colourTextWinner = "#33cc00",
                 colourButtonNormal = "#f1f1f1",
                 colourButtonClicked = "#d2d2d2",
                 colourButtonWinner = "#ffff33",
                 signPlayerOne = "O",
                 signPlayerTwo = "X",
                 nameTextFont = "Arial";
    
    final int sizeTextFont = 50,
              typeTextFont = Font.PLAIN;
    
    TTTGameLogic gameLogic;
    int playerMuve;
    String playerSign;
    
    JTextField textPlayer1,
               textPlayer2;
    
    TTTButton[] buttons;
    
    class TTTButton extends JButton implements ActionListener{

        public TTTButton() {
            this.setBackground( Color.decode( colourButtonNormal));
            this.setText("");        
            this.setFont( new Font( nameTextFont, typeTextFont, sizeTextFont));
            addActionListener( this);
        }
        
        public TTTButton(String string) {
            this.setText( string);           
            addActionListener( this);
        }
        
        void ResetButton(){
            this.setBackground( Color.decode( colourButtonNormal));
            this.setText("");        
            this.setFont( new Font( nameTextFont, typeTextFont, sizeTextFont));
            this.setEnabled( true);
        }
        
        void DisableButton(){
            this.setEnabled( false);
        }

        @Override
        public void actionPerformed( ActionEvent ae) {
            Object source = ae.getSource();
            int indexOf = 0;
            
            if( playerMuve%2 == 0){
                playerSign = signPlayerOne;
                textPlayer1.setBackground( Color.decode( colourTextPlayerNormal));
                textPlayer2.setBackground( Color.decode( colourTextPlayerMuve));
            }
            else{
                playerSign = signPlayerTwo;
                textPlayer1.setBackground( Color.decode( colourTextPlayerMuve));
                textPlayer2.setBackground( Color.decode( colourTextPlayerNormal));
            }
            
            for( int f = 0; f < 10; ++f)
                if( source == buttons[f]){
                    indexOf = f;
                    break;
                }
            
            if( indexOf == 0){
                gameLogic.gameReset();
                ResetAllButtons();
                playerMuve = 0;
                textPlayer1.setText( signPlayerOne);
                textPlayer2.setText( signPlayerTwo);
            }
            else{
                buttons[ indexOf].setEnabled( false);
                buttons[ indexOf].setBackground( Color.decode( colourButtonClicked));
                buttons[ indexOf].setText( playerSign);
                gameLogic.setLogicBoardField( indexOf, playerMuve++);
            }            
            CheckWinner();
        }
    }
    
    void CheckWinner(){
        if( this.gameLogic.isPlayerOneWinner()){
                this.textPlayer1.setBackground( Color.decode( colourTextWinner));
                this.textPlayer2.setBackground( Color.decode( colourTextPlayerNormal));
                this.DisableAllButtons();
        }
        else if( this.gameLogic.isPlayerTwoWinner()){
                this.textPlayer1.setBackground( Color.decode( colourTextPlayerNormal));    
                this.textPlayer2.setBackground( Color.decode( colourTextWinner));
                this.DisableAllButtons();
        }
        else if( this.gameLogic.isGameEnded()){
                this.textPlayer1.setBackground( Color.decode( colourTextWinner));    
                this.textPlayer2.setBackground( Color.decode( colourTextWinner));
                this.DisableAllButtons();
        }  
        if( this.gameLogic.isThereAnyWinner()){
            for( int f = 1; f < 10; ++f)
                if( this.isWinnerField( f)){
                    this.buttons[ f].setBackground( Color.decode( colourButtonWinner));
                }
        }
    }
    
    boolean isWinnerField( int fieldIndex){
        return this.gameLogic.isFildWinner( fieldIndex);
    }
    
    void ResetAllButtons(){
        this.textPlayer1.setBackground( Color.decode( colourTextPlayerMuve));
        this.textPlayer2.setBackground( Color.decode( colourTextPlayerNormal));
    
        for( int f = 1; f < 10; ++f)
            this.buttons[f].ResetButton();
    }
    
    void DisableAllButtons(){
        for( int f = 1; f < 10; ++f)
            this.buttons[f].DisableButton();
    }
    
    public MyBoard() throws HeadlessException {
        super( "Tic Tac Toe");
        this.playerMuve = 0;
        this.gameLogic = new TTTGameLogic();
        this.buttons = new TTTButton[10];
        
        this.textPlayer1 = new JTextField( signPlayerOne);
        this.textPlayer1.setHorizontalAlignment( 0);
        this.textPlayer1.setEditable( false);
        this.textPlayer1.setBackground( Color.decode( colourTextPlayerMuve));
        this.textPlayer1.setFont( new Font( nameTextFont, typeTextFont, sizeTextFont));
        
        this.textPlayer2 = new JTextField( signPlayerTwo);
        this.textPlayer2.setHorizontalAlignment( 0);
        this.textPlayer2.setEditable( false);
        this.textPlayer2.setBackground( Color.decode( colourTextPlayerNormal));
        this.textPlayer2.setFont( new Font( nameTextFont, typeTextFont, sizeTextFont));
        
        for( int f = 1; f < 10; ++f)
            this.buttons[f] = new TTTButton();
        
        this.buttons[0] = new TTTButton("RESET");
        
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setSize( 280, 400);
        
        setLayout( new GridLayout(4,3));
        
        add( this.textPlayer1);
        add( this.buttons[0]);
        add( this.textPlayer2);
        
        for( int f = 1; f < 10; ++f)
            add( this.buttons[f]);
               
        setVisible( true);
    }
    
    
}

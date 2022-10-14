package com.example.chessgamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private TextView textView;
    private ChessState firstInstance;
    private ChessState secondInstance;
    private ChessState thirdInstance;
    private int numClicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numClicks = 0;
        Button runTestButton = findViewById(R.id.runTest);
        textView = findViewById(R.id.multiLineText);
        runTestButton.setOnClickListener(this);
        runTestButton.setText("RUN TEST");
        firstInstance = new ChessState();
        secondInstance = new ChessState(firstInstance);
        thirdInstance = new ChessState();
    }

    @Override
    public void onClick(View view) {

        //Only displays once
        if(numClicks == 0) {
            textView.append("Initial Board:\n" + firstInstance);
        }
        //Find the current color's turn
        String currPlayer = firstInstance.getWhoseMove() == 0 ? "White" : "Black";
        //Display whose move it is and what move the current player
        //decided to make
        textView.append("\n" + currPlayer + "'s Move\n");
        textView.append(currPlayer + " chooses to move a pawn\n");
        //check if the piece the player wants to move
        //can move and if that piece can capture anything
        if(canMove()){
            if(canCapture()){
                textView.append(currPlayer + " has captured a pawn\n");
                makeCapture();
            }
            makeMove();
        }
        //numClicks keeps track of the turn number
        numClicks++;
        //Alternate moves after a turn is over
        firstInstance.setWhoseMove(1 - firstInstance.getWhoseMove());
        textView.append("\n\n");
        //Display the updated board
        textView.append(String.valueOf(firstInstance));
        textView.append("\n\n");
        //Display secondInstance and thirdInstance to show that they're the same
        if(numClicks == 3){textView.append("Click again for second and third instance");}
        if(numClicks > 3) {
            textView.append("\n\n");
            textView.append("This is the second instance: \n");
            textView.append(""+secondInstance);
            textView.append("\n\n");
            textView.append("This is the third instance: \n");
            textView.append(""+thirdInstance);
        }
    }

    /**
     * Checks if the move is valid for the piece that is selected
     * @return True if the selected piece can move to the desired spot
     * false if not
     */
    public boolean canMove() {
        boolean move = false;
        if(numClicks == 0) {
            //White moves a pawn forward two spaces
            move = firstInstance.checkSelectPiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 6))
                    && firstInstance.checkMovePiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 6),
                    firstInstance.getPiece(4, 4));
        }else if(numClicks == 1){
            //Black moves a pawn forward two spaces
            move = firstInstance.checkSelectPiece(firstInstance.getWhoseMove(), firstInstance.getPiece(3, 1))
                    && firstInstance.checkMovePiece(firstInstance.getWhoseMove(), firstInstance.getPiece(3, 1),
                    firstInstance.getPiece(3, 3));
        }else if(numClicks == 2){
            //White captures blacks pawn
            move = firstInstance.checkSelectPiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 4))
                    && firstInstance.checkMovePiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 4),
                    firstInstance.getPiece(3, 3));
        }
        return move;
    }

    /**
     * Makes the desire move
     */
    public void makeMove() {
        if(numClicks == 0) {
            firstInstance.setPiece(4, 4, firstInstance.getPiece(4, 6));
            firstInstance.setPiece(4, 6, firstInstance.emptyPiece);
        }else if(numClicks == 1){
            firstInstance.setPiece(3, 3, firstInstance.getPiece(3, 1));
            firstInstance.setPiece(3, 1, firstInstance.emptyPiece);
        }else if(numClicks == 2) {
            firstInstance.setPiece(4, 4, firstInstance.getPiece(3, 3));
            firstInstance.setPiece(4, 4, firstInstance.emptyPiece);
        }
    }

    /**
     * Adds captured piece to appropriate
     * captured pieces arraylist
     */
    public void makeCapture(){
        //Adds a pawn to white's captured pieces
        int currPlayer = firstInstance.getWhoseMove();
        if(currPlayer == 0) {
            firstInstance.getWhiteCapturedPieces().add(firstInstance.getPiece(3,3));;
        }
    }


    /**
     * Checks to see if a capture can be made at the square the player
     * wants to move to
     * @return True if the piece the player selects can capture something
     * false if not
     */
    public boolean canCapture(){
        boolean capture = false;
        if(numClicks == 0) {
            capture = firstInstance.checkCapture(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 6),
                    firstInstance.getPiece(4, 4));
        }else if(numClicks == 1){
            capture = firstInstance.checkCapture(firstInstance.getWhoseMove(), firstInstance.getPiece(3, 1),
                    firstInstance.getPiece(3, 3));
        }else if(numClicks == 2){
            capture = firstInstance.checkCapture(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 4),
                    firstInstance.getPiece(3, 3));
        }
        return capture;
    }
}
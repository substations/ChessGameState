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
        firstInstance = new ChessState();
        secondInstance = new ChessState(firstInstance);
        thirdInstance = new ChessState();
    }

    @Override
    public void onClick(View view) {

        if(numClicks == 0) {
            textView.append("Initial Board:\n" + String.valueOf(firstInstance));
        }
        firstInstance.setWhoseMove(firstInstance.getWhoseMove());
        if(numClicks == 0) {
            if(canMove()) {
                makeMove();
            }else if(canCapture()) {
                makeCapture();
            }
        }else if(numClicks == 1) {
            if(canMove()) {
                makeMove();
            }else if(canCapture()) {
                makeCapture();
            }
        }else if(numClicks == 2) {
            if(canMove()) {
                makeMove();
            }else if(canCapture()) {
                makeCapture();
            }
        }
        numClicks++;
        firstInstance.setWhoseMove(1 - firstInstance.getWhoseMove());
        textView.append("\n\n");
        textView.append(String.valueOf(firstInstance));
        textView.append("\n\n");
    }

    public boolean canMove() {
        boolean move = false;
        if(numClicks == 0) {
            move = firstInstance.checkSelectPiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 6))
                    && firstInstance.checkMovePiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 6),
                    firstInstance.getPiece(4, 4));
        }else if(numClicks == 1){
            textView.append(""+firstInstance.getWhoseMove());
            move = firstInstance.checkSelectPiece(firstInstance.getWhoseMove(), firstInstance.getPiece(3, 1))
                    && firstInstance.checkMovePiece(firstInstance.getWhoseMove(), firstInstance.getPiece(3, 1),
                    firstInstance.getPiece(3, 3));
        }else if(numClicks == 2){
            move = firstInstance.checkSelectPiece(firstInstance.getWhoseMove(), firstInstance.getPiece(3, 1))
                    && firstInstance.checkMovePiece(firstInstance.getWhoseMove(), firstInstance.getPiece(4, 4),
                    firstInstance.getPiece(3, 3));
        }
        return move;
    }

    public void makeMove() {
        if(numClicks == 0) {
            firstInstance.setPiece(4, 4, firstInstance.getPiece(4, 6));
            firstInstance.setPiece(4, 6, firstInstance.emptyPiece);
        }else if(numClicks == 1){
            firstInstance.setPiece(3, 3, firstInstance.getPiece(3, 1));
            firstInstance.setPiece(3, 1, firstInstance.emptyPiece);
        }
    }

    public void makeCapture(){
        int currPlayer = firstInstance.getWhoseMove();
        if(currPlayer == 0) {
            firstInstance.getWhiteCapturedPieces().add(firstInstance.getPiece(3,3));
            firstInstance.setPiece(3, 3, firstInstance.getPiece(4, 4));
            firstInstance.setPiece(4, 4, firstInstance.emptyPiece);
        }else {

        }
    }

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
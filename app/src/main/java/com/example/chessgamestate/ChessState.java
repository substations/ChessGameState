package com.example.chessgamestate;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChessState implements View.OnClickListener {

    private Piece[][] pieces;
    private int[][] board;
    private Button runTestButton;
    private TextView textView;

    private int playerToMove;

    public enum Piece{
        PAWN, BISHOP, ROOK, KNIGHT, KING, QUEEN
    }

    public enum PieceColor{
        BLACK, WHITE
    }

    public ChessState(){
        pieces = new Piece[8][8];
        board = new int[8][8];


        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){

            }
        }

    }

    @Override
    public void onClick(View view) {
        textView.append("This button works");
    }

    public void setRunTestButton(Button button) {this.runTestButton = button;}
    public void setTextView(TextView textView){this.textView = textView;}
}

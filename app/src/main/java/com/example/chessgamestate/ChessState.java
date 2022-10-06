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



    public ChessState(){
        pieces = new Piece[8][8];
        board = new int[8][8];

        for (int i = 0; i < 8; i++){
//            pieces[1][i] = pieces[6][i] = Piece.PAWN;
//            pieces[0][0] = pieces[0][7] = pieces[0][7] = pieces[7][7] = Piece.BISHOP;

            pieces[0][0] = new Piece();
        }

//        for (int row = 0; row < 8; row++){
//            for (int col = 0; col < 8; col++){
//
//
//                if(j == 0) {
//                    pieces[0][j] = -4;
//                    pieces[1][j] = -3;
//                    pieces[2][j] = -2;
//                    pieces[3][j] = -5;
//                    pieces[4][j] = -6;
//                    pieces[5][j] = -2;
//                    pieces[6][j] = -3;
//                    pieces[7][j] = -4;
//                } else if(j == 1) {
//                    pieces[i][j] = -1;
//                } else if(j == 6) {
//                    pieces[i][j] = 1;
//                } else if(j == 7) {
//                    pieces[0][j] = 4;
//                    pieces[1][j] = 3;
//                    pieces[2][j] = 2;
//                    pieces[3][j] = 5;
//                    pieces[4][j] = 6;
//                    pieces[5][j] = 2;
//                    pieces[6][j] = 3;
//                    pieces[7][j] = 4;
//                } else {
//                    pieces[i][j] = 0;
//                }
//
//
//            }
//        }

    }

    @Override
    public void onClick(View view) {
        textView.append("This button works");
    }

    public void setRunTestButton(Button button) {this.runTestButton = button;}
    public void setTextView(TextView textView){this.textView = textView;}
}

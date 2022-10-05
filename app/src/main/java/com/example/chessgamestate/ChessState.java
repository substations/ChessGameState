package com.example.chessgamestate;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChessState implements View.OnClickListener {

    // An enum for the different types of pieces
    public enum PieceType {
        PAWN, BISHOP, ROOK, KNIGHT, KING, QUEEN
    }

    // An enum for the color of the pieces
    public enum ColorType {
        BLACK, WHITE
    }

    private Piece[][] pieces; // An array that holds all of the pieces and their position
    private int[][] board; // An array that determines what kind of drawing should be made
    private Button runTestButton;
    private TextView textView;

    private int playerToMove;

    public ChessState() {
        pieces = new Piece[8][8];
        board = new int[8][8];

        // Setting the initial position of all of the pieces
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if(row == 0) {
                    pieces[row][0] = new Piece(PieceType.ROOK, ColorType.BLACK);
                    pieces[row][1] = new Piece(PieceType.KNIGHT, ColorType.BLACK);
                    pieces[row][2] = new Piece(PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[row][3] = new Piece(PieceType.QUEEN, ColorType.BLACK);
                    pieces[row][4] = new Piece(PieceType.KING, ColorType.BLACK);
                    pieces[row][5] = new Piece(PieceType.BISHOP, ColorType.BLACK);
                    pieces[row][6] = new Piece(PieceType.KNIGHT, ColorType.BLACK);
                    pieces[row][7] = new Piece(PieceType.ROOK, ColorType.BLACK);
                } else if(row == 1) {
                    pieces[row][col] = new Piece(PieceType.PAWN, ColorType.BLACK);

                    if(pieces[row][col].getPieceType() == PieceType.PAWN) {
                        //textView.append("This is a Pawn");
                    }
                } else if (row == 6) {
                    pieces[row][col] = new Piece(PieceType.PAWN, ColorType.WHITE);
                } else if (row == 7) {
                    pieces[row][0] = new Piece(PieceType.ROOK, ColorType.WHITE);
                    pieces[row][1] = new Piece(PieceType.KNIGHT, ColorType.WHITE);
                    pieces[row][2] = new Piece(PieceType.BISHOP, ColorType.WHITE);
                    pieces[row][3] = new Piece(PieceType.QUEEN, ColorType.WHITE);
                    pieces[row][4] = new Piece(PieceType.KING, ColorType.WHITE);
                    pieces[row][5] = new Piece(PieceType.BISHOP, ColorType.WHITE);
                    pieces[row][6] = new Piece(PieceType.KNIGHT, ColorType.WHITE);
                    pieces[row][7] = new Piece(PieceType.ROOK, ColorType.WHITE);
                }
            }
        }

        /*for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[col].length; col++) {
                board[row][col] = 0;
            }
        }*/

        playerToMove = 0;
    }
    /*
    public ChessState(){
        pieces = new Piece[8][8];
        board = new int[8][8];

        // Setting the initial position of all of the pieces
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if(row == 0) {
                    pieces[row][0] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK);
                    pieces[row][1] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK);
                    pieces[row][2] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[row][3] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.BLACK);
                    pieces[row][4] = new Piece(Piece.PieceType.KING, Piece.ColorType.BLACK);
                    pieces[row][5] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[row][6] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK);
                    pieces[row][7] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK);
                } else if(row == 1) {
                    pieces[row][col] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.BLACK);

                    // Test if Pawn is placed
                    if(pieces[row][0].equals(Piece.PieceType.PAWN)) {
                        if (pieces[row][0].equals(Piece.ColorType.BLACK)) {
                            System.out.println("Piece is Black Pawn");
                        } else if (pieces[row][0].equals(Piece.ColorType.WHITE)) {
                            System.out.println("Piece is Black Pawn");
                        }
                    } // Pawn Test

                } else if (row == 6) {
                    pieces[row][col] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.WHITE);
                } else if (row == 7) {
                    pieces[row][0] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE);
                    pieces[row][1] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE);
                    pieces[row][2] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE);
                    pieces[row][3] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.WHITE);
                    pieces[row][4] = new Piece(Piece.PieceType.KING, Piece.ColorType.WHITE);
                    pieces[row][5] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE);
                    pieces[row][6] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE);
                    pieces[row][7] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE);
                }
            }
        }

        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[col].length; col++) {
                board[row][col] = 0;
            }
        }

        playerToMove = 0;
    }

    // Copy Constructor
    public ChessState(ChessState other) {

        pieces = new Piece[8][8];
        board = new int[8][8];

        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = other.pieces[i][j];
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = other.board[i][j];
            }
        }

        playerToMove = other.playerToMove;
    }

    public void setBoard(int row, int col, int num) {
        board[row][col] = num;
    }

    public int getBoard(int row, int col) {
        return board[row][col];
    }

    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        pieces[row][col] = piece;
    }

    public int getWhoseMove() {
        return playerToMove;
    }

    public void setWhoseMove(int id) {
        playerToMove = id;
    }

    public boolean equals(Object other){
        if(! (other instanceof ChessState)) return false;
        ChessState chessState = (ChessState) other;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(this.board[i][j] != chessState.board[i][j]){
                    return false;
                }
            }
        }

        if (this.playerToMove != chessState.playerToMove){
            return false;
        }
        return true;
    }
    */

    @Override
    public void onClick(View view) {
        textView.append("This button works");
    }

    public void setRunTestButton(Button button) {
        this.runTestButton = button;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

}

package com.example.chessgamestate;

public class ChessState {

    private Piece[][] pieces;
    private int[][] board;

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
}

package com.example.chessgamestate;

public class Piece {

    public enum PieceType{
        PAWN, BISHOP, ROOK, KNIGHT, KING, QUEEN
    }
    public enum PieceColor{
        BLACK, WHITE
    }


    private PieceType piece;

    public Piece(PieceType p, PieceColor c){
        piece = PieceType.PAWN;
    }


}

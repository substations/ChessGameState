package com.example.chessgamestate;

public class Piece extends ChessState{

    private PieceType pieceType;
    private ColorType pieceColor;

    public Piece(PieceType pieceType, ColorType pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}

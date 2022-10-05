package com.example.chessgamestate;

import android.graphics.Color;

public class Piece {


    // An enum for the different types of pieces
    public enum PieceType {
        PAWN, BISHOP, ROOK, KNIGHT, KING, QUEEN
    }

    // An enum for the color of the pieces
    public enum ColorType {
        BLACK, WHITE
    }

    private PieceType pieceType;
    private ColorType pieceColor;

    public Piece(PieceType pieceType, ColorType pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public ColorType getPieceColor() {
        return pieceColor;
    }
}

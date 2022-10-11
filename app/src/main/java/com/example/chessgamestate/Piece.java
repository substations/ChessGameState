package com.example.chessgamestate;

import android.graphics.Color;

public class Piece {


    // An enum for the different types of pieces
    public enum PieceType {
        PAWN, BISHOP, ROOK, KNIGHT, KING, QUEEN, EMPTY
    }

    // An enum for the color of the pieces
    public enum ColorType {
        BLACK, WHITE, EMPTY
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

    @Override
    public String toString() {
        if(pieceType == PieceType.PAWN) {
            return "P";
        }else if(pieceType == PieceType.BISHOP) {
            return "B";
        }else if (pieceType == PieceType.KNIGHT){
            return "N";
        }else if (pieceType == PieceType.ROOK) {
            return "R";
        }else if(pieceType == PieceType.KING){
            return "K";
        }else if (pieceType == PieceType.QUEEN) {
            return "Q";
        }
        return "E";

    }
}

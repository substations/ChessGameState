package com.example.chessgamestate;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChessState {

    private Piece[][] pieces; // An array that holds all of the pieces and their position
    private int[][] board; // An array that determines what kind of drawing should be made
    private int turnCount;

    private boolean piecesPlaced = false;
    private boolean boardInitialized = false;

    //0: white
    //1: black
    private int playerToMove;

    public ChessState() {
        pieces = new Piece[8][8];
        board = new int[8][8];

        // Setting the initial position of all of the pieces
        for (int row = 0; row < pieces.length; row++) {
            for (int col = 0; col < pieces[row].length; col++) {
                if (col == 0) {
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK);
                    pieces[1][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK);
                    pieces[2][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[3][col] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.BLACK);
                    pieces[4][col] = new Piece(Piece.PieceType.KING, Piece.ColorType.BLACK);
                    pieces[5][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK);
                    pieces[6][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK);
                    pieces[7][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK);
                } else if (col == 1) {
                    pieces[row][1] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.BLACK);
                } else if (col == 6) {
                    pieces[row][6] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.WHITE);
                } else if (col == 7) {
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE);
                    pieces[1][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE);
                    pieces[2][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE);
                    pieces[3][col] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.WHITE);
                    pieces[4][col] = new Piece(Piece.PieceType.KING, Piece.ColorType.WHITE);
                    pieces[5][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE);
                    pieces[6][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE);
                    pieces[7][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE);
                } else {
                    pieces[row][col] = new Piece(Piece.PieceType.EMPTY, Piece.ColorType.EMPTY);
                }
            }
        }
        piecesPlaced = true;

        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                board[row][col] = 0;
            }
        }
        boardInitialized = true;
        playerToMove = 0;
        turnCount = 0;
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
        boardInitialized = other.boardInitialized;
        piecesPlaced = other.piecesPlaced;
        playerToMove = other.playerToMove;
        turnCount = other.turnCount;
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

    //checks if selected piece and playerToMove color is consistent
    public boolean checkSelectPiece(int id, Piece p){
        //check if white
        if((p.getPieceColor() == Piece.ColorType.WHITE) && (id == 0)){
            return true;
        } else if ((p.getPieceColor() == Piece.ColorType.BLACK) && (id == 1)){ //check if black
            return true;
        }
        return false;
    }

    //checks if the selected piece is able to go to the new position they want to move to
    public boolean checkMovePiece(int id, Piece currentPiece, Piece newPiece){
        if(id == 0 && currentPiece.getPieceColor() == Piece.ColorType.WHITE &&
                newPiece.getPieceColor() != Piece.ColorType.WHITE) {
            return true;
        } else if(id == 1 && currentPiece.getPieceColor() == Piece.ColorType.BLACK &&
                newPiece.getPieceColor() != Piece.ColorType.BLACK) {
            return true;
        }
        return false;
    }
    //checks if the selected piece is able to capture the piece on the position they want to move to
    public boolean checkCapture(int id, Piece currentPiece, Piece otherPiece) {
        if(id == 0 && currentPiece.getPieceColor() == Piece.ColorType.WHITE &&
                otherPiece.getPieceColor() == Piece.ColorType.BLACK) {
            return true;
        } else if(id == 1 && currentPiece.getPieceColor() == Piece.ColorType.BLACK &&
                otherPiece.getPieceColor() == Piece.ColorType.WHITE) {
            return true;
        }
        return false;
    }

    //checks if the player moving is a pawn and if that pawn is able to be promoted because of its current position
    public boolean checkPromotion(Piece p) {
        if (p.getPieceType() == Piece.PieceType.PAWN && p.getPieceColor() == Piece.ColorType.WHITE) {
            for(int i = 0; i < pieces[0].length; i++) {
                if (p == pieces[0][i]) {
                    return true;
                }
            }
        } else if(p.getPieceType() == Piece.PieceType.PAWN && p.getPieceColor() == Piece.ColorType.BLACK) {
            for (int i = 0; i < pieces[0].length; i++) {
                if (p == pieces[7][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks if it's the players turn who clicks the resign button
    public boolean checkResign(int id) {
        if (id == 0) {
            return true;
        } else if (id == 1) {
            return true;
        }
        return false;
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

    @Override
    public String toString() {
//        String toReturn = "";
//        if(turnCount == 0) {
//            if (piecesPlaced) {
//                toReturn += "Pieces Placed\n";
//            }
//            if (boardInitialized) {
//                toReturn += "Board setup\n";
//            }
//        }
//        int currPlayer = getWhoseMove();
//        boolean canMove = checkSelectPiece(currPlayer,pieces[5][6]) &&
//                checkMovePiece(currPlayer,pieces[5][6],pieces[5][4]);
//        if(turnCount == 0) {
//            toReturn += "Player " + currPlayer + "'s turn\n";
//            toReturn += "Player " + currPlayer + " chooses to move a Pawn\n";
//            toReturn += "Player " + currPlayer + " moves Pawn to e4\n";
//        }else if(turnCount == 1) {
//            toReturn += "Player " + currPlayer + "'s turn\n";
//            toReturn += "Player " + currPlayer + " chooses to move a Knight\n";
//            toReturn += "Player " + currPlayer + " moves Knight to f6\n";
//        }else if(turnCount == 2){
//            toReturn += "Player " + currPlayer + "'s turn\n";
//            toReturn += "Player " + currPlayer + " chooses to move a Bishop\n";
//            toReturn += "Player " + currPlayer + " moves Pawn to c4\n";
//        }else{
//            toReturn += "End of Game State\n";
//        }
//        setWhoseMove(1 - currPlayer);
//        turnCount++;
//        return toReturn;
        String toReturn = "";
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                toReturn += (String.valueOf(pieces[j][i]) + " ");
            }
            toReturn += "\n";
        }
        return toReturn;
    }

    public int getTurnCount(){return this.turnCount;}
}

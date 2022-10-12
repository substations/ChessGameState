package com.example.chessgamestate;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ChessState {

    private Piece[][] pieces; // An array that holds all of the pieces and their position
    private int[][] board; // An array that determines what kind of drawing should be made
    private int turnCount;

    private ArrayList<Piece> whiteCapturedPieces;
    private ArrayList<Piece> blackCapturedPieces;

    private boolean piecesPlaced = false;
    private boolean boardInitialized = false;
    public Piece emptyPiece;

    //0: white
    //1: black
    private int playerToMove;

    public ChessState() {
        pieces = new Piece[8][8];
        board = new int[8][8];
        whiteCapturedPieces =new ArrayList<>();
        blackCapturedPieces =new ArrayList<>();

        // Setting the initial position of all of the pieces
        for (int row = 0; row < pieces.length; row++) {
            for (int col = 0; col < pieces[row].length; col++) {
                if (col == 0) {
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK, 0, col);
                    pieces[1][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK, 1, col);
                    pieces[2][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK, 2, col);
                    pieces[3][col] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.BLACK, 3, col);
                    pieces[4][col] = new Piece(Piece.PieceType.KING, Piece.ColorType.BLACK, 4, col);
                    pieces[5][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.BLACK, 5, col);
                    pieces[6][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.BLACK, 6, col);
                    pieces[7][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.BLACK, 7, col);
                } else if (col == 1) {
                    pieces[row][1] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.BLACK, row, 1);
                } else if (col == 6) {
                    pieces[row][6] = new Piece(Piece.PieceType.PAWN, Piece.ColorType.WHITE, row, 6);
                } else if (col == 7) {
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE,0, col);
                    pieces[1][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE, 1, col);
                    pieces[2][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE, 2, col);
                    pieces[3][col] = new Piece(Piece.PieceType.QUEEN, Piece.ColorType.WHITE, 3, col);
                    pieces[4][col] = new Piece(Piece.PieceType.KING, Piece.ColorType.WHITE, 4, col);
                    pieces[5][col] = new Piece(Piece.PieceType.BISHOP, Piece.ColorType.WHITE, 5, col);
                    pieces[6][col] = new Piece(Piece.PieceType.KNIGHT, Piece.ColorType.WHITE, 6, col);
                    pieces[7][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE, 7, col);
                } else {
                    pieces[row][col] = new Piece(Piece.PieceType.EMPTY, Piece.ColorType.EMPTY, row, col);
                }
            }
        }
        emptyPiece = new Piece(Piece.PieceType.EMPTY,Piece.ColorType.EMPTY, 0, 0);
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
    public boolean checkMovePiece(int id, Piece currentPiece, Piece newPosition){
        if(id == 0 && currentPiece.getPieceColor() == Piece.ColorType.WHITE
                && newPosition.getPieceColor() != Piece.ColorType.WHITE) {
            if (currentPiece.getPieceType() == Piece.PieceType.PAWN) {
                Piece.ColorType color = Piece.ColorType.WHITE;
                movePawn(currentPiece, newPosition, color);
            } else if (currentPiece.getPieceType() == Piece.PieceType.BISHOP) {
                moveBishop(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.KNIGHT) {
                moveKnight(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.ROOK) {
                moveRook(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.QUEEN) {
                moveQueen(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.KING) {
                moveKing(currentPiece, newPosition);
            }
        } else if (id == 1 && currentPiece.getPieceColor() == Piece.ColorType.BLACK
                && newPosition.getPieceColor() != Piece.ColorType.BLACK) {
            if (currentPiece.getPieceType() == Piece.PieceType.PAWN) {
                Piece.ColorType color = Piece.ColorType.BLACK;
                movePawn(currentPiece, newPosition, color);
            } else if (currentPiece.getPieceType() == Piece.PieceType.BISHOP) {
                moveBishop(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.KNIGHT) {
                moveKnight(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.ROOK) {
                moveRook(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.QUEEN) {
                moveQueen(currentPiece, newPosition);
            } else if (currentPiece.getPieceType() == Piece.PieceType.KING) {
                moveKing(currentPiece, newPosition);
            }
        }
        return false;
    }

    public boolean movePawn(Piece currentPosition, Piece newPosition, Piece.ColorType color) {
        if(color == Piece.ColorType.WHITE) {
            if (newPosition.getX() == (currentPosition.getX() + 2)) {
                if (newPosition.getY() == 6) {
                    return true;
                }
            } else if (newPosition.getX() == (currentPosition.getX() + 1)) {
                return true;
            } else if (newPosition.getX() == currentPosition.getX() + 1 &&
                    newPosition.getY() == currentPosition.getY() + 1) {
                return true;
            }
        } else if(color == Piece.ColorType.BLACK) {
            if (newPosition.getX() == (currentPosition.getX() - 2)) {
                if (newPosition.getY() == 1) {
                    return true;
                }
            } else if (newPosition.getX() == (currentPosition.getX() - 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean moveBishop(Piece currentPosition, Piece newPosition) {
        if(generalDiagonalMove(currentPosition, newPosition)) {
            return true;
        }
        return false;
    }

    public boolean generalDiagonalMove(Piece currentPosition, Piece newPosition) {
        for(int i = 1; i < pieces.length; i++) {
            //moving up and right
            if(currentPosition.getX() + i <= 7) {
                //moving right
                if (newPosition.getX() == currentPosition.getX() + i) {
                    if (currentPosition.getY() - i >= 0) {
                        //moving up
                        if (newPosition.getY() == currentPosition.getY() - i) {
                            return true;
                        }
                    }
                }
            }
            //moving up and left
            if(currentPosition.getX() - i >= 0) {
                //moving left
                if (newPosition.getX() == currentPosition.getX() - i) {
                    if (currentPosition.getY() - i >= 0) {
                        //moving up
                        if (newPosition.getY() == currentPosition.getY() - i) {
                            return true;
                        }
                    }
                }
            }
            //moving down and left
            if(currentPosition.getX() - i >= 0) {
                //moving left
                if (newPosition.getX() == currentPosition.getX() - i) {
                    if (currentPosition.getY() + i <= 7) {
                        //moving down
                        if (newPosition.getY() == currentPosition.getY() + i) {
                            return true;
                        }
                    }
                }
            }
            //moving down and right
            if(currentPosition.getX() + i <= 7) {
                //moving right
                if (newPosition.getX() == currentPosition.getX() + i) {
                    if (currentPosition.getY() + i <= 7) {
                        //moving down
                        if (newPosition.getY() == currentPosition.getY() + i) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean generalSideMove(Piece currentPosition, Piece newPosition) {
        for(int i = 1; i < pieces.length; i++) {
            //moving left
            if(newPosition.getX() - i >= 0) {
                if(newPosition.getX() == currentPosition.getX() - i) {
                    return true;
                }
            }
            //moving right
            if(newPosition.getX() + i <= 7) {
                if(newPosition.getX() == currentPosition.getX() + i) {
                    return true;
                }
            }
            //moving up
            if(newPosition.getY() - i >= 0) {
                if(newPosition.getY() == currentPosition.getY() - i) {
                    return true;
                }
            }
            //moving down
            if(newPosition.getY() + i <= 7) {
                if(newPosition.getY() == currentPosition.getY() + i) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveKnight(Piece currentPosition, Piece newPosition) {
        if(currentPosition.getX() + 2 <= 7) {
            if (newPosition.getX() == currentPosition.getX() + 2) {
                if (newPosition.getY() == currentPosition.getY() + 1) {
                    return true;
                } else if (newPosition.getY() == currentPosition.getY() - 1) {
                    return true;
                }
            }
        }
        if(currentPosition.getX() - 2 >= 0) {
            if (newPosition.getX() == currentPosition.getX() - 2) {
                if (newPosition.getY() == currentPosition.getY() + 1) {
                    return true;
                } else if (newPosition.getY() == currentPosition.getY() - 1) {
                    return true;
                }
            }
        }
        if(currentPosition.getY() + 2 <= 7) {
            if (newPosition.getY() == currentPosition.getY() + 2) {
                if (newPosition.getX() == currentPosition.getX() + 1) {
                    return true;
                } else if (newPosition.getX() == currentPosition.getX() - 1) {
                    return true;
                }
            }
        }
        if(currentPosition.getY() - 2 >= 0) {
            if (newPosition.getY() == currentPosition.getY() - 2) {
                if (newPosition.getX() == currentPosition.getX() + 1) {
                    return true;
                } else if (newPosition.getX() == currentPosition.getX() - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveRook(Piece currentPosition, Piece newPosition) {
        if(generalSideMove(currentPosition, newPosition)) {
            return true;
        }
        return false;
    }

    public boolean moveQueen(Piece currentPosition, Piece newPosition) {
        if(generalDiagonalMove(currentPosition, newPosition)) {
            return true;
        }
        if(generalSideMove(currentPosition, newPosition)) {
            return true;
        }
        return false;
    }

    public boolean moveKing(Piece currentPosition, Piece newPosition) {
        //moving up and right
        if(currentPosition.getX() + 1 <= 7) {
            //moving right
            if (newPosition.getX() == currentPosition.getX() + 1) {
                if (currentPosition.getY() - 1 >= 0) {
                    //moving up
                    if (newPosition.getY() == currentPosition.getY() - 1) {
                        return true;
                    }
                }
            }
        }
        //moving up and left
        if(currentPosition.getX() - 1 >= 0) {
            //moving left
            if (newPosition.getX() == currentPosition.getX() - 1) {
                if (currentPosition.getY() - 1 >= 0) {
                    //moving up
                    if (newPosition.getY() == currentPosition.getY() - 1) {
                        return true;
                    }
                }
            }
        }
        //moving down and left
        if(currentPosition.getX() - 1 >= 0) {
            //moving left
            if (newPosition.getX() == currentPosition.getX() - 1) {
                if (currentPosition.getY() + 1 <= 7) {
                    //moving down
                    if (newPosition.getY() == currentPosition.getY() + 1) {
                        return true;
                    }
                }
            }
        }
        //moving down and right
        if(currentPosition.getX() + 1 <= 7) {
            //moving right
            if (newPosition.getX() == currentPosition.getX() + 1) {
                if (currentPosition.getY() + 1 <= 7) {
                    //moving down
                    if (newPosition.getY() == currentPosition.getY() + 1) {
                        return true;
                    }
                }
            }
        }
        //moving left
        if(newPosition.getX() - 1 >= 0) {
            if(newPosition.getX() == currentPosition.getX() - 1) {
                return true;
            }
        }
        //moving right
        if(newPosition.getX() + 1 <= 7) {
            if(newPosition.getX() == currentPosition.getX() + 1) {
                return true;
            }
        }
        //moving up
        if(newPosition.getY() - 1 >= 0) {
            if(newPosition.getY() == currentPosition.getY() - 1) {
                return true;
            }
        }
        //moving down
        if(newPosition.getY() + 1 <= 7) {
            if(newPosition.getY() == currentPosition.getY() + 1) {
                return true;
            }
        }
        return false;
    }

    //checks if the selected piece is able to capture the piece on the position they want to move to
    public boolean checkCapture(int id, Piece currentPiece, Piece otherPiece) {
        if(currentPiece.getPieceType() != Piece.PieceType.PAWN) {
            if (id == 0 && currentPiece.getPieceColor() == Piece.ColorType.WHITE &&
                    otherPiece.getPieceColor() == Piece.ColorType.BLACK) {
                return true;
            } else if (id == 1 && currentPiece.getPieceColor() == Piece.ColorType.BLACK &&
                    otherPiece.getPieceColor() == Piece.ColorType.WHITE) {
                return true;
            }
        } else if (currentPiece.getPieceType() == Piece.PieceType.PAWN) {
            //checkCapturePawn(id, currentPiece, otherPiece);
        }
        return false;
    }

    /*public boolean checkCapturePawn(int id, Piece currentPiece, Piece otherPiece) {
        if(id == 0) {
            if (otherPiece.getX() == currentPiece.getX() + 1) {
                if (otherPiece.getY() == currentPiece.getY() + 1) {
                    if(otherPiece.getPieceColor() == Piece.ColorType.BLACK) {
                        return true;
                    }
                }
            }
        } else if (id == 1) {
            if (otherPiece.getX() == currentPiece.getX() + 1) {
                if (otherPiece.getY() == currentPiece.getY() + 1) {
                    if (otherPiece.getPieceColor() == Piece.ColorType.WHITE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }*/

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
        String toReturn = "";
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                toReturn += (String.valueOf(pieces[j][i]) + " ");
            }
            toReturn += "\n";
        }

        toReturn += "\n White Captured Pieces: ";

        for(int i = 0; i < whiteCapturedPieces.size(); i++){
            toReturn += (String.valueOf(whiteCapturedPieces.get(i)));
        }

        toReturn += "\n Black Captured Pieces: ";
        for(int i = 0; i < blackCapturedPieces.size(); i++){
            toReturn += (String.valueOf(blackCapturedPieces.get(i)));
        }

        toReturn += "\n";
        return toReturn;
    }

    public int getTurnCount(){return this.turnCount;}

    public ArrayList<Piece> getWhiteCapturedPieces(){return this.whiteCapturedPieces;}
    public ArrayList<Piece> getBlackCapturedPieces(){return this.blackCapturedPieces;}
}

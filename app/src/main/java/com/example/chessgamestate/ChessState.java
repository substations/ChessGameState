package com.example.chessgamestate;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ChessState {

    private Piece[][] pieces; // An array that holds all of the pieces and their position
    private int turnCount;

    private ArrayList<Piece> whiteCapturedPieces;
    private ArrayList<Piece> blackCapturedPieces;

    public Piece emptyPiece;

    //0: white
    //1: black
    private int playerToMove;

    public ChessState() {
        pieces = new Piece[8][8];
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();

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
                    pieces[0][col] = new Piece(Piece.PieceType.ROOK, Piece.ColorType.WHITE, 0, col);
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
        emptyPiece = new Piece(Piece.PieceType.EMPTY, Piece.ColorType.EMPTY, 0, 0);

        playerToMove = 0;
        turnCount = 0;
    }

    // Copy Constructor
    public ChessState(ChessState other) {
        pieces = new Piece[8][8];
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = other.pieces[i][j];
            }
        }
        emptyPiece = other.emptyPiece;

        playerToMove = other.playerToMove;
        turnCount = other.turnCount;
    }

    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        piece.setY(col);
        piece.setX(row);
        pieces[row][col] = piece;
    }

    public int getWhoseMove() {
        return playerToMove;
    }

    public void setWhoseMove(int id) {
        playerToMove = id;
    }

    //checks if selected piece and playerToMove color is consistent
    public boolean checkSelectPiece(int id, Piece p) {
        //check if white
        if ((p.getPieceColor() == Piece.ColorType.WHITE) && (id == 0)) {
            return true;
        } else if ((p.getPieceColor() == Piece.ColorType.BLACK) && (id == 1)) { //check if black
            return true;
        }
        return false;
    }

    //checks if the selected piece is able to go to the new position they want to move to
    public boolean checkMovePiece(int id, Piece currentPiece, Piece newPosition) {
        if (id == 0 && currentPiece.getPieceColor() == Piece.ColorType.WHITE
                && newPosition.getPieceColor() != Piece.ColorType.WHITE) {
            if (currentPiece.getPieceType() == Piece.PieceType.PAWN) {
                Piece.ColorType color = Piece.ColorType.WHITE;
                return movePawn(currentPiece, newPosition, color);
            }
        } else if (id == 1 && currentPiece.getPieceColor() == Piece.ColorType.BLACK
                && newPosition.getPieceColor() != Piece.ColorType.BLACK) {
            if (currentPiece.getPieceType() == Piece.PieceType.PAWN) {
                Piece.ColorType color = Piece.ColorType.BLACK;
                return movePawn(currentPiece, newPosition, color);
            }
        }
        if (currentPiece.getPieceType() == Piece.PieceType.BISHOP) {
            return moveBishop(currentPiece, newPosition);
        } else if (currentPiece.getPieceType() == Piece.PieceType.KNIGHT) {
            return moveKnight(currentPiece, newPosition);
        } else if (currentPiece.getPieceType() == Piece.PieceType.ROOK) {
            return moveRook(currentPiece, newPosition);
        } else if (currentPiece.getPieceType() == Piece.PieceType.QUEEN) {
            return moveQueen(currentPiece, newPosition);
        } else if (currentPiece.getPieceType() == Piece.PieceType.KING) {
            return moveKing(currentPiece, newPosition);
        }
        return false;
    }

    public boolean movePawn(Piece currentPosition, Piece newPosition, Piece.ColorType color) {
        if (color == Piece.ColorType.WHITE) {
            if (newPosition.getY() == (currentPosition.getY() - 2)
                    && newPosition.getX() == currentPosition.getX()) {
                if (currentPosition.getY() == 6) {
                    return true;
                }
            } else if (newPosition.getY() == (currentPosition.getY() - 1)
                    && newPosition.getX() == currentPosition.getX()) {
                return true;
            } else if (newPosition.getY() == currentPosition.getY() - 1 &&
                    newPosition.getX() == currentPosition.getX() + 1) {
                if (newPosition.getPieceColor() == Piece.ColorType.BLACK) {
                    return true;
                }
            } else if (newPosition.getY() == currentPosition.getY() - 1 &&
                    newPosition.getX() == currentPosition.getX() - 1) {
                if (newPosition.getPieceColor() == Piece.ColorType.BLACK) {
                    return true;
                }
            }
        } else if (color == Piece.ColorType.BLACK) {
            if (newPosition.getY() == (currentPosition.getY() + 2)) {
                if (currentPosition.getY() == 1) {
                    return true;
                }
            } else if (newPosition.getY() == (currentPosition.getY() + 1)) {
                return true;
            } else if (newPosition.getY() == currentPosition.getY() + 1 &&
                    newPosition.getX() == currentPosition.getX() + 1) {
                if (newPosition.getPieceColor() == Piece.ColorType.WHITE) {
                    return true;
                }
            } else if (newPosition.getY() == currentPosition.getY() + 1 &&
                    newPosition.getX() == currentPosition.getX() - 1) {
                if (newPosition.getPieceColor() == Piece.ColorType.WHITE) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moveBishop(Piece currentPosition, Piece newPosition) {
        return generalDiagonalMove(currentPosition, newPosition);
    }

    public boolean generalDiagonalMove(Piece currentPosition, Piece newPosition) {
        for (int i = 1; i < pieces.length; i++) {

            //moving up and right
            if (currentPosition.getX() + i <= 7 && currentPosition.getY() - i >= 0) {
                if (pieces[currentPosition.getX() + i][currentPosition.getY() - i].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getX() == currentPosition.getX() + i
                            && newPosition.getY() == currentPosition.getY() - i) {
                        return true;
                    }
                }
            }
            //moving up and left
            if (currentPosition.getX() - i >= 0 && currentPosition.getY() - i >= 0) {
                if (pieces[currentPosition.getX() - i][currentPosition.getY() - i].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getX() == currentPosition.getX() - i
                            && newPosition.getY() == currentPosition.getY() - i) {
                        return true;
                    }
                }
            }
            //moving down and left
            if (currentPosition.getX() - i >= 0 && currentPosition.getY() + i <= 7) {
                if (pieces[currentPosition.getX() - i][currentPosition.getY() + i].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getX() == currentPosition.getX() - i
                            && newPosition.getY() == currentPosition.getY() + i) {
                        return true;
                    }
                }
            }
            //moving down and right
            if (currentPosition.getX() + i <= 7 && currentPosition.getY() + i <= 7) {
                if (pieces[currentPosition.getX() + i][currentPosition.getY() + i].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getX() == currentPosition.getX() + i
                            && newPosition.getY() == currentPosition.getY() + i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean generalSideMove(Piece currentPosition, Piece newPosition) {
        for (int i = 1; i < pieces.length; i++) {
            //moving left
            if (currentPosition.getX() - i >= 0) {
                if (pieces[currentPosition.getX() - i][currentPosition.getY()].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getX() == currentPosition.getX() - i) {
                        return true;
                    }
                }
            }
            //moving right
            if (currentPosition.getX() + i <= 7) {
                if (pieces[currentPosition.getX() + i][currentPosition.getY()].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getX() == currentPosition.getX() + i) {
                        return true;
                    }
                }
            }
            //moving up
            if (currentPosition.getY() - i >= 0) {
                if (pieces[currentPosition.getX()][currentPosition.getY() - i].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getY() == currentPosition.getY() - i) {
                        return true;
                    }
                }
            }
            //moving down
            if (currentPosition.getY() + i <= 7) {
                if (pieces[currentPosition.getX()][currentPosition.getY() + i].getPieceType()
                        == Piece.PieceType.EMPTY) {
                    if (newPosition.getY() == currentPosition.getY() + i) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveKnight(Piece currentPosition, Piece newPosition) {
        if (currentPosition.getX() + 2 <= 7) {
            if (newPosition.getX() == currentPosition.getX() + 2) {
                if (newPosition.getY() == currentPosition.getY() + 1) {
                    if (pieces[currentPosition.getX() + 2][currentPosition.getY() + 1].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                } else if (newPosition.getY() == currentPosition.getY() - 1) {
                    if (pieces[currentPosition.getX() + 2][currentPosition.getY() - 1].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                }
            }
        }
        if (currentPosition.getX() - 2 >= 0) {
            if (newPosition.getX() == currentPosition.getX() - 2) {
                if (newPosition.getY() == currentPosition.getY() + 1) {
                    if (pieces[currentPosition.getX() - 2][currentPosition.getY() + 1].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                } else if (newPosition.getY() == currentPosition.getY() - 1) {
                    if (pieces[currentPosition.getX() - 2][currentPosition.getY() - 1].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                }
            }
        }
        if (currentPosition.getY() + 2 <= 7) {
            if (newPosition.getY() == currentPosition.getY() + 2) {
                if (newPosition.getX() == currentPosition.getX() + 1) {
                    if (pieces[currentPosition.getX() + 1][currentPosition.getY() + 2].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                } else if (newPosition.getX() == currentPosition.getX() - 1) {
                    if (pieces[currentPosition.getX() - 1][currentPosition.getY() + 2].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                }
            }
        }
        if (currentPosition.getY() - 2 >= 0) {
            if (newPosition.getY() == currentPosition.getY() - 2) {
                if (newPosition.getX() == currentPosition.getX() + 1) {
                    if (pieces[currentPosition.getX() + 1][currentPosition.getY() - 2].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                } else if (newPosition.getX() == currentPosition.getX() - 1) {
                    if (pieces[currentPosition.getX() - 1][currentPosition.getY() - 2].getPieceType()
                            == Piece.PieceType.EMPTY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveRook(Piece currentPosition, Piece newPosition) {
        return generalSideMove(currentPosition, newPosition);
    }

    public boolean moveQueen(Piece currentPosition, Piece newPosition) {
        if (generalDiagonalMove(currentPosition, newPosition)) {
            return true;
        }
        return generalSideMove(currentPosition, newPosition);
    }

    public boolean moveKing(Piece currentPosition, Piece newPosition) {
        //moving up and right
        if (currentPosition.getX() + 1 <= 7 && currentPosition.getY() - 1 >= 0) {
            if (pieces[currentPosition.getX() + 1][currentPosition.getY() - 1].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX() + 1
                        && newPosition.getY() == currentPosition.getY() - 1) {
                    return true;
                }
            }
        }
        //moving up and left
        if (currentPosition.getX() - 1 >= 0 && currentPosition.getY() - 1 >= 0) {
            if (pieces[currentPosition.getX() - 1][currentPosition.getY() - 1].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX() - 1
                        && newPosition.getY() == currentPosition.getY() - 1) {
                    return true;
                }
            }
        }
        //moving down and left
        if (currentPosition.getX() - 1 >= 0 && currentPosition.getY() + 1 <= 7) {
            if (pieces[currentPosition.getX() - 1][currentPosition.getY() + 1].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX() - 1
                        && newPosition.getY() == currentPosition.getY() + 1) {
                    return true;
                }
            }
        }
        //moving down and right
        if (currentPosition.getX() + 1 <= 7 && currentPosition.getY() + 1 <= 7) {
            if (pieces[currentPosition.getX() + 1][currentPosition.getY() + 1].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX() + 1
                        && newPosition.getY() == currentPosition.getY() + 1) {
                    return true;
                }
            }
        }
        //moving left
        if (currentPosition.getX() - 1 >= 0) {
            if (pieces[currentPosition.getX() - 1][currentPosition.getY()].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX() - 1
                        && newPosition.getY() == currentPosition.getY()) {
                    return true;
                }
            }
        }
        //moving right
        if (currentPosition.getX() + 1 <= 7) {
            if (pieces[currentPosition.getX() + 1][currentPosition.getY()].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX() + 1
                        && newPosition.getY() == currentPosition.getY()) {
                    return true;
                }
            }
        }
        //moving up
        if (currentPosition.getY() - 1 >= 0) {
            if (pieces[currentPosition.getX()][currentPosition.getY() - 1].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX()
                        && newPosition.getY() == currentPosition.getY() - 1) {
                    return true;
                }
            }
        }
        //moving down
        if (currentPosition.getY() + 1 <= 7) {
            if (pieces[currentPosition.getX()][currentPosition.getY() + 1].getPieceType()
                    == Piece.PieceType.EMPTY) {
                if (newPosition.getX() == currentPosition.getX()
                        && newPosition.getY() == currentPosition.getY() + 1) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks if the selected piece is able to capture the piece on the position they want to move to
    public boolean checkCapture(int id, Piece currentPiece, Piece otherPiece) {
        if (id == 0 && currentPiece.getPieceColor() == Piece.ColorType.WHITE &&
                otherPiece.getPieceColor() == Piece.ColorType.BLACK) {
            return true;
        } else if (id == 1 && currentPiece.getPieceColor() == Piece.ColorType.BLACK &&
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

    @Override
    public String toString() {
        String toReturn = "";
        for(int i = 0; i < pieces.length; i++) {
            for(int j = 0; j < pieces[i].length; j++) {
                toReturn += (pieces[j][i] + " ");
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


    public ArrayList<Piece> getWhiteCapturedPieces(){return this.whiteCapturedPieces;}
    public ArrayList<Piece> getBlackCapturedPieces(){return this.blackCapturedPieces;}
}
